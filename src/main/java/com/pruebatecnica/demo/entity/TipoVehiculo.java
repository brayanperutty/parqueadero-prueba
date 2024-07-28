package com.pruebatecnica.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "tipo_vehiculo")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TipoVehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo", nullable = false)
    private Integer idTipo;

    @Column(nullable = false)
    private String tipo;

    @OneToMany(mappedBy = "tipo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Vehiculo> vehiculos = new HashSet<>();
}
