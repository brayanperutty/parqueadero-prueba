package com.pruebatecnica.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import jakarta.persistence.*;

@Entity
@Data
@Table(name = "historial")
public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial", nullable = false)
    private Integer idHistorial;

    @ManyToOne
    @JoinColumn(name = "id_parqueadero", nullable = false)
    private Parqueadero parqueadero;

    @ManyToOne
    @JoinColumn(name = "placa_vehiculo", nullable = false)
    private Vehiculo vehiculo;

    @Column(name = "fecha_salida", nullable = false)
    private String fechaSalida;

    @Column(name = "hora_salida", nullable = false)
    private String horaSalida;

    @Column(name = "hora_ingreso", nullable = false)
    private String horaIngreso;

    @Column(name = "fecha_ingreso", nullable = false)
    private String fechaIngreso;

    @Column(nullable = false)
    private Integer cobro;

    public Historial (){}
}