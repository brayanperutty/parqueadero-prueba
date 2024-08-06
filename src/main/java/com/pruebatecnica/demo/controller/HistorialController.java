package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.dto.SalidaVehiculoDTO;
import com.pruebatecnica.demo.responses.salidavehiculo.SalidaVehiculoCreateResponse;
import com.pruebatecnica.demo.service.HistorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/historiales")
@RequiredArgsConstructor
public class HistorialController {

    public final HistorialService historialService;

    @PostMapping()
    @PreAuthorize("hasRole('SOCIO')")
    public ResponseEntity<SalidaVehiculoCreateResponse> createSalidaVehiculo(@RequestBody SalidaVehiculoDTO salidaVehiculoDTO){
        SalidaVehiculoCreateResponse salidaVehiculoCreateResponse = historialService.createSalidaVehiculo(salidaVehiculoDTO);
        return ResponseEntity.ok(salidaVehiculoCreateResponse);
    }
}
