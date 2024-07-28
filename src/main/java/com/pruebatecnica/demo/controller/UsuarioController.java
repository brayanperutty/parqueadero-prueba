package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.auth.RegisterSocioRequest;
import com.pruebatecnica.demo.entity.Usuario;
import com.pruebatecnica.demo.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    public final UsuarioService usuarioService;

    Map<String, Object> response = new HashMap<>();

    @GetMapping("/{cedula}")
    public ResponseEntity<?> getUsuario(@PathVariable String cedula){
        return ResponseEntity.ok(usuarioService.usuarioRepository.findById(cedula));
    }

    @PostMapping(value = "register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> registrarSocio(@RequestBody RegisterSocioRequest socio) {
        response.clear();
        String mensaje = usuarioService.crearSocio(socio);
        response.put("mensaje", mensaje);
        if(mensaje.startsWith("Socio")){
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.badRequest().body(response);
        }
    }

    @GetMapping(value = "list")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> getUsuarios(){
        return usuarioService.listUsuarios();
    }

}
