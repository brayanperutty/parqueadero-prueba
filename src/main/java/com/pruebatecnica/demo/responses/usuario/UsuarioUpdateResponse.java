package com.pruebatecnica.demo.responses.usuario;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class UsuarioUpdateResponse {

    private String mensaje = "Usuario actualizado con éxito";
}
