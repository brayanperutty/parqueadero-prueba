package com.pruebatecnica.demo.auth;

import com.pruebatecnica.demo.dto.UsuarioCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginUserRequest request){
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public void register(@RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        authService.register(usuarioCreateDTO);
    }
}
