import com.example.cocina_service.KafkaConsumer;
import com.example.cocina_service.services.PlatosService;
import org.example.dtos.DevolucionBodegaDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class KafkaConsumerTest {

    @Mock
    PlatosService platosService;

    @InjectMocks
    KafkaConsumer kafkaConsumer;

    @Test
    void shouldCallPrepararPlatoWhenMessageArrives() {
        // GIVEN
        DevolucionBodegaDTO dto = new DevolucionBodegaDTO(1L);

        // WHEN
        kafkaConsumer.devolucionBodega(dto);

        // THEN
        verify(platosService).prepararPlato(dto);
    }
}
