package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.entity.IngresoVehiculo;
import com.pruebatecnica.demo.entity.Parqueadero;
import com.pruebatecnica.demo.entity.TipoVehiculo;
import com.pruebatecnica.demo.entity.Vehiculo;
import com.pruebatecnica.demo.repository.ParqueaderoRepository;
import com.pruebatecnica.demo.repository.TipoVehiculoRepository;
import com.pruebatecnica.demo.repository.VehiculoRepository;
import com.pruebatecnica.demo.responses.parqueadero.ParqueaderoErrorResponses;
import com.pruebatecnica.demo.responses.tipovehiculo.TipoVehiculoResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final TipoVehiculoRepository tipoVehiculoRepository;
    private final ParqueaderoRepository parqueaderoRepository;

    private final TipoVehiculoResponses tipoVehiculoResponses;
    private final ParqueaderoErrorResponses parqueaderoErrorResponses;

    public void createVehiculo(String placa, String marca, String modelo, String color, Integer idTipo){
        TipoVehiculo tipoVehiculo = tipoVehiculoRepository.findById(idTipo).orElseThrow(() -> new RuntimeException(tipoVehiculoResponses.getNoEncontrado()));
        Vehiculo v = Vehiculo.builder()
                .placa(placa)
                .marca(marca)
                .modelo(modelo)
                .color(color)
                .tipo(tipoVehiculo)
                .build();
        vehiculoRepository.save(v);
    }

    public Set<Vehiculo> listVehiculoByParqueadero(Integer parqueaderoId) {
        Parqueadero parqueadero = parqueaderoRepository.findById(parqueaderoId).orElseThrow(() -> new RuntimeException(parqueaderoErrorResponses.getNoEncontrado()));

        return parqueadero.getIngresos().stream()
                    .map(IngresoVehiculo::getVehiculo)
                    .collect(Collectors.toSet());

    }

}
