package com.pruebatecnica.demo.responses.usuario;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UsuarioCreateResponse {

    private String mensaje = "Socio creado con éxito";

}
