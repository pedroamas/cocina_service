package com.example.cocina_service.domain.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class PlatoDTO {
    private Long id;

    private String receta;

    private LocalDateTime horaPedido;

    private LocalDateTime horaPedidoIngredientes;

    private LocalDateTime horaReciboIngredientes;

    private LocalDateTime horaFinalizado;

    private boolean finalizado;


}
