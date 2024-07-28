package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.service.VehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/vehiculo")
@RequiredArgsConstructor
public class VehiculoController {

    public final VehiculoService vehiculoService;

    Map<String, Object> response = new HashMap<>();

    @GetMapping(value = "/list/{idParqueadero}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> listVehiculoByParqueadero(@PathVariable Integer idParqueadero){
        response.clear();
        List<Object[]> vehiculos = vehiculoService.listVehiculoByParqueadero(idParqueadero);
        if(vehiculos.isEmpty()){
            response.put("mensaje", "No hay vehículos actualmente en este parqueadero");
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.ok(vehiculoService.listVehiculoByParqueadero(idParqueadero));
        }
    }

}
