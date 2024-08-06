package com.pruebatecnica.demo.auth;

import com.pruebatecnica.demo.dto.UsuarioCreateDTO;
import com.pruebatecnica.demo.entity.Rol;
import com.pruebatecnica.demo.entity.Token;
import com.pruebatecnica.demo.entity.Usuario;
import com.pruebatecnica.demo.config.jwt.JwtService;
import com.pruebatecnica.demo.repository.RolRepository;
import com.pruebatecnica.demo.repository.TokenRepository;
import com.pruebatecnica.demo.repository.UsuarioRepository;
import com.pruebatecnica.demo.responses.usuario.UsuarioErrorResponses;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RolRepository rolRepository;
    private final TokenRepository tokenRepository;

    private final UsuarioErrorResponses usuarioErrorResponses;

    @Transactional
    public AuthResponse login(LoginUserRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsuario(), request.getPass()));

        Usuario user = usuarioRepository.findByUsername(request.getUsuario()).orElseThrow();
        String token = jwtService.getToken(user);
        Date fechaToken = jwtService.getIssuedFromToken(token);
        guardarTokenUsuario(user, token,fechaToken);

        return AuthResponse.builder()
                .token(token)
                .nombreUsuario(user.getNombreCompleto())
                .cedula(user.getCedula())
                .build();
    }

    public void guardarTokenUsuario(Usuario usuario, String token, Date fechaToken){
        LocalDateTime localDateTime = convertToLocalDateTime(fechaToken);
        Token tokenUsuario = Token.builder()
                .usuario(usuario)
                .tokenUsuario(token)
                .tipoToken("BEARER")
                .fechaCreacion(localDateTime)
                .expired(false)
                .revoked(false)
                .build();

        tokenRepository.save(tokenUsuario);
    }

    public void register(UsuarioCreateDTO usuarioCreateDTO) {

        Rol rol = rolRepository.findById(usuarioCreateDTO.getRol()).orElseThrow(() -> new RuntimeException(usuarioErrorResponses.getRolNoEncontrado()));
        Usuario.UsuarioBuilder builder = Usuario.builder();
        builder.username(usuarioCreateDTO.getUsername());
        builder.password(passwordEncoder.encode(usuarioCreateDTO.getPassword()));
        builder.nombreCompleto(usuarioCreateDTO.getNombreCompleto());
        builder.cedula(usuarioCreateDTO.getCedula());
        builder.rol(rol);
        Usuario user = builder
                .build();

        usuarioRepository.save(user);
    }

    public static LocalDateTime convertToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}
