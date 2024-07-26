package com.pruebatecnica.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "usuario_parqueadero")
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioParqueadero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_usuario")
    private String cedula;

    @Column(name = "id_parqueadero")
    private Integer idParqueadero;
}
