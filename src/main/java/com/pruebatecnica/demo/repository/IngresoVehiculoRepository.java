package com.pruebatecnica.demo.repository;

import com.pruebatecnica.demo.entity.IngresoVehiculo;
import com.pruebatecnica.demo.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngresoVehiculoRepository extends JpaRepository<IngresoVehiculo, Integer> {

    @Query(value = "SELECT CASE WHEN COUNT(iv) > 0 THEN TRUE ELSE FALSE END FROM ingreso_vehiculo iv WHERE iv.placa_vehiculo = :placaVehiculo ", nativeQuery = true)
    boolean validatePlacaVehiculo(String placaVehiculo);

    @Query(value = "SELECT CASE WHEN COUNT(iv) > 0 THEN TRUE ELSE FALSE END FROM ingreso_vehiculo iv WHERE iv.placa_vehiculo = :placaVehiculo " +
            "AND iv.id_parqueadero = :idParqueadero", nativeQuery = true)
    boolean validateRegistroVehiculo(String placaVehiculo, Integer idParqueadero);

    @Query(value = "SELECT CASE WHEN COUNT(p) > 0 THEN TRUE ELSE FALSE END " +
            "FROM Parqueadero p " +
            "JOIN p.socios s " +
            "WHERE p.idParqueadero = :idParqueadero " +
            "AND s.cedula = :cedula")
    boolean validateSocioWithParqueadero(String cedula, Integer idParqueadero);

    @Query(value = "SELECT iv FROM IngresoVehiculo iv WHERE iv.vehiculo = :vehiculo")
    IngresoVehiculo findByVehiculo(Vehiculo vehiculo);
}
