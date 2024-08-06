package com.pruebatecnica.demo.responses.usuario;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UsuarioParqueaderoResponse {

    private String mensaje = "Socio asignado exitosamente";
}
