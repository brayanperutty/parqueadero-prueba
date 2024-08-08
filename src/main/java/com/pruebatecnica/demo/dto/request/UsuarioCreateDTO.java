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
public class UsuarioCreateDTO {

    private static final String REGEX_NOMBRE = "^[A-Za-z\\s]*$";

    @NotNull(message = "El campo cédula no debe ser nulo")
    @NotBlank(message = "El campo cédula no debe estar vacío")
    @Pattern(regexp = "^\\d+$", message = "La cédula solo debe contener números")
    @Size(min = 6, max = 10, message = "La cédula no debe contener menos de 6 digitos ni más de 10")
    private String cedula;

    @NotNull(message = "El campo nombre no debe ser nulo")
    @NotBlank(message = "El campo nombre no debe estar vacío")
    @Pattern(regexp = REGEX_NOMBRE, message = "El campo debe contener solo letras y espacios.")
    @Size(max = 255, message = "Nombre de usuario demasiado extenso")
    private String nombreCompleto;

    @NotNull(message = "El campo username o correo no debe ser nulo")
    @NotBlank(message = "El campo username o correo no debe estar vacío")
    @Size(max = 255, message = "Correo demasiado extenso")
    private String username;

    @NotNull(message = "El campo password no debe ser nulo")
    @NotBlank(message = "El campo password no debe estar vacío")
    @Size(max = 100, message = "Password demasiado extensa")
    private String password;
}
