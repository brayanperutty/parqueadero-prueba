package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.auth.AuthService;
import com.pruebatecnica.demo.dto.ParqueaderoUsuarioDTO;
import com.pruebatecnica.demo.dto.UsuarioCreateDTO;
import com.pruebatecnica.demo.entity.Parqueadero;
import com.pruebatecnica.demo.entity.Usuario;
import com.pruebatecnica.demo.repository.ParqueaderoRepository;
import com.pruebatecnica.demo.repository.RolRepository;
import com.pruebatecnica.demo.repository.UsuarioRepository;
import com.pruebatecnica.demo.responses.usuario.UsuarioCreateResponse;
import com.pruebatecnica.demo.responses.usuario.UsuarioErrorResponses;
import com.pruebatecnica.demo.responses.usuario.UsuarioParqueaderoResponse;
import com.pruebatecnica.demo.responses.usuario.UsuarioUpdateResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    public final UsuarioRepository usuarioRepository;
    private final ParqueaderoRepository parqueaderoRepository;
    private final RolRepository rolRepository;
    private final AuthService authService;

    private final UsuarioErrorResponses usuarioErrorResponses;
    private final UsuarioCreateResponse usuarioCreateResponse;
    private final UsuarioUpdateResponse usuarioUpdateResponse;
    private final UsuarioParqueaderoResponse usuarioParqueaderoResponse;

    public Usuario getUsuario(Integer id){
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException(usuarioErrorResponses.getUsuarioNoEncontrado()));
    }

    public UsuarioCreateResponse createSocio(UsuarioCreateDTO usuarioCreateDTO) throws IllegalArgumentException{
       if(usuarioRepository.validateCedula(usuarioCreateDTO.getCedula())){

            throw new IllegalArgumentException(usuarioErrorResponses.getCedulaRegistrada());

        }else if(usuarioRepository.validateCorreo(usuarioCreateDTO.getUsername())){

           throw new IllegalArgumentException(usuarioErrorResponses.getCorreoRegistrado());

        }else if(!rolRepository.validateRol(usuarioCreateDTO.getRol())){

           throw new IllegalArgumentException(usuarioErrorResponses.getRolNoEncontrado());
       }else{
            authService.register(usuarioCreateDTO);
            return usuarioCreateResponse;
        }
    }

    public UsuarioUpdateResponse updateSocio(Usuario usuario){
        Usuario user = usuarioRepository.findById(usuario.getId()).orElseThrow(() -> new RuntimeException(usuarioErrorResponses.getUsuarioNoEncontrado()));

            user.setNombreCompleto(usuario.getNombreCompleto());
            user.setCedula(usuario.getCedula());
            user.setParqueaderos(usuario.getParqueaderos());
            user.setUsername(usuario.getUsername());
            user.setPassword(usuario.getPassword());
            user.setRol(usuario.getRol());
            usuarioRepository.save(user);

            return usuarioUpdateResponse;
    }

    public void deleteSocio(Integer id){
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> listUsuarios(){
        return usuarioRepository.findAll();
    }

    public UsuarioParqueaderoResponse asignarParqueadero(Integer id, Set<ParqueaderoUsuarioDTO> parqueaderos){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException(usuarioErrorResponses.getUsuarioNoEncontrado()));

        parqueaderos.forEach(p -> {
            Parqueadero parqueadero = parqueaderoRepository.findById(p.getId()).orElseThrow(() -> new RuntimeException("Parqueadero " + p.getId() +
                    "no encontrado"));

            usuario.getParqueaderos().add(parqueadero);
            parqueadero.getSocios().add(usuario);

            usuarioRepository.save(usuario);
            parqueaderoRepository.save(parqueadero);
        });

            return usuarioParqueaderoResponse;
    }

    public Set<Parqueadero> listParqueaderoBySocio(Integer id){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException(usuarioErrorResponses.getUsuarioNoEncontrado()));

        return usuario.getParqueaderos();
    }

}
