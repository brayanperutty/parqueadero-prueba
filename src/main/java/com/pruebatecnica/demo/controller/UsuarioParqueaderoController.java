package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.service.UsuarioParqueaderoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/socio")
public class UsuarioParqueaderoController {

    public final UsuarioParqueaderoService usuarioParqueaderoService;

    public UsuarioParqueaderoController(UsuarioParqueaderoService usuarioParqueaderoService) {
        this.usuarioParqueaderoService = usuarioParqueaderoService;
    }
}
