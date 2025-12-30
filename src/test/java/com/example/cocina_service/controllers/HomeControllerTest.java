package com.example.cocina_service.controllers;

import com.example.cocina_service.TriggerSink;
import com.example.cocina_service.common.Menu;
import com.example.cocina_service.domain.dtos.PlatoDTO;
import com.example.cocina_service.services.PlatosService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = HomeController.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class)
@ActiveProfiles("test")
public class HomeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PlatosService platosService;

    @MockitoBean
    TriggerSink sink;

    @Test
    void shouldReturnNewPlateOk() throws Exception {
        mockMvc.perform(get("/new-plate").header("X-API-KEY", "test-api-key"))
                .andExpect(status().isOk())
                .andExpect(content().string("Nuevo plato ordenado"));
        verify(platosService).newPlate();
    }
    @Test
    void shouldReturnPagedPlatosWithDefaults() throws Exception {
        // GIVEN
        PlatoDTO dto = new PlatoDTO();
        dto.setId(1L);
        dto.setReceta(Menu.getRandomMenu().getName());
        Page<PlatoDTO> page =
                new PageImpl<>(List.of(dto), PageRequest.of(0, 10), 1);

        when(platosService.findAll(0, 10)).thenReturn(page);

        // WHEN + THEN
        mockMvc.perform(get("/monitoring-platos").header("X-API-KEY", "test-api-key"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id").value(1L))
                .andExpect(jsonPath("$.content[0].receta").value(dto.getReceta()))
                .andExpect(jsonPath("$.size").value(10))
                .andExpect(jsonPath("$.number").value(0));

        verify(platosService).findAll(0, 10);
    }
}
