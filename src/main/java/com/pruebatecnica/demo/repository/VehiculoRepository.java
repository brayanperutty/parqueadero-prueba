package com.pruebatecnica.demo.repository;

import com.pruebatecnica.demo.entity.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, String> {

    @Query(value = "SELECT v.placa as placa, tv.tipo as tipo_vehiculo, v.marca as marca, v.modelo as modelo, v.color as color, iv.fecha_ingreso as fechaIngreso, iv.hora_ingreso as horaIngreso " +
            "FROM vehiculo v " +
            "JOIN ingreso_vehiculo iv ON v.placa = iv.placa_vehiculo " +
            "JOIN tipo_vehiculo tv ON v.id_tipo = tv.id_tipo " +
            "JOIN parqueadero p ON iv.id_parqueadero = p.id_parqueadero " +
            "WHERE p.id_parqueadero = :idParqueadero ", nativeQuery = true)
    List<Object[]> listVehiculoByParqueadero(Integer idParqueadero);
}
