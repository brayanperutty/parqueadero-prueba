package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.repository.VehiculoRepository;
import org.springframework.stereotype.Service;

@Service
public class VehiculoService {

    public final VehiculoRepository vehiculoRepository;

    public VehiculoService(VehiculoRepository vehiculoRepository) {
        this.vehiculoRepository = vehiculoRepository;
    }
}
