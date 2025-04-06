package com.nttdata.prueba.controller;

import com.nttdata.prueba.model.HealthResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/v1")
public class HealthController {

    @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HealthResponse> health() {
        log.info("health ok.");
        //return new ResponseEntity(new HealthResponse(), HttpStatus.OK);
        HealthResponse response = new HealthResponse();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
