package com.pruebatecnica.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "vehiculo")
@AllArgsConstructor
@NoArgsConstructor
public class Vehiculo {

    @Id
    private String placa;

    @Column(name = "id_tipo")
    private Integer idTipo;

    private String marca;
    private String modelo;
    private String color;
}
