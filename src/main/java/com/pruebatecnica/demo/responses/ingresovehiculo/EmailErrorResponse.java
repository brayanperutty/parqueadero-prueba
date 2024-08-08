package com.pruebatecnica.demo.responses.ingresovehiculo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class EmailErrorResponse {

    private String mensaje = "Error al enviar el correo";
}
