package com.nttdata.prueba.controller;

import com.nttdata.prueba.exception.InvalidEmailException;
import com.nttdata.prueba.exception.InvalidPasswordException;
import com.nttdata.prueba.exception.UsuarioExisteException;
import com.nttdata.prueba.exception.UsuarioNotFoundException;
import com.nttdata.prueba.model.HealthResponse;
import com.nttdata.prueba.model.UsuarioRequest;
import com.nttdata.prueba.model.UsuarioResponse;
import com.nttdata.prueba.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/v1")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping(value = "/usuarios/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioResponse> getUsuario(@PathVariable @Valid String id) throws UsuarioNotFoundException {
        UsuarioResponse usuario = usuarioService.getUsuario(id)
                .orElseThrow(() -> new UsuarioNotFoundException());
        return ResponseEntity.status(HttpStatus.OK).body(usuario);
    }

    @GetMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UsuarioResponse>> getUsuarios(){
        List<UsuarioResponse> usuarios = usuarioService.getUsuarios();
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @PostMapping(value = "/usuarios",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UsuarioResponse> createUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest)
            throws UsuarioExisteException, InvalidPasswordException, InvalidEmailException {
        UsuarioResponse usuario = usuarioService.postUsuario(usuarioRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    @PutMapping(value = "/usuarios",
            produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUsuario(@RequestBody @Valid UsuarioRequest usuarioRequest)
            throws UsuarioNotFoundException {
        usuarioService.putUsuario(usuarioRequest);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(value = "/usuarios/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deleteUsuario(@PathVariable @Valid String id) throws UsuarioNotFoundException {
        usuarioService.deleteUsuario(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
