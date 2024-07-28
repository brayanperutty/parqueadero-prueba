package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.auth.RegisterIngresoRequest;
import com.pruebatecnica.demo.auth.RegisterSalidaRequest;
import com.pruebatecnica.demo.service.HistorialService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/historial")
@RequiredArgsConstructor
public class HistorialController {

    public final HistorialService historialService;

    Map<String, Object> response = new HashMap<>();

    @PostMapping(value = "register")
    @PreAuthorize("hasRole('SOCIO')")
    public ResponseEntity<?> createSalidaVehiculo(@RequestBody RegisterSalidaRequest registerSalidaRequest){
        response.clear();
        String mensaje = historialService.createSalidaVehiculo(registerSalidaRequest);
        response.put("mensaje", mensaje);
        if(mensaje.startsWith("El") || mensaje.startsWith("No")){
            return ResponseEntity.badRequest().body(response);
        }else{
            return ResponseEntity.ok(response);
        }
    }
}
