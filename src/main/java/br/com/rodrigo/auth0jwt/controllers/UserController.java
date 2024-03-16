package br.com.rodrigo.auth0jwt.controllers;


import br.com.rodrigo.auth0jwt.models.dtos.UserDto;
import br.com.rodrigo.auth0jwt.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    private ResponseEntity<UserDto> saveUser(@RequestBody UserDto userDto) {
        return new ResponseEntity<>(userService.saveUser(userDto), HttpStatus.CREATED);
    }

    @GetMapping("/admin")
    private String getAdmin() {
        return "Admin Permission";
    }

    @GetMapping("/user")
    private String getUser() {
        return "User Permission";
    }
}
