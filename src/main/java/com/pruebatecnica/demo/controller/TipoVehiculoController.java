package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.service.TipoVehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tipo-vehiculo")
@RequiredArgsConstructor
public class TipoVehiculoController {

    public final TipoVehiculoService tipoVehiculoService;
}
