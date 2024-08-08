package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.auth.AuthService;
import com.pruebatecnica.demo.dto.request.ParqueaderoUsuarioDTO;
import com.pruebatecnica.demo.dto.request.UsuarioCreateDTO;
import com.pruebatecnica.demo.entity.Parqueadero;
import com.pruebatecnica.demo.entity.Usuario;
import com.pruebatecnica.demo.repository.IngresoVehiculoRepository;
import com.pruebatecnica.demo.repository.ParqueaderoRepository;
import com.pruebatecnica.demo.repository.UsuarioRepository;
import com.pruebatecnica.demo.responses.usuario.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    public final UsuarioRepository usuarioRepository;
    private final ParqueaderoRepository parqueaderoRepository;
    private final IngresoVehiculoRepository ingresoVehiculoRepository;
    private final AuthService authService;

    private final UsuarioErrorResponses usuarioErrorResponses;
    private final UsuarioCreateResponse usuarioCreateResponse;
    private final UsuarioUpdateResponse usuarioUpdateResponse;
    private final UsuarioParqueaderoVinculacionResponse usuarioParqueaderoVinculacionResponse;
    private final UsuarioParqueaderoDesvinculacionResponse usuarioParqueaderoDesvinculacionResponse;

    public Usuario getUsuario(Integer id){
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException(usuarioErrorResponses.getUsuarioNoEncontrado()));
    }

    public UsuarioCreateResponse createSocio(UsuarioCreateDTO usuarioCreateDTO) throws IllegalArgumentException{
       if(usuarioRepository.validateCedula(usuarioCreateDTO.getCedula())){

            throw new IllegalArgumentException(usuarioErrorResponses.getCedulaRegistrada());

        }else if(usuarioRepository.validateCorreo(usuarioCreateDTO.getUsername())){

           throw new IllegalArgumentException(usuarioErrorResponses.getCorreoRegistrado());

        }else{
            authService.register(usuarioCreateDTO);
            return usuarioCreateResponse;
        }
    }

    public UsuarioUpdateResponse updateSocio(UsuarioCreateDTO usuarioCreateDTO){
        Usuario user = usuarioRepository.findByUsername(usuarioCreateDTO.getUsername()).orElseThrow(() -> new RuntimeException(usuarioErrorResponses.getUsuarioNoEncontrado()));

            user.setNombreCompleto(usuarioCreateDTO.getNombreCompleto());
            user.setCedula(usuarioCreateDTO.getCedula());
            user.setUsername(usuarioCreateDTO.getUsername());
            user.setPassword(usuarioCreateDTO.getPassword());
            usuarioRepository.save(user);
            return usuarioUpdateResponse;
    }

    public void deleteSocio(Integer id){
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> listUsuarios(){
        return usuarioRepository.findAll();
    }

    public UsuarioParqueaderoVinculacionResponse asignarParqueadero(Integer id, ParqueaderoUsuarioDTO parqueaderoUsuarioDTO){
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException(usuarioErrorResponses.getUsuarioNoEncontrado()));

        parqueaderoUsuarioDTO.getId().forEach(parqueaderoId -> {
            Parqueadero parqueadero = parqueaderoRepository.findById(parqueaderoId).orElseThrow(() -> new RuntimeException("Parqueadero " + parqueaderoId +
                    "no encontrado"));

            usuario.getParqueaderos().add(parqueadero);
            parqueadero.getSocios().add(usuario);

            usuarioRepository.save(usuario);
            parqueaderoRepository.save(parqueadero);
        });
            return usuarioParqueaderoVinculacionResponse;
    }

    public UsuarioParqueaderoDesvinculacionResponse desvincularParqueadero(Integer id, ParqueaderoUsuarioDTO parqueaderoUsuarioDTO){

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException(usuarioErrorResponses.getUsuarioNoEncontrado()));

        parqueaderoUsuarioDTO.getId().forEach(parqueaderoId -> {
            if(ingresoVehiculoRepository.validateSocioWithParqueadero(parqueaderoId, usuario.getId())){
                Parqueadero parqueadero = parqueaderoRepository.findById(parqueaderoId).orElseThrow(() -> new RuntimeException("Parqueadero " + parqueaderoId +
                        "no encontrado"));
                usuario.getParqueaderos().remove(parqueadero);
                parqueadero.getSocios().remove(usuario);

                usuarioRepository.save(usuario);
                parqueaderoRepository.save(parqueadero);
            }else{
                throw new IllegalArgumentException("Este socio no corresponde al parqueadero " + parqueaderoId);
            }
        });
        return usuarioParqueaderoDesvinculacionResponse;
    }
}
