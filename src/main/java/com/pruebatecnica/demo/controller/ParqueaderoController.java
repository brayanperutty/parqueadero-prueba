package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.service.ParqueaderoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/parqueadero")
public class ParqueaderoController {

    public final ParqueaderoService parqueaderoService;

    public ParqueaderoController(ParqueaderoService parqueaderoService) {
        this.parqueaderoService = parqueaderoService;
    }
}
