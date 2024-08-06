package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.entity.Vehiculo;
import com.pruebatecnica.demo.service.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/vehiculos")
@RequiredArgsConstructor
public class VehiculoController {

    public final VehiculoService vehiculoService;

    Map<String, Object> response = new HashMap<>();

    @GetMapping(value = "/list/{idParqueadero}")
    @PreAuthorize("hasAnyRole('ADMIN', 'SOCIO')")
    public Set<Vehiculo> listVehiculoByParqueadero(@PathVariable Integer idParqueadero){
        return vehiculoService.listVehiculoByParqueadero(idParqueadero);
    }

}
