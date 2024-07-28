package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.auth.RegisterIngresoRequest;
import com.pruebatecnica.demo.auth.RegisterSalidaRequest;
import com.pruebatecnica.demo.entity.Historial;
import com.pruebatecnica.demo.entity.IngresoVehiculo;
import com.pruebatecnica.demo.entity.Parqueadero;
import com.pruebatecnica.demo.entity.Vehiculo;
import com.pruebatecnica.demo.repository.HistorialRepository;
import com.pruebatecnica.demo.repository.IngresoVehiculoRepository;
import com.pruebatecnica.demo.repository.ParqueaderoRepository;
import com.pruebatecnica.demo.repository.VehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistorialService {

    private final HistorialRepository historialRepository;
    private final IngresoVehiculoRepository ingresoVehiculoRepository;
    private final VehiculoRepository vehiculoRepository;

    private final ParqueaderoRepository parqueaderoRepository;

    public String createSalidaVehiculo(RegisterSalidaRequest registerSalidaRequest){

        if (registerSalidaRequest.getIdParqueadero().toString().trim().isEmpty()) {
            return "El campo de idParqueadero no debe ser vacío o nulo";
        } else if (registerSalidaRequest.getPlacaVehiculo().trim().isEmpty()) {
            return "El campo de placa_vehiculo no debe ser vacío o nulo";
        } else if (!ingresoVehiculoRepository.validateRegistroVehiculo(registerSalidaRequest.getPlacaVehiculo(), registerSalidaRequest.getIdParqueadero())) {
            return "No se puede registrar salida, no existe la placa de este vehículo en este parqueadero";
        } else if (!ingresoVehiculoRepository.validateSocioWithParqueadero(registerSalidaRequest.getCedula(), registerSalidaRequest.getIdParqueadero())) {
            return "El parqueadero no corresponde al socio ingresado";
        }else{
            Optional<Vehiculo> v = vehiculoRepository.findById(registerSalidaRequest.getPlacaVehiculo());
            Optional<Parqueadero> p = parqueaderoRepository.findById(registerSalidaRequest.getIdParqueadero());

            Vehiculo vehiculo = Vehiculo.builder()
                    .placa(v.get().getPlaca())
                    .marca(v.get().getMarca())
                    .modelo(v.get().getModelo())
                    .tipo(v.get().getTipo())
                    .color(v.get().getColor())
                    .build();

            IngresoVehiculo ingreso = ingresoVehiculoRepository.findByVehiculo(vehiculo);

            LocalDateTime fechaHoraSalida = LocalDateTime.now();

            Duration tiempo = Duration.between(ingreso.getFechaHoraIngreso(), fechaHoraSalida);

            long cobroFinal = 0L;

            if((tiempo.toMinutes() % 60) > 1){
                if(v.get().getTipo().getIdTipo() == 1){
                    cobroFinal = (tiempo.toHours() + 1) * p.get().getCostoHoraMoto();
                }else{
                    cobroFinal = (tiempo.toHours() + 1) * p.get().getCostoHoraCarro();
                }
            }

            Historial historial = Historial.builder()
                    .vehiculo(ingreso.getVehiculo())
                    .parqueadero(ingreso.getParqueadero())
                    .fechaHoraIngreso(ingreso.getFechaHoraIngreso())
                    .fechaHoraSalida(fechaHoraSalida)
                    .cobro(cobroFinal)
                    .build();

            historialRepository.save(historial);
            ingresoVehiculoRepository.deleteById(ingreso.getIdIngreso());
            return "Salida registrada";
        }
    }

}
