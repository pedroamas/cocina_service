package com.example.cocina_service.controllers;

import com.example.cocina_service.services.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RestController
public class HomeController {
    @Autowired
    HomeService homeService;

    @PostMapping("/new-plate")
    public ResponseEntity<String> newPlate(){
        homeService.newPlate();
        return ResponseEntity.ok("Nuevo plato ordenado ");
    }

    @GetMapping(value = "/monitoring", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> monitoring(){
        return Flux.interval(Duration.ofSeconds(1))
                .map(i -> "Mensaje número " + i);
    }
}
