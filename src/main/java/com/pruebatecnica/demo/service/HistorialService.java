package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.config.jwt.JwtService;
import com.pruebatecnica.demo.dto.request.SalidaVehiculoDTO;
import com.pruebatecnica.demo.entity.Historial;
import com.pruebatecnica.demo.entity.IngresoVehiculo;
import com.pruebatecnica.demo.entity.Parqueadero;
import com.pruebatecnica.demo.entity.Vehiculo;
import com.pruebatecnica.demo.repository.HistorialRepository;
import com.pruebatecnica.demo.repository.IngresoVehiculoRepository;
import com.pruebatecnica.demo.repository.ParqueaderoRepository;
import com.pruebatecnica.demo.repository.VehiculoRepository;
import com.pruebatecnica.demo.responses.parqueadero.ParqueaderoErrorResponses;
import com.pruebatecnica.demo.responses.salidavehiculo.SalidaVehiculoCreateResponse;
import com.pruebatecnica.demo.responses.salidavehiculo.SalidaVehiculoErrorResponses;
import com.pruebatecnica.demo.responses.vehiculo.VehiculoErrorResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
@RequiredArgsConstructor
public class HistorialService {

    private final HistorialRepository historialRepository;
    private final IngresoVehiculoRepository ingresoVehiculoRepository;
    private final VehiculoRepository vehiculoRepository;
    private final ParqueaderoRepository parqueaderoRepository;

    private final VehiculoErrorResponses vehiculoErrorResponses;
    private final ParqueaderoErrorResponses parqueaderoErrorResponses;
    private final SalidaVehiculoErrorResponses salidaVehiculoErrorResponses;
    private final SalidaVehiculoCreateResponse salidaVehiculoCreateResponse;

    private final JwtService jwtService;

    public SalidaVehiculoCreateResponse createSalidaVehiculo(SalidaVehiculoDTO salidaVehiculoDTO) throws IllegalArgumentException{
        System.out.println("service");
        Integer idUsuario = jwtService.getIdUsuario();
        if (!ingresoVehiculoRepository.validateRegistroVehiculo(salidaVehiculoDTO.getPlacaVehiculo(), salidaVehiculoDTO.getIdParqueadero())) {
            throw new IllegalArgumentException(salidaVehiculoErrorResponses.getErrorSalida());
        } else if (!ingresoVehiculoRepository.validateSocioWithParqueadero(salidaVehiculoDTO.getIdParqueadero(), idUsuario)) {
            throw new IllegalArgumentException(salidaVehiculoErrorResponses.getParqueaderoError());
        }else{
            Vehiculo v = vehiculoRepository.findById(salidaVehiculoDTO.getPlacaVehiculo()).orElseThrow(() -> new RuntimeException(vehiculoErrorResponses.getNoEncontrado()));
            Parqueadero p = parqueaderoRepository.findById(salidaVehiculoDTO.getIdParqueadero()).orElseThrow(() -> new RuntimeException(parqueaderoErrorResponses.getNoEncontrado()));

            IngresoVehiculo ingresoVehiculo = ingresoVehiculoRepository.findByVehiculo(v).orElseThrow(() -> new RuntimeException(vehiculoErrorResponses.getNoEncontrado()));

            LocalDateTime fechaHoraSalida = LocalDateTime.now();

            Duration tiempo = Duration.between(ingresoVehiculo.getFechaHoraIngreso(), fechaHoraSalida);

            long cobroFinal = 0L;

            if((tiempo.toMinutes() % 60) > 1){
                if(v.getTipo().getIdTipo() == 1){
                    cobroFinal = (tiempo.toHours() + 1) * p.getCostoHoraMoto();
                }else{
                    cobroFinal = (tiempo.toHours() + 1) * p.getCostoHoraCarro();
                }
            }
            System.out.println(ingresoVehiculo);
            Historial historial = Historial.builder()
                    .vehiculo(ingresoVehiculo.getVehiculo())
                    .parqueadero(ingresoVehiculo.getParqueadero())
                    .fechaHoraIngreso(ingresoVehiculo.getFechaHoraIngreso())
                    .fechaHoraSalida(fechaHoraSalida)
                    .cobro(cobroFinal)
                    .build();

            historialRepository.save(historial);
            ingresoVehiculoRepository.deleteById(ingresoVehiculo.getIdIngreso());
            return salidaVehiculoCreateResponse;
        }
    }

}
