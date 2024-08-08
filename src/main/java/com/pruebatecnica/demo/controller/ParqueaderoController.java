package com.pruebatecnica.demo.controller;

import com.pruebatecnica.demo.entity.Parqueadero;
import com.pruebatecnica.demo.responses.parqueadero.ParqueaderoCreateResponse;
import com.pruebatecnica.demo.service.ParqueaderoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parqueaderos")
@RequiredArgsConstructor
public class ParqueaderoController {

    public final ParqueaderoService parqueaderoService;

    @GetMapping(value = "{idParqueadero}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public Parqueadero getParqueadero(@PathVariable Integer idParqueadero){
        return parqueaderoService.getParqueadero(idParqueadero);
    }

    @PostMapping(value = "")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public ParqueaderoCreateResponse createParqueadero(@RequestBody Parqueadero parqueadero){
            return parqueaderoService.createParqueadero(parqueadero);
    }

    @PutMapping(value = "")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public String updateParqueadero(@RequestBody Parqueadero parqueadero){
        return parqueaderoService.updateParqueadero(parqueadero);
    }

    @DeleteMapping(value = "{idParqueadero}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public void deleteParqueadero(@PathVariable Integer idParqueadero){
        parqueaderoService.deleteParqueadero(idParqueadero);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public List<Parqueadero> listParqueadero(){
        return parqueaderoService.listParqueadero();
    }

}
