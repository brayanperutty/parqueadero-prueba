package com.pruebatecnica.demo.repository;

import com.pruebatecnica.demo.entity.Parqueadero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ParqueaderoRepository extends JpaRepository <Parqueadero, Integer> {

    @Query(value = "SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END FROM parqueadero p WHERE p.nombre = :nombreParqueadero", nativeQuery = true)
    boolean validateParqueadero(String nombreParqueadero);
}
