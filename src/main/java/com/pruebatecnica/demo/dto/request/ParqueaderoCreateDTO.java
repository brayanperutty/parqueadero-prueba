package com.pruebatecnica.demo.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParqueaderoCreateDTO {

    @NotNull(message = "El campo del nombre del parqueadero no debe ser nulo")
    @NotBlank(message = "El campo del nombre del parqueadero no debe estar vacío")
    @Size(min = 1, max = 255, message = "El nombre de parqueadero es muy extenso")
    private String nombre;

    @NotNull(message = "El campo de la capacidad del parqueadero no debe ser nulo")
    @Min(value = 1, message = "La capacidad del parqueadero debe ser mínimo 1")
    @Pattern(regexp = "\\d", message = "El campo de la capacidad del parqueadero solo debe ser números")
    private Integer capacidad;

    @NotNull(message = "El campo del costo por hora para carro no debe ser nulo")
    @Min(value = 1, message = "El costo por hora para carro debe ser mínimo 1")
    @Pattern(regexp = "\\d", message = "El campo de costo por hora para carro solo debe ser números")
    private Integer costoHoraCarro;

    @NotNull(message = "El campo del costo por hora para moto no debe ser nulo")
    @Min(value = 1, message = "El costo por hora para moto debe ser mínimo 1")
    @Pattern(regexp = "\\d", message = "El campo de costo por hora para moto solo debe ser números")
    private Integer costoHoraMoto;
}
