package com.pruebatecnica.demo.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SalidaVehiculoDTO {

    @NotNull(message = "El campo de la placa del vehículo no debe ser nulo")
    @NotBlank(message = "El campo de la placa del vehículo no debe estar vacío")
    @Size(min = 6, max = 6, message = "La placa del vehículo solo debe contener 6 caracteres")
    @Pattern(regexp = ".", message = "La placa no debe contener caracteres especiales ni la letra ñ")
    private String placaVehiculo;

    @NotNull(message = "El campo de id del parqueadero no debe ser nulo")
    @Min(value = 1, message = "El id del parqueadero debe contener al menos 1 dígito")
    private Integer idParqueadero;
}
