package com.pruebatecnica.demo.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterSalidaRequest {

    private String placaVehiculo;
    private Integer idParqueadero;
    private String cedula;
}
