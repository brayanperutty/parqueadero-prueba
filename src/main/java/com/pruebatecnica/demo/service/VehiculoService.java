package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.auth.RegisterIngresoRequest;
import com.pruebatecnica.demo.entity.TipoVehiculo;
import com.pruebatecnica.demo.entity.Vehiculo;
import com.pruebatecnica.demo.repository.TipoVehiculoRepository;
import com.pruebatecnica.demo.repository.VehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final TipoVehiculoRepository tipoVehiculoRepository;

    public void createVehiculo(RegisterIngresoRequest registerIngresoRequest){
        Optional<TipoVehiculo> tipo = tipoVehiculoRepository.findById(registerIngresoRequest.getIdTipo());
        Vehiculo v = Vehiculo.builder()
                .placa(registerIngresoRequest.getPlacaVehiculo())
                .marca(registerIngresoRequest.getMarca())
                .modelo(registerIngresoRequest.getModelo())
                .color(registerIngresoRequest.getColor())
                .tipo(tipo.get())
                .build();
        vehiculoRepository.save(v);
    }

    public List<Object[]> listVehiculoByParqueadero(Integer idParqueadero){
        return vehiculoRepository.listVehiculoByParqueadero(idParqueadero);
    }

}
