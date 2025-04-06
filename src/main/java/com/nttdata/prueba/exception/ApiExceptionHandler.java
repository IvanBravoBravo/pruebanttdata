package com.nttdata.prueba.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(UsuarioNotFoundException.class)
    public ResponseEntity<MensajeError> handleUsuarioNotFound(UsuarioNotFoundException ex) {
        MensajeError error = new MensajeError();
        error.setMensaje("usuario no encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(UsuarioExisteException.class)
    public ResponseEntity<MensajeError> handleUsuarioFound(UsuarioExisteException ex) {
        MensajeError error = new MensajeError();
        error.setMensaje("El correo ya esta registrado");
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<MensajeError> handleInvalidEmail(InvalidEmailException ex) {
        MensajeError error = new MensajeError();
        error.setMensaje("Correo con formato incorrecto");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<MensajeError> handleInvalidPassword(InvalidPasswordException ex) {
        MensajeError error = new MensajeError();
        error.setMensaje("Password con formato incorrecto");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
