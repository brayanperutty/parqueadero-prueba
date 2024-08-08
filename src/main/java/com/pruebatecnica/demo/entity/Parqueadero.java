package com.pruebatecnica.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "parqueadero")
public class Parqueadero{

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
    @JsonIgnore
    private Set<Usuario> socios = new HashSet<>();

    @OneToMany(mappedBy = "parqueadero", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<IngresoVehiculo> ingresos = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Parqueadero that = (Parqueadero) o;
        return Objects.equals(idParqueadero, that.idParqueadero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idParqueadero);
    }
}
