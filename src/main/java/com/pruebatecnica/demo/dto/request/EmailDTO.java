package com.pruebatecnica.demo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailDTO {

    @NotNull(message = "El campo del correo no debe ser nulo")
    @NotBlank(message = "El campo del correo no debe estar vacío")
    private String email;

    @NotNull(message = "El campo de la placa del vehículo no debe ser nulo")
    @NotBlank(message = "El campo de la placa del vehículo no debe estar vacío")
    @Size(min = 6, max = 6, message = "La placa del vehículo solo debe contener 6 caracteres")
    @Pattern(regexp = ".", message = "La placa no debe contener caracteres especiales ni la letra ñ")
    private String placa;

    @NotNull(message = "El campo del mensaje no debe ser nulo")
    @NotBlank(message = "El campo del mensaje no debe estar vacío")
    private String mensaje;

    @NotNull(message = "El campo del nombre del parqueadero no debe ser nulo")
    @NotBlank(message = "El campo del nombre del parqueadero no debe estar vacío")
    private String parqueaderoNombre;
}
