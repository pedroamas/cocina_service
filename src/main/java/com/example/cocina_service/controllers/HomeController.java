package com.example.cocina_service.controllers;

import com.example.cocina_service.TriggerSink;
import com.example.cocina_service.domain.dtos.PlatoDTO;
import com.example.cocina_service.services.PlatosService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@CrossOrigin(
        origins = "*",
        allowedHeaders = "*"
)
public class HomeController {
    @Autowired
    PlatosService platosService;

    @Autowired
    TriggerSink sink;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/new-plate")
    public ResponseEntity<String> newPlate(){
        platosService.newPlate();
        return ResponseEntity.ok("Nuevo plato ordenado");
    }

    @GetMapping(value = "/monitoring-platos")
    public Page<PlatoDTO> monitoringPlatos(
            @RequestParam(defaultValue = "0") int pageNumber,
            @RequestParam(defaultValue = "10") int limit
    ) {
        return platosService.findAll(pageNumber, limit);
    }

    @GetMapping(
            value = "/stream-platos",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE
    )
    public Flux<String> stream() {
        return sink.flux();
    }


}
