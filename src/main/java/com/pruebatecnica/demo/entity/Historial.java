package com.pruebatecnica.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "historial")
@AllArgsConstructor
@NoArgsConstructor
public class Historial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_historial")
    private Integer idHistorial;

    @Column(name = "id_ingreso")
    private Integer idIngreso;

    @Column(name = "fecha_salida")
    private String fechaSalida;

    @Column(name = "hora_salida")
    private String horaSalida;

    private Integer cobro;
}
