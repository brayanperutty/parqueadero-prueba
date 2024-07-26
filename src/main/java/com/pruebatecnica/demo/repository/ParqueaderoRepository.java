package com.pruebatecnica.demo.repository;

import com.pruebatecnica.demo.entity.Parqueadero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParqueaderoRepository extends JpaRepository <Parqueadero, Integer> {

}
