package com.pruebatecnica.demo.auth;

import com.pruebatecnica.demo.entity.Token;
import com.pruebatecnica.demo.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    private final TokenRepository tokenRepository;

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        Token tokenUsuario = tokenRepository.findByToken(jwt)
                .orElse(null);
        if (tokenUsuario != null) {
            tokenUsuario.setExpired(true);
            tokenUsuario.setRevoked(true);
            tokenRepository.save(tokenUsuario);
            SecurityContextHolder.clearContext();
        }
    }
}
