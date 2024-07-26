package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.repository.UsuarioParqueaderoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioParqueaderoService {

    public final UsuarioParqueaderoRepository usuarioParqueaderoRepository;

    public UsuarioParqueaderoService(UsuarioParqueaderoRepository usuarioParqueaderoRepository) {
        this.usuarioParqueaderoRepository = usuarioParqueaderoRepository;
    }
}
