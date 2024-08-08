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

public class IngresoVehiculoDTO {

    @NotNull(message = "El campo del id del parqueadero no debe ser nulo")
    @Min(value = 1, message = "El id del parqueadero debe contener al menos 1 dígito")
    private Integer idParqueadero;

    @NotNull(message = "El campo de la placa del vehículo no debe ser nulo")
    @NotBlank(message = "El campo de la placa del vehículo no debe estar vacío")
    @Size(min = 6, max = 6, message = "La placa del vehículo solo debe contener 6 caracteres")
    @Pattern(regexp = ".", message = "La placa no debe contener caracteres especiales ni la letra ñ")
    private String placaVehiculo;

    @NotNull(message = "El campo de la marca del vehículo no debe ser nulo")
    @NotBlank(message = "El campo de la marca del vehículo no debe estar vacío")
    private String marcaVehiculo;

    @NotNull(message = "El campo del modelo del vehículo no debe ser nulo")
    @NotBlank(message = "El campo del modelo del vehículo no debe estar vacío")
    private String modeloVehiculo;

    @NotNull(message = "El campo del color del vehículo no debe ser nulo")
    @NotBlank(message = "El campo del color del vehículo no debe estar vacío")
    private String colorVehiculo;

    @NotNull(message = "El campo del tipo del vehículo no debe ser nulo")
    @Min(value = 1, message = "El campo del tipo del vehículo debe contener al menos 1 dígito")
    private Integer idTipoVehiculo;
}
