package com.example.cocina_service;

import com.example.cocina_service.services.PlatosService;
import org.example.dtos.DevolucionBodegaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @Autowired
    private PlatosService platosService;

    @KafkaListener(topics = "devolucionBodega", groupId = "mi-grupo1")
    public void devolucionBodega(DevolucionBodegaDTO devolucionBodegaDTO) {
        platosService.prepararPlato(devolucionBodegaDTO);

    }

}
