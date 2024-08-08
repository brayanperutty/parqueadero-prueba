package com.pruebatecnica.demo.responses.usuario;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UsuarioParqueaderoDesvinculacionResponse {

    private String mensaje = "Socio desvinculado exitosamente";
}
