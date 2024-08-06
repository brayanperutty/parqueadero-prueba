package com.pruebatecnica.demo.responses.usuario;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UsuarioErrorResponses {

    private String usuarioNoEncontrado = "Usuario no encontrado";

    private String cedulaRegistrada = "Cédula ya registrada";

    private String correoRegistrado = "Correo ya registrado";

    private String rolNoEncontrado = "Rol no encontrado";

}
