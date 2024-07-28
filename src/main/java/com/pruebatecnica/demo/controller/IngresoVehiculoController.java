package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.auth.RegisterIngresoRequest;
import com.pruebatecnica.demo.service.IngresoVehiculoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ingreso")
@RequiredArgsConstructor
public class IngresoVehiculoController {

    public final IngresoVehiculoService ingresoVehiculoService;

    Map<String, Object> response = new HashMap<>();

    @PostMapping(value = "register")
    @PreAuthorize("hasRole('SOCIO')")
    public ResponseEntity<?> createIngresoVehiculo(@RequestBody RegisterIngresoRequest registerIngresoRequest){
        response.clear();
        String mensaje = ingresoVehiculoService.createIngresoVehiculo(registerIngresoRequest);
        if(mensaje.startsWith("El") || mensaje.startsWith("No") || mensaje.startsWith("La")){
            response.put("mensaje", mensaje);
            return ResponseEntity.badRequest().body(response);
        }else{
            response.put("id", Integer.parseInt(mensaje));
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
    }
}
