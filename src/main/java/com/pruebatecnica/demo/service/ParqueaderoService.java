package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.repository.ParqueaderoRepository;
import org.springframework.stereotype.Service;

@Service
public class ParqueaderoService {

    public final ParqueaderoRepository parqueaderoRepository;

    public ParqueaderoService(ParqueaderoRepository parqueaderoRepository) {
        this.parqueaderoRepository = parqueaderoRepository;
    }
}
