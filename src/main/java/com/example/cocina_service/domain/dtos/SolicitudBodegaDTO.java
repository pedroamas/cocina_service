package com.example.cocina_service.domain.dtos;

import com.example.cocina_service.common.Recipe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudBodegaDTO {
    private Long idPlato;
    private Recipe recipe;
}
