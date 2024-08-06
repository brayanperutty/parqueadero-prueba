package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.dto.IngresoVehiculoDTO;
import com.pruebatecnica.demo.responses.ingresovehiculo.IngresoVehiculoCreateResponse;
import com.pruebatecnica.demo.service.IngresoVehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingresos")
@RequiredArgsConstructor
public class IngresoVehiculoController {

    public final IngresoVehiculoService ingresoVehiculoService;

    @PostMapping()
    @PreAuthorize("hasRole('SOCIO')")
    public ResponseEntity<IngresoVehiculoCreateResponse> createIngresoVehiculo(@RequestBody IngresoVehiculoDTO ingresoVehiculoDTO){
        IngresoVehiculoCreateResponse ingresoVehiculoCreateResponse = ingresoVehiculoService.createIngresoVehiculo(ingresoVehiculoDTO);
        return ResponseEntity.ok(ingresoVehiculoCreateResponse);
    }
}
