package br.com.rodrigo.auth0jwt.models.dtos;

import br.com.rodrigo.auth0jwt.models.enums.RoleEnum;

public record UserDto(
        String name,
        String login,
        String password,
        RoleEnum role
) {
}
