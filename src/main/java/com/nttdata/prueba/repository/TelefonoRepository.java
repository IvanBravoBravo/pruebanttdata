package com.nttdata.prueba.repository;

import com.nttdata.prueba.entity.Telefono;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefonoRepository extends JpaRepository<Telefono, Long> {
}
