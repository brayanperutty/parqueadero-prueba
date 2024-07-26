package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.service.RolService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rol")
public class RolController {

    public final RolService rolService;

    public RolController(RolService rolService) {
        this.rolService = rolService;
    }
}
