package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.entity.Parqueadero;
import com.pruebatecnica.demo.responses.parqueadero.ParqueaderoCreateResponse;
import com.pruebatecnica.demo.service.ParqueaderoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parqueaderos")
@RequiredArgsConstructor
public class ParqueaderoController {

    public final ParqueaderoService parqueaderoService;

    private static final String MATCHING_RESPONSE = "Parqueadero";

    @GetMapping(value = "{idParqueadero}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Parqueadero> getParqueadero(@PathVariable Integer idParqueadero){
        return ResponseEntity.ok(parqueaderoService.getParqueadero(idParqueadero));
    }

    @PostMapping(value = "")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ParqueaderoCreateResponse> createParqueadero(@RequestBody Parqueadero parqueadero){
        ParqueaderoCreateResponse parqueaderoCreateResponse = parqueaderoService.createParqueadero(parqueadero);
            return ResponseEntity.ok(parqueaderoCreateResponse);
    }

    @PutMapping(value = "")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateParqueadero(@RequestBody Parqueadero parqueadero){
        String mensaje = parqueaderoService.updateParqueadero(parqueadero);
        if(mensaje.equals(MATCHING_RESPONSE)){
            return ResponseEntity.ok(mensaje);
        }else{
            return ResponseEntity.badRequest().body(mensaje);
        }
    }

    @DeleteMapping(value = "{idParqueadero}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteParqueadero(@PathVariable Integer idParqueadero){
        parqueaderoService.deleteParqueadero(idParqueadero);
    }


}
