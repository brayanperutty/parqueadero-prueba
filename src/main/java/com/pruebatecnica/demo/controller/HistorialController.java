package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.service.HistorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/historial")
@RequiredArgsConstructor
public class HistorialController {

    public final HistorialService historialService;
}
