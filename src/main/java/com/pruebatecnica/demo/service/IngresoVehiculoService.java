package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.auth.RegisterIngresoRequest;
import com.pruebatecnica.demo.entity.IngresoVehiculo;
import com.pruebatecnica.demo.entity.Parqueadero;
import com.pruebatecnica.demo.entity.Vehiculo;
import com.pruebatecnica.demo.repository.IngresoVehiculoRepository;
import com.pruebatecnica.demo.repository.ParqueaderoRepository;
import com.pruebatecnica.demo.repository.VehiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class IngresoVehiculoService {

    private final IngresoVehiculoRepository ingresoVehiculoRepository;
    private final VehiculoRepository vehiculoRepository;
    private final VehiculoService vehiculoService;
    private final ParqueaderoRepository parqueaderoRepository;

    public String createIngresoVehiculo(RegisterIngresoRequest registerIngresoRequest) {

        if (registerIngresoRequest.getIdParqueadero().toString().trim().isEmpty()) {
            return "El campo de idParqueadero no debe ser vacío o nulo";
        } else if (registerIngresoRequest.getPlacaVehiculo().trim().isEmpty()) {
            return "El campo de placa_vehiculo no debe ser vacío o nulo";
        } else if (registerIngresoRequest.getMarca().trim().isEmpty()) {
            return "El campo de marca de vehículo no debe ser vacío o nulo";
        } else if (registerIngresoRequest.getModelo().trim().isEmpty()) {
            return "El campo de modelo de vehículo no debe ser vacío o nulo";
        } else if (registerIngresoRequest.getColor().trim().isEmpty()) {
            return "El campo de color de vehículo no debe ser vacío o nulo";
        } else if (registerIngresoRequest.getIdTipo().toString().trim().isEmpty()) {
            return "El campo de tipo de vehículo no debe ser vacío o nulo";
        } else if (ingresoVehiculoRepository.validatePlacaVehiculo(registerIngresoRequest.getPlacaVehiculo())) {
            return "No se puede registrar ingreso, ya existe la placa en este u otro parqueadero";
        } else if (!ingresoVehiculoRepository.validateSocioWithParqueadero(registerIngresoRequest.getCedula(), registerIngresoRequest.getIdParqueadero())) {
            return "El parqueadero no corresponde al socio ingresado";
        } else {

            if (!vehiculoRepository.existsById(registerIngresoRequest.getPlacaVehiculo())) {
                vehiculoService.createVehiculo(registerIngresoRequest);
            }

            Optional<Vehiculo> vehiculo = vehiculoRepository.findById(registerIngresoRequest.getPlacaVehiculo());
            Optional<Parqueadero> parqueadero = parqueaderoRepository.findById(registerIngresoRequest.getIdParqueadero());

            LocalDateTime fechaIngreso = LocalDateTime.now();

            IngresoVehiculo ingreso = IngresoVehiculo.builder()
                    .vehiculo(vehiculo.get())
                    .parqueadero(parqueadero.get())
                    .fechaHoraIngreso(fechaIngreso)
                    .build();

            ingresoVehiculoRepository.save(ingreso);
            System.out.println(ingresoVehiculoRepository.validateSocioWithParqueadero(registerIngresoRequest.getCedula(), registerIngresoRequest.getIdParqueadero()));
            return "" + ingreso.getIdIngreso();
        }
    }

}
