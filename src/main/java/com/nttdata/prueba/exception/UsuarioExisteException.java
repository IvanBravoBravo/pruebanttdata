package com.nttdata.prueba.exception;

public class UsuarioExisteException extends Exception{
    public UsuarioExisteException() {
    }

    public UsuarioExisteException(String message) {
        super(message);
    }
}
