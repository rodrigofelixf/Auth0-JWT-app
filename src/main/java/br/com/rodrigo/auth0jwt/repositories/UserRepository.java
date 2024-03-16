package br.com.rodrigo.auth0jwt.repositories;

import br.com.rodrigo.auth0jwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByLogin(String login);
}
