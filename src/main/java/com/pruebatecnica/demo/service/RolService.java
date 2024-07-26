package com.pruebatecnica.demo.service;

import com.pruebatecnica.demo.repository.RolRepository;
import org.springframework.stereotype.Service;

@Service
public class RolService {

    public final RolRepository rolRepository;

    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }
}
