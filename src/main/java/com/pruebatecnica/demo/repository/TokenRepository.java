package com.pruebatecnica.demo.repository;

import com.pruebatecnica.demo.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query("SELECT t FROM Token t WHERE t.fechaCreacion < :expirationTime")
    List<Token> findTokensOlderThan(@Param("expirationTime") LocalDateTime expirationTime);

   Optional<Token> findByTokenUsuario(String token);
}
