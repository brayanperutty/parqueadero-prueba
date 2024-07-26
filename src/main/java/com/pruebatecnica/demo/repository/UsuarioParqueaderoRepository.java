package com.pruebatecnica.demo.repository;

import com.pruebatecnica.demo.entity.UsuarioParqueadero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioParqueaderoRepository extends JpaRepository<UsuarioParqueadero, Integer> {
}
