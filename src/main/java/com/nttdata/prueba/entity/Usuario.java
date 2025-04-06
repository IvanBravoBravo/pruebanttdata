package com.nttdata.prueba.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity(name = "Usuario")
@Table(name = "usuarios")
@NamedQuery(name = "Usuario.findByCorreo",
        query = "select u from Usuario u where u.correo = ?1")
public class Usuario{

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private String id;

    private String nombre;

    private String correo;

    private String contraseña;

    //@Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime creado;

    //@Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @UpdateTimestamp
    private LocalDateTime modificado;

    @CreationTimestamp
    private LocalDateTime ultimoLogin;

    private String token;

    private boolean activo;

    @OneToMany(mappedBy = "usuario")
    //@OneToMany(cascade = CascadeType.ALL)
    //@JoinColumn(name = "usuario_id")
    private List<Telefono> telefonos;
}
