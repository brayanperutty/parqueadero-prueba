package com.pruebatecnica.demo.auth;

import com.pruebatecnica.demo.entity.Rol;
import com.pruebatecnica.demo.entity.Usuario;
import com.pruebatecnica.demo.jwt.JwtService;
import com.pruebatecnica.demo.repository.RolRepository;
import com.pruebatecnica.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final RolRepository rolRepository;

    public AuthResponse login(LoginUserRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        Usuario user = usuarioRepository.findByUsername(request.getUsername()).orElseThrow();
        String token = jwtService.getToken(user);

        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse register(RegisterSocioRequest socio) {

        Optional<Rol> rol = rolRepository.findById(socio.getRol());
        Usuario user = Usuario.builder()
                .username(socio.getUsername())
                .password(passwordEncoder.encode(socio.getPassword()))
                .nombreCompleto(socio.getNombreCompleto())
                .cedula(socio.getCedula())
                .rol(rol.get())
                .build();

        usuarioRepository.save(user);

        return AuthResponse.builder()
                .token(jwtService.getToken(user))
                .build();

    }
}
