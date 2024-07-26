package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.repository.IngresoVehiculoRepository;
import org.springframework.stereotype.Service;

@Service
public class IngresoVehiculoService {

    public final IngresoVehiculoRepository ingresoVehiculoRepository;

    public IngresoVehiculoService(IngresoVehiculoRepository ingresoVehiculoRepository) {
        this.ingresoVehiculoRepository = ingresoVehiculoRepository;
    }
}
