package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.entity.Usuario;
import com.pruebatecnica.demo.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    public final UsuarioRepository usuarioRepository;

    public List<Usuario> listUsuarios(){
        return usuarioRepository.findAll();
    }

}
