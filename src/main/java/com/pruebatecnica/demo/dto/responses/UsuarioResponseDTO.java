package com.pruebatecnica.demo.dto.responses;

import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Set;

@Data
@Component
public class UsuarioResponseDTO {

    private Integer id;

    private String nombreCompleto;

    private String username;

    private Set<ParqueaderoSocioDTO> parqueaderos;
}
