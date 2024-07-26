package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.service.HistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/historial")
public class HIstorialController {

    public final HistorialService historialService;

    public HIstorialController(HistorialService historialService) {
        this.historialService = historialService;
    }
}
