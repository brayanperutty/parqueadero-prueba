package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.auth.AuthService;
import com.pruebatecnica.demo.auth.RegisterSocioRequest;
import com.pruebatecnica.demo.entity.Token;
import com.pruebatecnica.demo.entity.Usuario;
import com.pruebatecnica.demo.repository.TokenRepository;
import com.pruebatecnica.demo.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    public final UsuarioRepository usuarioRepository;
    private final AuthService authService;

    public String crearSocio(RegisterSocioRequest socio){
        if(socio.getCedula().length() < 6 || socio.getCedula().length() > 10){
            return "La cédula no debe tener menos de 6 dígitos o más de 10";
        }else if(!socio.getCedula().matches("^[0-9]+$") || socio.getCedula() == null || socio.getCedula().trim().isEmpty()){
            return "La cédula solo debe contener números, o no debe estar vacío o nulo este campo";
        }else if(socio.getNombreCompleto().matches(".*\\d.*") || socio.getNombreCompleto() == null || socio.getNombreCompleto().trim().isEmpty()){
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

    public List<Usuario> listUsuarios(){
        return usuarioRepository.findAll();
    }

}
