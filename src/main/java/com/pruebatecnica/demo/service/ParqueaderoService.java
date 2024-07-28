package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.entity.Parqueadero;
import com.pruebatecnica.demo.repository.ParqueaderoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParqueaderoService {

    public final ParqueaderoRepository parqueaderoRepository;

    public Optional<Parqueadero> getParqueadero(Integer idParqueadero){
        return parqueaderoRepository.findById(idParqueadero);
    }

    public String createParqueadero(Parqueadero parqueadero){
        if(parqueaderoRepository.validateParqueadero(parqueadero.getNombre())){
            return "Este nombre de parqueadero ya existe registrado";
        }else if(!parqueadero.getCapacidad().toString().matches("^[0-9]+$") || parqueadero.getCapacidad().toString().trim().isEmpty()){
            return "La cantidad de capacidad del parqueadero solo deben ser números positivos, o no debe estar vacío este campo";
        }else if(parqueadero.getNombre() == null || parqueadero.getNombre().trim().isEmpty()){
            return "No debe estar vacío o nulo el campo del nombre";
        }else if(!parqueadero.getCostoHoraMoto().toString().matches("^[0-9]+$") || parqueadero.getCostoHoraMoto().toString().trim().isEmpty()){
            return "En el costo por hora Moto no debe tener caracteres, o no debe estar vacío o nulo el campo";
        }else if(!parqueadero.getCostoHoraCarro().toString().matches("^[0-9]+$") || parqueadero.getCostoHoraCarro().toString().trim().isEmpty()){
            return "En el costo por hora Carro no debe tener caracteres, o no debe estar vacío o nulo el campo";
        }else {
            parqueaderoRepository.save(parqueadero);
            return "Parqueadero '" + parqueadero.getNombre() + "' creado con éxito";
        }
    }

    public String updateParqueadero(Parqueadero parqueadero){
        Optional<Parqueadero> p = parqueaderoRepository.findById(parqueadero.getIdParqueadero());
        if(p.isPresent()){
            p.get().setCapacidad(parqueadero.getCapacidad());
            p.get().setNombre(parqueadero.getNombre());
            p.get().setCostoHoraCarro(parqueadero.getCostoHoraCarro());
            p.get().setCostoHoraMoto(parqueadero.getCostoHoraMoto());
            parqueaderoRepository.save(p.get());
            return "Parqueadero '" + parqueadero.getNombre() + "' actualizado con éxito";
        }else{
            return "Error al actualizar el parqueadero";
        }
    }

    public void deleteParqueadero(Integer idParqueadero){
        parqueaderoRepository.deleteById(idParqueadero);
    }

}
