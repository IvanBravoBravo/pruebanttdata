package com.nttdata.prueba.model;

import lombok.Data;

@Data
public class TelefonoRequest {
    private String numero;
    private String codigoCiudad;
    private String codigoPais;
}
