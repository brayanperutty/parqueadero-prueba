package com.pruebatecnica.demo.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParqueaderoUsuarioDTO {

    @NotNull(message = "Parqueadero no debe ser nulo")
    @NotBlank(message = "Parqueadero no debe estar vacío")
    @Min(value = 1, message = "El id del parqueadero contener al menos 1 dígito")
    private Set<Integer> id;
}
