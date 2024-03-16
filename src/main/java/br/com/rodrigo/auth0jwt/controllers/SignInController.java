package br.com.rodrigo.auth0jwt.controllers;


import br.com.rodrigo.auth0jwt.models.dtos.SignIn;
import br.com.rodrigo.auth0jwt.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sigin")
public class SignInController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AuthenticationService authenticationService;


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String sigIn(@RequestBody  SignIn signIn) {
        var userAuthenticationToken = new UsernamePasswordAuthenticationToken(signIn.login(), signIn.password());
        authenticationManager.authenticate(userAuthenticationToken);

        return authenticationService.getToken(signIn);
    }
}
