package com.pruebatecnica.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "vehiculo")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Vehiculo {

    @Id
    @Column(nullable = false)
    private String placa;

    @ManyToOne
    @JoinColumn(name = "id_tipo", nullable = false)
    private TipoVehiculo tipo;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String color;

    @OneToMany(mappedBy = "vehiculo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<IngresoVehiculo> ingresos = new HashSet<>();

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Historial> historial = new HashSet<>();
}
