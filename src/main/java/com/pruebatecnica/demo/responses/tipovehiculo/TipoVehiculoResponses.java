package com.pruebatecnica.demo.responses.tipovehiculo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class TipoVehiculoResponses {

    private String noEncontrado = "Tipo de vehículo no encontrado";
}
