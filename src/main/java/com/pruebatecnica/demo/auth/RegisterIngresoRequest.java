package com.pruebatecnica.demo.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterIngresoRequest {
    private Integer idParqueadero;
    private String placaVehiculo;

    private String cedula;
    private String color;
    private String marca;
    private String modelo;
    private Integer idTipo;
}
