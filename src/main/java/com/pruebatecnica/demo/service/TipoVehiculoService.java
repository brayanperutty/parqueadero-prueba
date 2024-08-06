package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.repository.TipoVehiculoRepository;
import org.springframework.stereotype.Service;

@Service
public class TipoVehiculoService {

    public final TipoVehiculoRepository tipoVehiculoRepository;

    public TipoVehiculoService(TipoVehiculoRepository tipoVehiculoRepository) {
        this.tipoVehiculoRepository = tipoVehiculoRepository;
    }
}
