package com.pruebatecnica.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "parqueadero")
public class Parqueadero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parqueadero", nullable = false)
    private Integer idParqueadero;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Integer capacidad;

    @Column(nullable = false)
    private Integer costoHoraCarro;

    @Column(nullable = false)
    private Integer costoHoraMoto;

    @ManyToMany(mappedBy = "parqueaderos", fetch = FetchType.LAZY)
    private Set<Usuario> socios = new HashSet<>();

    @OneToMany(mappedBy = "parqueadero", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<IngresoVehiculo> ingresos = new HashSet<>();
}
