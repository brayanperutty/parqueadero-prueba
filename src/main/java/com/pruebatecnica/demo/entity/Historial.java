package com.pruebatecnica.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "historial")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Historial{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial", nullable = false)
    private Integer idHistorial;

    @ManyToOne
    @JoinColumn(name = "id_parqueadero", nullable = false)
    private  Parqueadero parqueadero;

    @ManyToOne
    @JoinColumn(name = "placa_vehiculo", nullable = false)
    private  Vehiculo vehiculo;

    @Column(name = "fecha_hora_salida", nullable = false)
    private LocalDateTime fechaHoraSalida;

    @Column(name = "fecha_hora_ingreso", nullable = false)
    private LocalDateTime fechaHoraIngreso;

    @Column(nullable = false)
    private Long cobro;
}