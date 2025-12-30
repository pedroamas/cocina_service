package com.example.cocina_service.services;

import org.example.dtos.SolicitudBodegaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductorKafka {
    private static final String BODEGA = "bodega";

    @Autowired
    private KafkaTemplate<String, SolicitudBodegaDTO> kafkaTemplate;

    public void pedirABodega(SolicitudBodegaDTO solicitudBodegaDTO) {
        kafkaTemplate.send(BODEGA, solicitudBodegaDTO);
        System.out.println("Plato: " + solicitudBodegaDTO.getIdPlato() + " Receta: "+solicitudBodegaDTO.getRecipe().getName());
    }
}
