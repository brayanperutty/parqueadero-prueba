package com.pruebatecnica.demo.responses.vehiculo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class VehiculoErrorResponses {

    private String noEncontrado = "Vehículo no encontrado";

}
