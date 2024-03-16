package br.com.rodrigo.auth0jwt.services;


import br.com.rodrigo.auth0jwt.models.User;
import br.com.rodrigo.auth0jwt.models.dtos.UserDto;
import br.com.rodrigo.auth0jwt.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDto saveUser(UserDto userDto) {
        User isExistUser = userRepository.findByLogin(userDto.login());
        if (isExistUser != null) {
            throw new RuntimeException("Exist User");
        }

        var passwordHash = passwordEncoder.encode(userDto.password());

        User newUser = userRepository.save(
                new User(userDto.name(), userDto.login(), passwordHash, userDto.role())
        );

        return new UserDto(newUser.getName(), newUser.getLogin(), newUser.getPassword(), newUser.getRole());
    }


}
