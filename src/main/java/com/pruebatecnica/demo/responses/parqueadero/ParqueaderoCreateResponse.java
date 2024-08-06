package com.pruebatecnica.demo.responses.parqueadero;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ParqueaderoCreateResponse {

    private String mensaje = "Parqueadero creado con éxito";
}
