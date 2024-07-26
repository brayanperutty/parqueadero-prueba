package com.pruebatecnica.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ingreso_vehiculo")
@AllArgsConstructor
@NoArgsConstructor
public class IngresoVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingreso")
    private Integer idIngreso;

    @Column(name = "id_parqueadero")
    private Integer idParqueadero;

    @Column(name = "id_vehiculo")
    private Integer idVehiculo;

    @Column(name = "fecha_ingreso")
    private String fechaIngreso;

    @Column(name = "hora_ingreso")
    private String horaIngreso;
}
