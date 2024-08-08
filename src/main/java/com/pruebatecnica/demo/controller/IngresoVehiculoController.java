package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.dto.request.IngresoVehiculoDTO;
import com.pruebatecnica.demo.responses.ingresovehiculo.IngresoVehiculoCreateResponse;
import com.pruebatecnica.demo.service.IngresoVehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingresos")
@RequiredArgsConstructor
public class IngresoVehiculoController {

    public final IngresoVehiculoService ingresoVehiculoService;

    @PostMapping()
    @PreAuthorize("hasRole('SOCIO')")
    @ResponseStatus(HttpStatus.OK)
    public IngresoVehiculoCreateResponse createIngresoVehiculo(@RequestBody IngresoVehiculoDTO ingresoVehiculoDTO){
        return ingresoVehiculoService.createIngresoVehiculo(ingresoVehiculoDTO);
    }
}
