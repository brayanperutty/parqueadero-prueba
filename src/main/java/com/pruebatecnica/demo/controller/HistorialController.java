package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.dto.request.SalidaVehiculoDTO;
import com.pruebatecnica.demo.responses.salidavehiculo.SalidaVehiculoCreateResponse;
import com.pruebatecnica.demo.service.HistorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/historiales")
@RequiredArgsConstructor
public class HistorialController {

    public final HistorialService historialService;

    @PostMapping()
    @PreAuthorize("hasRole('SOCIO')")
    @ResponseStatus(HttpStatus.OK)
    public SalidaVehiculoCreateResponse createSalidaVehiculo(@RequestBody SalidaVehiculoDTO salidaVehiculoDTO){
        System.out.println("controller");
        return historialService.createSalidaVehiculo(salidaVehiculoDTO);
    }
}
