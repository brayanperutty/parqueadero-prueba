package com.pruebatecnica.demo.auth;

import com.pruebatecnica.demo.entity.Rol;
import com.pruebatecnica.demo.entity.Token;
import com.pruebatecnica.demo.entity.Usuario;
import com.pruebatecnica.demo.config.jwt.JwtService;
import com.pruebatecnica.demo.repository.RolRepository;
import com.pruebatecnica.demo.repository.TokenRepository;
import com.pruebatecnica.demo.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RolRepository rolRepository;
    private final TokenRepository tokenRepository;

    @Transactional
    public AuthResponse login(LoginUserRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        Usuario user = usuarioRepository.findByUsername(request.getUsername()).orElseThrow();
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
                .token(token)
                .tipoToken("BEARER")
                .fechaCreacion(localDateTime)
                .expired(false)
                .revoked(false)
                .build();

        tokenRepository.save(tokenUsuario);
    }

    public void register(RegisterSocioRequest socio) {

        Optional<Rol> rol = rolRepository.findById(socio.getRol());
        Usuario user = Usuario.builder()
                .username(socio.getUsername())
                .password(passwordEncoder.encode(socio.getPassword()))
                .nombreCompleto(socio.getNombreCompleto())
                .cedula(socio.getCedula())
                .rol(rol.get())
                .build();

        usuarioRepository.save(user);
    }

    public static LocalDateTime convertToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
}
