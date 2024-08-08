package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.dto.request.ParqueaderoUsuarioDTO;
import com.pruebatecnica.demo.dto.request.UsuarioCreateDTO;
import com.pruebatecnica.demo.entity.Usuario;
import com.pruebatecnica.demo.responses.usuario.UsuarioCreateResponse;
import com.pruebatecnica.demo.responses.usuario.UsuarioParqueaderoDesvinculacionResponse;
import com.pruebatecnica.demo.responses.usuario.UsuarioParqueaderoVinculacionResponse;
import com.pruebatecnica.demo.responses.usuario.UsuarioUpdateResponse;
import com.pruebatecnica.demo.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    public final UsuarioService usuarioService;


    @GetMapping(value = "{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SOCIO')")
    public ResponseEntity<Usuario> getUsuario(@PathVariable Integer id){
        return ResponseEntity.ok(usuarioService.getUsuario(id));
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioCreateResponse> createSocio(@Valid @RequestBody UsuarioCreateDTO usuarioCreateDTO) {
        UsuarioCreateResponse usuarioCreateResponse = usuarioService.createSocio(usuarioCreateDTO);
            return ResponseEntity.ok(usuarioCreateResponse);
    }

    @PutMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'SOCIO')")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioUpdateResponse updateSocio(@Valid @RequestBody UsuarioCreateDTO usuarioCreateDTO){
        return usuarioService.updateSocio(usuarioCreateDTO);
    }

    @DeleteMapping(value = "{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteSocio(@PathVariable Integer id){
        usuarioService.deleteSocio(id);
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public List<Usuario> getUsuarios(){
        return usuarioService.listUsuarios();
    }

    @PostMapping(value = "{id}/parqueaderos")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioParqueaderoVinculacionResponse vincularSocioParqueadero(@PathVariable Integer id, @RequestBody ParqueaderoUsuarioDTO parqueaderos){
        return usuarioService.asignarParqueadero(id,parqueaderos);
    }

    @PostMapping(value = "{id}/parqueaderos/delete")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioParqueaderoDesvinculacionResponse desvincularSocioParqueadero(@PathVariable Integer id, @RequestBody ParqueaderoUsuarioDTO parqueaderoUsuarioDTO){
        return usuarioService.desvincularParqueadero(id, parqueaderoUsuarioDTO);
    }
}
