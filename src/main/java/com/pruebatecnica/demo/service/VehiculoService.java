package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.auth.RegisterIngresoRequest;
import com.pruebatecnica.demo.entity.IngresoVehiculo;
import com.pruebatecnica.demo.entity.Parqueadero;
import com.pruebatecnica.demo.entity.TipoVehiculo;
import com.pruebatecnica.demo.entity.Vehiculo;
import com.pruebatecnica.demo.repository.ParqueaderoRepository;
import com.pruebatecnica.demo.repository.TipoVehiculoRepository;
import com.pruebatecnica.demo.repository.VehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VehiculoService {

    private final VehiculoRepository vehiculoRepository;
    private final TipoVehiculoRepository tipoVehiculoRepository;

    private final ParqueaderoRepository parqueaderoRepository;

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

    public Set<Vehiculo> listVehiculoByParqueadero(Integer parqueaderoId) {
        Parqueadero parqueadero = parqueaderoRepository.findById(parqueaderoId).orElse(null);
        if (parqueadero != null) {
            return parqueadero.getIngresos().stream()
                    .map(IngresoVehiculo::getVehiculo)
                    .collect(Collectors.toSet());
        }
        return null;
    }

}
