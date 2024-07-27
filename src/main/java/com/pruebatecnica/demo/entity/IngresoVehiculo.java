package com.pruebatecnica.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "ingreso_vehiculo")
@AllArgsConstructor
@NoArgsConstructor
public class IngresoVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ingreso", nullable = false)
    private Integer idIngreso;

    @ManyToOne
    @JoinColumn(name = "id_parqueadero", nullable = false)
    private Parqueadero parqueadero;

    @ManyToOne
    @JoinColumn(name = "placa_vehiculo", nullable = false)
    private Vehiculo vehiculo;

    @Column(name = "fecha_ingreso", nullable = false)
    private String fechaIngreso;

    @Column(name = "hora_ingreso", nullable = false)
    private String horaIngreso;
}
