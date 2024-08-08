package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.entity.Parqueadero;
import com.pruebatecnica.demo.repository.ParqueaderoRepository;
import com.pruebatecnica.demo.responses.parqueadero.ParqueaderoCreateResponse;
import com.pruebatecnica.demo.responses.parqueadero.ParqueaderoErrorResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParqueaderoService {

    private final ParqueaderoRepository parqueaderoRepository;
    private final ParqueaderoErrorResponses parqueaderoErrorResponses;
    private final ParqueaderoCreateResponse parqueaderoCreateResponse;

    public Parqueadero getParqueadero(Integer idParqueadero){
        return parqueaderoRepository.findById(idParqueadero).orElseThrow(() -> new RuntimeException(parqueaderoErrorResponses.getNoEncontrado()));

    }

    public ParqueaderoCreateResponse createParqueadero(Parqueadero parqueadero) throws IllegalArgumentException{
        if(parqueaderoRepository.validateParqueadero(parqueadero.getNombre())){
            throw new IllegalArgumentException(parqueaderoErrorResponses.getRegistradoAnteriormente());
        }else {
            parqueaderoRepository.save(parqueadero);
            return parqueaderoCreateResponse;
        }
    }

    public String updateParqueadero(Parqueadero parqueadero){
        Parqueadero p = parqueaderoRepository.findById(parqueadero.getIdParqueadero()).orElseThrow(() -> new RuntimeException(parqueaderoErrorResponses.getErrorCreateParqueadero()));

            p.setCapacidad(parqueadero.getCapacidad());
            p.setNombre(parqueadero.getNombre());
            p.setCostoHoraCarro(parqueadero.getCostoHoraCarro());
            p.setCostoHoraMoto(parqueadero.getCostoHoraMoto());
            parqueaderoRepository.save(p);
            return "Parqueadero '" + parqueadero.getNombre() + "' actualizado con éxito";

    }

    public void deleteParqueadero(Integer idParqueadero){
        parqueaderoRepository.deleteById(idParqueadero);
    }

    public List<Parqueadero> listParqueadero(){
       return parqueaderoRepository.findAll();
    }

}
