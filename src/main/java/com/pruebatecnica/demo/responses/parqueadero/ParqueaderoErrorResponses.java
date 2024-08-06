package com.pruebatecnica.demo.responses.parqueadero;


import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ParqueaderoErrorResponses {

    private String noEncontrado = "Parqueadero no encontrado";

    private String registradoAnteriormente = "Este nombre de parqueadero ya existe registrado";

    private String errorCreateParqueadero = "Error al actualizar el parqueadero";
}
