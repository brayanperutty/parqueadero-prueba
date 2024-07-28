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

    @GetMapping(value = "get")
    @PreAuthorize("hasAnyRole('ADMIN', 'SOCIO')")
    public ResponseEntity<?> getUsuario(@RequestBody String cedula){
        return ResponseEntity.ok(usuarioService.getUsuario(cedula));
    }

    @PostMapping(value = "register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createSocio(@RequestBody RegisterSocioRequest socio) {
        response.clear();
        String mensaje = usuarioService.createSocio(socio);
        response.put("mensaje", mensaje);
        if(mensaje.startsWith("Socio")){
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping(value = "update")
    @PreAuthorize("hasAnyRole('ADMIN', 'SOCIO')")
    public ResponseEntity<?> updateSocio(@RequestBody Usuario usuario){
        response.clear();
        String mensaje = usuarioService.updateSocio(usuario);
        response.put("mensaje", mensaje);
        if(mensaje.startsWith("Socio")){
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSocio(@RequestBody String cedula){
        usuarioService.deleteSocio(cedula);
    }

    @GetMapping(value = "list")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> getUsuarios(){
        return usuarioService.listUsuarios();
    }

}
