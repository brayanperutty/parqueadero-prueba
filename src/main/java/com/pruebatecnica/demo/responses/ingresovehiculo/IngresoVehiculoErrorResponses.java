package com.pruebatecnica.demo.responses.ingresovehiculo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class IngresoVehiculoErrorResponses {

    private String errorIngreso = "No se puede registrar ingreso, ya existe la placa en este u otro parqueadero";

    private String parqueaderoError = "El parqueadero no corresponde al socio ingresado";
}
