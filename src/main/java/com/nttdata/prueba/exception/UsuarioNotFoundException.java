package com.nttdata.prueba.exception;

public class UsuarioNotFoundException extends Exception{
    public UsuarioNotFoundException()
    {
    }
    public UsuarioNotFoundException(String message)
    {
        super(message);
    }
}
