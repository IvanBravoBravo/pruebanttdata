package com.nttdata.prueba.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "Telefono")
@Table(name = "telefonos")
public class Telefono {
    @ManyToOne
    private Usuario usuario;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String numero;
    private String codigoCiudad;
    private String codigoPais;
}
