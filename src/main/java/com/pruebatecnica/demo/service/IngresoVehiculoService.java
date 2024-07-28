package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.entity.Vehiculo;
import com.pruebatecnica.demo.repository.IngresoVehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IngresoVehiculoService {

    public final IngresoVehiculoRepository ingresoVehiculoRepository;

}
