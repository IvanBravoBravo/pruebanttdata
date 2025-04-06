package com.nttdata.prueba.repository;

import com.nttdata.prueba.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    public Optional<Usuario> findByCorreo(String correo);
}
