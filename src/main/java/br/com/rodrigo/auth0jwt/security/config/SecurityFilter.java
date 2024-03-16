package br.com.rodrigo.auth0jwt.security.config;

import br.com.rodrigo.auth0jwt.models.User;
import br.com.rodrigo.auth0jwt.repositories.UserRepository;
import br.com.rodrigo.auth0jwt.services.AuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserRepository userRepository;


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        String token = extractToken(request);

        if (token != null) {
            String login = authenticationService.validateTokenJwt(token);
            User user = userRepository.findByLogin(login);

            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);

    }

    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        return Optional.ofNullable(authHeader)
                .filter(header -> header.toLowerCase().startsWith("bearer "))
                .map(header -> header.substring(7).trim())
                .filter(token -> !token.isEmpty())
                .orElse(null);
    }
}
