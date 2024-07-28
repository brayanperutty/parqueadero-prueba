package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.service.IngresoVehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ingreso")
@RequiredArgsConstructor
public class IngresoVehiculoController {

    public final IngresoVehiculoService ingresoVehiculoService;
}
