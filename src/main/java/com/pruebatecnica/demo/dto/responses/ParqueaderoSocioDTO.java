package com.pruebatecnica.demo.dto.responses;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ParqueaderoSocioDTO {

    private Integer id;

    private String nombreParqueadero;
}
