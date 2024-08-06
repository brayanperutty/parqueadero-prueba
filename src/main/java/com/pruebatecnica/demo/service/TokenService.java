package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.entity.Token;
import com.pruebatecnica.demo.repository.TokenRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final TokenRepository tokenRepository;

    @Scheduled(fixedRate = 3600000) // Ejecutar cada hora
    @Transactional
    public void cleanUpExpiredTokens() {
        LocalDateTime expirationTime = LocalDateTime.now().minusHours(6);
        List<Token> tokensToDelete = tokenRepository.findTokensOlderThan(expirationTime);
        if (!tokensToDelete.isEmpty()) {
            tokenRepository.deleteAll(tokensToDelete);
        }
    }
}
