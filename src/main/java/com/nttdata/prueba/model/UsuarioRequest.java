package com.nttdata.prueba.model;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class UsuarioRequest {
    @NotNull
    private String nombre;
    @NotNull
    private String correo;
    @NotNull
    private String contraseña;
    private List<TelefonoRequest> telefonos;
}
