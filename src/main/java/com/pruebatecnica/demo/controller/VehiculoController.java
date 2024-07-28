package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.service.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehiculo")
@RequiredArgsConstructor
public class VehiculoController {

    public final VehiculoService vehiculoService;

}
