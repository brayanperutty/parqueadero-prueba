package com.pruebatecnica.demo.responses.salidavehiculo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class SalidaVehiculoCreateResponse {

    private String mensaje = "Salida registrada";
}
