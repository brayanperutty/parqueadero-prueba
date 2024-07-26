package com.pruebatecnica.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "parqueadero")
public class Parqueadero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parqueadero")
    private Integer idParqueadero;

    private String nombre;
    private Integer capacidad;

    @ManyToMany
    @JoinTable(
            name = "usuario_parqueadero",
            joinColumns = @JoinColumn(name = "id_parqueadero"),
            inverseJoinColumns = @JoinColumn(name = "cedula")
    )
    @Column(name = "id_administrador")
    private List<String> idAdministrador;

    private Integer costoHoraCarro;
    private Integer costoHoraMoto;
}
