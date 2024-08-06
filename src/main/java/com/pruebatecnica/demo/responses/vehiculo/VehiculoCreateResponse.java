package com.pruebatecnica.demo.responses.vehiculo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class VehiculoCreateResponse {

    private String creado = "Vehículo creado con éxito";
}
