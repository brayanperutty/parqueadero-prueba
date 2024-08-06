package com.pruebatecnica.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "ingreso_vehiculo")
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @Column(name = "fecha_hora_ingreso", nullable = false)
    private LocalDateTime fechaHoraIngreso;

}
