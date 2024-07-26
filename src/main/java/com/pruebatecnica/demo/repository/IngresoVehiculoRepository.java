package com.pruebatecnica.demo.repository;

import com.pruebatecnica.demo.entity.IngresoVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngresoVehiculoRepository extends JpaRepository<IngresoVehiculo, Integer> {
}
