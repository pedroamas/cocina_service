package com.example.cocina_service;

import com.example.cocina_service.common.Menu;
import com.example.cocina_service.services.ProductorKafka;
import org.example.dtos.SolicitudBodegaDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class KafkaProducerTest {
    @Mock
    KafkaTemplate<String, SolicitudBodegaDTO> kafkaTemplate;

    @InjectMocks
    ProductorKafka productorKafka;

    @Test
    void shouldSendMessageToKafka() {
        // GIVEN
        SolicitudBodegaDTO dto = new SolicitudBodegaDTO(1L, Menu.getRandomMenu());

        // WHEN
        productorKafka.pedirABodega(dto);

        // THEN
        verify(kafkaTemplate)
                .send(anyString(), eq(dto));
    }
}
