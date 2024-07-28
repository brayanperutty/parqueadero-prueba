package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.entity.Parqueadero;
import com.pruebatecnica.demo.service.ParqueaderoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/parqueadero")
@RequiredArgsConstructor
public class ParqueaderoController {

    public final ParqueaderoService parqueaderoService;

    Map<String, Object> response = new HashMap<>();

    @GetMapping(value = "{idParqueadero}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getParqueadero(@PathVariable Integer idParqueadero){
        return ResponseEntity.ok(parqueaderoService.getParqueadero(idParqueadero));
    }

    @PostMapping(value = "register")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createParqueadero(@RequestBody Parqueadero parqueadero){
        response.clear();
        String mensaje = parqueaderoService.createParqueadero(parqueadero);
        response.put("mensaje", mensaje);
        if(mensaje.startsWith("Parqueadero")){
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping(value = "update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateParqueadero(@RequestBody Parqueadero parqueadero){
        response.clear();
        String mensaje = parqueaderoService.updateParqueadero(parqueadero);
        response.put("mensaje", mensaje);
        if(mensaje.startsWith("Parqueadero")){
            return ResponseEntity.ok(response);
        }else{
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping(value = "{idParqueadero}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteParqueadero(@PathVariable Integer idParqueadero){
        parqueaderoService.deleteParqueadero(idParqueadero);
    }


}
