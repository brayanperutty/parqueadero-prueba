package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.config.jwt.JwtService;
import com.pruebatecnica.demo.dto.request.EmailDTO;
import com.pruebatecnica.demo.dto.request.IngresoVehiculoDTO;
import com.pruebatecnica.demo.entity.IngresoVehiculo;
import com.pruebatecnica.demo.entity.Parqueadero;
import com.pruebatecnica.demo.entity.Usuario;
import com.pruebatecnica.demo.entity.Vehiculo;
import com.pruebatecnica.demo.repository.IngresoVehiculoRepository;
import com.pruebatecnica.demo.repository.ParqueaderoRepository;
import com.pruebatecnica.demo.repository.UsuarioRepository;
import com.pruebatecnica.demo.repository.VehiculoRepository;
import com.pruebatecnica.demo.responses.ingresovehiculo.EmailErrorResponse;
import com.pruebatecnica.demo.responses.ingresovehiculo.EmailOkResponse;
import com.pruebatecnica.demo.responses.ingresovehiculo.IngresoVehiculoCreateResponse;
import com.pruebatecnica.demo.responses.ingresovehiculo.IngresoVehiculoErrorResponses;
import com.pruebatecnica.demo.responses.parqueadero.ParqueaderoErrorResponses;
import com.pruebatecnica.demo.responses.vehiculo.VehiculoErrorResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class IngresoVehiculoService {

    private final IngresoVehiculoRepository ingresoVehiculoRepository;
    private final VehiculoRepository vehiculoRepository;
    private final VehiculoService vehiculoService;
    private final ParqueaderoRepository parqueaderoRepository;
    private final UsuarioRepository usuarioRepository;

    private final ParqueaderoErrorResponses parqueaderoErrorResponses;
    private final VehiculoErrorResponses vehiculoErrorResponses;
    private final IngresoVehiculoCreateResponse ingresoVehiculoCreateResponse;
    private final IngresoVehiculoErrorResponses ingresoVehiculoErrorResponses;
    private final EmailOkResponse emailOkResponse;
    private final EmailErrorResponse emailErrorResponse;

    private final RestTemplate restTemplate;
    private final JwtService jwtService;


    public IngresoVehiculoCreateResponse createIngresoVehiculo(IngresoVehiculoDTO ingresoVehiculoDTO) throws IllegalArgumentException{

        Integer idUsuario = jwtService.getIdUsuario();
        if (ingresoVehiculoRepository.validatePlacaVehiculo(ingresoVehiculoDTO.getPlacaVehiculo())) {
            throw new IllegalArgumentException(ingresoVehiculoErrorResponses.getErrorIngreso());
        } else if (!ingresoVehiculoRepository.validateSocioWithParqueadero(ingresoVehiculoDTO.getIdParqueadero(), idUsuario)) {
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
           Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new RuntimeException("Socio no encontrado"));
           LocalDateTime fechaIngreso = LocalDateTime.now();

           EmailDTO emailDTO = EmailDTO.builder().
                   email(usuario.getUsername())
                   .placa(vehiculo.getPlaca())
                   .mensaje(emailOkResponse.getMensaje())
                   .parqueaderoNombre(parqueadero.getNombre())
                   .build();

           if(!sendEmail(emailDTO).equals(emailOkResponse)){
               vehiculoRepository.deleteById(vehiculo.getPlaca());
               throw new IllegalArgumentException(emailErrorResponse.getMensaje());
           }else {

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

    public EmailOkResponse sendEmail(EmailDTO emailDTO){
        return restTemplate.postForObject("http://localhost:8090/email", emailDTO, EmailOkResponse.class);
    }

}
