package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.service.HistorialService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/historial")
public class HistorialController {

    public final HistorialService historialService;

    public HistorialController(HistorialService historialService) {
        this.historialService = historialService;
    }
}
