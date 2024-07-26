package com.pruebatecnica.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    private String cedula;

    @Column(name = "nombre_completo")
    private String nombreCompleto;
    private String correo;
    private String pass;
    @Column(name = "id_rol")
    private Integer idRol;

    @ManyToMany(mappedBy = "parqueadero")
    private List<Parqueadero> parqueadero;
}
