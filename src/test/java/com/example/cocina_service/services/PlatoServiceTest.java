package com.example.cocina_service.services;

import com.example.cocina_service.TriggerSink;
import com.example.cocina_service.common.Menu;
import com.example.cocina_service.domain.Plato;
import com.example.cocina_service.domain.dtos.PlatoDTO;
import com.example.cocina_service.repositories.PlatosRepository;
import org.example.dtos.DevolucionBodegaDTO;
import org.example.dtos.SolicitudBodegaDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlatoServiceTest {

    @Mock
    private ProductorKafka productorKafka;

    @Mock
    private TriggerSink sink;

    @Mock
    private PlatosRepository platosRepository;

    @InjectMocks
    private PlatosService platosService;

    @Test
    void shouldCreateNewPlateAndSendEvents() {
        // GIVEN
        Plato platoGuardado = new Plato();
        platoGuardado.setId(1L);

        when(platosRepository.save(any(Plato.class)))
                .thenReturn(platoGuardado);

        // WHEN
        platosService.newPlate();

        // THEN
        verify(platosRepository, times(1)).save(any(Plato.class));

        verify(sink, times(1))
                .emit(startsWith("Nuevo plato"));

        verify(productorKafka, times(1))
                .pedirABodega(any(SolicitudBodegaDTO.class));
    }

    @Test
    void shouldPrepararPlatoThrowNullPointer(){
        // GIVEN
        when(platosRepository.findById(anyLong())).thenReturn(Optional.empty());
        // THEN
        DevolucionBodegaDTO devolucionBodegaDTO = new DevolucionBodegaDTO(1L);
        NullPointerException ex = assertThrows(
                NullPointerException.class,
                () -> platosService.prepararPlato(devolucionBodegaDTO)
        );

        assertEquals("Plato no encontrado", ex.getMessage());
    }

    @Test
    void shouldPrepararPlato() {
        // GIVEN
        when(platosRepository.findById(anyLong())).thenReturn(Optional.of(new Plato()));

        // WHEN
        platosService.prepararPlato(new DevolucionBodegaDTO(1L));

        // THEN
        verify(platosRepository, times(1)).save(any(Plato.class));
        verify(sink, times(1))
                .emit(startsWith("Plato finalizado"));
    }
    @Test
    void shouldReturnPageOfPlatoDTOsOrderedByIdDesc() {
        // GIVEN
        int pageNumber = 0;
        int limit = 2;

        Plato plato1 = new Plato();
        plato1.setId(2L);
        plato1.setReceta(Menu.getRandomMenu().getName());

        Plato plato2 = new Plato();
        plato2.setId(1L);
        plato2.setReceta(Menu.getRandomMenu().getName());

        List<Plato> platos = List.of(plato1, plato2);

        Page<Plato> pagePlatos =
                new PageImpl<>(platos, PageRequest.of(pageNumber, limit), 2);

        ArgumentCaptor<Pageable> pageableCaptor = ArgumentCaptor.forClass(Pageable.class);

        when(platosRepository.findAll(pageableCaptor.capture()))
                .thenReturn(pagePlatos);

        // WHEN
        Page<PlatoDTO> result = platosService.findAll(pageNumber, limit);

        // THEN
        assertNotNull(result);
        assertEquals(2, result.getContent().size());

        PlatoDTO dto1 = result.getContent().get(0);
        PlatoDTO dto2 = result.getContent().get(1);

        assertEquals(plato1.getReceta(), dto1.getReceta());
        assertEquals(plato2.getReceta(), dto2.getReceta());

        // ✔️ Verificamos pageable
        Pageable pageable = pageableCaptor.getValue();
        assertEquals(pageNumber, pageable.getPageNumber());
        assertEquals(limit, pageable.getPageSize());
        assertEquals(
                Sort.by(Sort.Direction.DESC, "id"),
                pageable.getSort()
        );
    }
    @Test
    void shouldReturnEmptyPageWhenNoPlatos() {
        // GIVEN
        when(platosRepository.findAll(any(Pageable.class)))
                .thenReturn(Page.empty());

        // WHEN
        Page<PlatoDTO> result = platosService.findAll(0, 10);

        // THEN
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

}

