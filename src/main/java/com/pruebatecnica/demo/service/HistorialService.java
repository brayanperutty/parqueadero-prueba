package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.repository.HistorialRepository;
import org.springframework.stereotype.Service;

@Service
public class HistorialService {

    public final HistorialRepository historialRepository;

    public HistorialService(HistorialRepository historialRepository) {
        this.historialRepository = historialRepository;
    }
}
