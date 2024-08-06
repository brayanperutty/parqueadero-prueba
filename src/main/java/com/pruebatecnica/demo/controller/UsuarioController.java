package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.dto.ParqueaderoUsuarioDTO;
import com.pruebatecnica.demo.dto.UsuarioCreateDTO;
import com.pruebatecnica.demo.entity.Parqueadero;
import com.pruebatecnica.demo.entity.Usuario;
import com.pruebatecnica.demo.responses.usuario.UsuarioCreateResponse;
import com.pruebatecnica.demo.responses.usuario.UsuarioParqueaderoResponse;
import com.pruebatecnica.demo.responses.usuario.UsuarioUpdateResponse;
import com.pruebatecnica.demo.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
    public ResponseEntity<UsuarioUpdateResponse> updateSocio(@Valid @RequestBody Usuario usuario){
        UsuarioUpdateResponse usuarioUpdateResponse = usuarioService.updateSocio(usuario);

        return ResponseEntity.ok(usuarioUpdateResponse);
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
    public ResponseEntity<UsuarioParqueaderoResponse> asignarSocioToParqueadero(@PathVariable Integer id, @RequestBody Set<ParqueaderoUsuarioDTO> parqueaderos){
        UsuarioParqueaderoResponse usuarioParqueaderoResponse = usuarioService.asignarParqueadero(id,parqueaderos);
        return ResponseEntity.ok(usuarioParqueaderoResponse);
    }

    @GetMapping(value = "{id}/parqueaderos")
    @PreAuthorize("hasAnyRole('ADMIN', 'SOCIO')")
    public Set<Parqueadero> listParqueaderosBySocio(@PathVariable Integer id){
        return usuarioService.listParqueaderoBySocio(id);
    }

}
