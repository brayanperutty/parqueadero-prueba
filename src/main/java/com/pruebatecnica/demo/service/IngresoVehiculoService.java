package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.dto.IngresoVehiculoDTO;
import com.pruebatecnica.demo.entity.IngresoVehiculo;
import com.pruebatecnica.demo.entity.Parqueadero;
import com.pruebatecnica.demo.entity.Vehiculo;
import com.pruebatecnica.demo.repository.IngresoVehiculoRepository;
import com.pruebatecnica.demo.repository.ParqueaderoRepository;
import com.pruebatecnica.demo.repository.VehiculoRepository;
import com.pruebatecnica.demo.responses.ingresovehiculo.IngresoVehiculoCreateResponse;
import com.pruebatecnica.demo.responses.ingresovehiculo.IngresoVehiculoErrorResponses;
import com.pruebatecnica.demo.responses.parqueadero.ParqueaderoErrorResponses;
import com.pruebatecnica.demo.responses.vehiculo.VehiculoErrorResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class IngresoVehiculoService {

    private final IngresoVehiculoRepository ingresoVehiculoRepository;
    private final VehiculoRepository vehiculoRepository;
    private final VehiculoService vehiculoService;
    private final ParqueaderoRepository parqueaderoRepository;

    private final ParqueaderoErrorResponses parqueaderoErrorResponses;
    private final VehiculoErrorResponses vehiculoErrorResponses;
    private final IngresoVehiculoCreateResponse ingresoVehiculoCreateResponse;
    private final IngresoVehiculoErrorResponses ingresoVehiculoErrorResponses;

    public IngresoVehiculoCreateResponse createIngresoVehiculo(IngresoVehiculoDTO ingresoVehiculoDTO) throws IllegalArgumentException{

       if (ingresoVehiculoRepository.validatePlacaVehiculo(ingresoVehiculoDTO.getPlacaVehiculo())) {
            throw new IllegalArgumentException(ingresoVehiculoErrorResponses.getErrorIngreso());
        } else if (!ingresoVehiculoRepository.validateSocioWithParqueadero(ingresoVehiculoDTO.getIdSocio(), ingresoVehiculoDTO.getIdParqueadero())) {
            throw new IllegalArgumentException(ingresoVehiculoErrorResponses.getParqueaderoError());
        } else {

            if (!vehiculoRepository.existsById(ingresoVehiculoDTO.getPlacaVehiculo())) {
                vehiculoService.createVehiculo(
                        ingresoVehiculoDTO.getPlacaVehiculo(),
                        ingresoVehiculoDTO.getMarcaVehiculo(),
                        ingresoVehiculoDTO.getModeloVehiculo(),
                        ingresoVehiculoDTO.getColorVehiculo(),
                        ingresoVehiculoDTO.getIdTipoVehiculo()
                );
            }

            Vehiculo vehiculo = vehiculoRepository.findById(ingresoVehiculoDTO.getPlacaVehiculo()).orElseThrow(() -> new RuntimeException(vehiculoErrorResponses.getNoEncontrado()));
            Parqueadero parqueadero = parqueaderoRepository.findById(ingresoVehiculoDTO.getIdParqueadero()).orElseThrow(() -> new RuntimeException(parqueaderoErrorResponses.getNoEncontrado()));

            LocalDateTime fechaIngreso = LocalDateTime.now();

            IngresoVehiculo ingreso = IngresoVehiculo.builder()
                    .vehiculo(vehiculo)
                    .parqueadero(parqueadero)
                    .fechaHoraIngreso(fechaIngreso)
                    .build();

            ingresoVehiculoRepository.save(ingreso);
           ingresoVehiculoCreateResponse.setId(ingreso.getIdIngreso());
            return ingresoVehiculoCreateResponse;
        }
    }

}
