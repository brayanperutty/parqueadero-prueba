package com.pruebatecnica.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @Column(nullable = false)
    private String cedula;

    @Column(name = "nombre_completo", nullable = false)
    private String nombreCompleto;

    @Column(nullable = false)
    private String correo;

    @Column(nullable = false)
    private String pass;

    @ManyToOne
    @JoinColumn(name = "id_rol", nullable = false)
    private Rol idRol;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "usuario_parqueadero",
            joinColumns = @JoinColumn(name = "usuario_cedula"),
            inverseJoinColumns = @JoinColumn(name = "parqueadero_id")
    )
    private Set<Parqueadero> parqueaderos = new HashSet<>();
}
