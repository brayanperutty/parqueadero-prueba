package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.entity.Usuario;
import com.pruebatecnica.demo.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    public final UsuarioService usuarioService;

    @GetMapping("/{cedula}")
    public ResponseEntity<?> getUsuario(@PathVariable String cedula){
        return ResponseEntity.ok(usuarioService.usuarioRepository.findById(cedula));
    }

    @GetMapping(value = "list")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> getUsuarios(){
        return usuarioService.listUsuarios();
    }

}
