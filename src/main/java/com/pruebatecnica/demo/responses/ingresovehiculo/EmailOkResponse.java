package com.pruebatecnica.demo.responses.ingresovehiculo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class EmailOkResponse {

    private String mensaje = "Correo enviado";
}
