package com.pruebatecnica.demo.responses.salidavehiculo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class SalidaVehiculoErrorResponses {

    private String errorSalida = "No se puede registrar salida, no existe la placa de este vehículo en este parqueadero";

    private String parqueaderoError = "El parqueadero no corresponde al socio ingresado";


}
