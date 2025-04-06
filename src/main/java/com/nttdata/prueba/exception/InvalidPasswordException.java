package com.nttdata.prueba.exception;

public class InvalidPasswordException extends Exception{
    public InvalidPasswordException() {
    }

    public InvalidPasswordException(String message) {
        super(message);
    }
}
