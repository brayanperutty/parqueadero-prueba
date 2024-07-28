package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.repository.VehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculoService {

    public final VehiculoRepository vehiculoRepository;

    public List<Object[]> listVehiculoByParqueadero(Integer idParqueadero){
        return vehiculoRepository.listVehiculoByParqueadero(idParqueadero);
    }

}
