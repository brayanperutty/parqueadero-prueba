package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.service.IngresoVehiculoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingreso")
public class IngresoVehiculoController {

    public final IngresoVehiculoService ingresoVehiculoService;

    public IngresoVehiculoController(IngresoVehiculoService ingresoVehiculoService) {
        this.ingresoVehiculoService = ingresoVehiculoService;
    }
}
