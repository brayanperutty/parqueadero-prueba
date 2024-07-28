package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.auth.AuthService;
import com.pruebatecnica.demo.auth.RegisterSocioRequest;
import com.pruebatecnica.demo.entity.Parqueadero;
import com.pruebatecnica.demo.entity.Usuario;
import com.pruebatecnica.demo.repository.ParqueaderoRepository;
import com.pruebatecnica.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    public final UsuarioRepository usuarioRepository;
    private final ParqueaderoRepository parqueaderoRepository;
    private final AuthService authService;

    public Optional<Usuario> getUsuario(String cedula){
        return usuarioRepository.findById(cedula);
    }

    public String createSocio(RegisterSocioRequest socio){
        if(socio.getCedula().length() < 6 || socio.getCedula().length() > 10){
            return "La cédula no debe tener menos de 6 dígitos o más de 10";
        }else if(!socio.getCedula().matches("^[0-9]+$") || socio.getCedula().trim().isEmpty()){
            return "La cédula solo debe contener números, o no debe estar vacío o nulo este campo";
        }else if(socio.getNombreCompleto().matches(".*\\d.*") || socio.getNombreCompleto().trim().isEmpty()){
            return "El nombre no debe contener números, o no debe estar vacío o nulo este campo";
        }else if(socio.getUsername() == null || socio.getUsername().trim().isEmpty()){
            return "No debe estar vacío o nulo el campo del correo";
        }else if(socio.getPassword() == null || socio.getPassword().trim().isEmpty()){
            return "No debe estar vacío o nulo el campo de la contraseña";
        }else if(socio.getRol() == null || socio.getRol().toString().trim().isEmpty()){
            return "No debe estar vacío o nulo el campo del rol a asignar";
        }else if(usuarioRepository.validateCedula(socio.getCedula())){
            return "Cédula ya registrada anteriormente";
        }else if(usuarioRepository.validateCorreo(socio.getUsername())){
            return "Correo ya registrado anteriormente";
        }else{
            authService.register(socio);
            return "Socio creado con éxito";
        }
    }

    public String updateSocio(Usuario usuario){
        Optional<Usuario> user = usuarioRepository.findById(usuario.getCedula());
        if(user.isPresent()){
            user.get().setNombreCompleto(usuario.getNombreCompleto());
            user.get().setCedula(usuario.getCedula());
            user.get().setParqueaderos(usuario.getParqueaderos());
            user.get().setUsername(usuario.getUsername());
            user.get().setPassword(usuario.getPassword());
            user.get().setRol(usuario.getRol());

            return "Socio actualizado con éxito";
        }else {
            return "Error al actualizar este usuario";
        }
    }


    public void deleteSocio(String cedula){
        usuarioRepository.deleteById(cedula);
    }

    public List<Usuario> listUsuarios(){
        return usuarioRepository.findAll();
    }

    public String asignarParqueadero(String cedula, Integer idParqueadero){
        Optional<Usuario> usuario = usuarioRepository.findById(cedula);
        Optional<Parqueadero> parqueadero = parqueaderoRepository.findById(idParqueadero);

        if(usuario.isPresent() && parqueadero.isPresent()){
            Usuario u = usuario.get();
            Parqueadero p = parqueadero.get();

            u.getParqueaderos().add(p);
            p.getSocios().add(u);

            usuarioRepository.save(u);
            parqueaderoRepository.save(p);

            return "Socio asignado exitosamente";
        }else{
            return "Parqueadero o socio no encontrado";
        }
    }

    public Set<Parqueadero> listParqueaderoBySocio(String cedula){
        Optional<Usuario> usuario = usuarioRepository.findById(cedula);

        if(usuario.isPresent()){
            return usuario.get().getParqueaderos();
        }
        return null;
    }

}
