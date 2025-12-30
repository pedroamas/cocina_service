package com.example.cocina_service.domain;

import com.example.cocina_service.domain.dtos.PlatoDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Plato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String receta;

    private LocalDateTime horaPedido;

    private LocalDateTime horaPedidoIngredientes;

    private LocalDateTime horaReciboIngredientes;

    private LocalDateTime horaFinalizado;

    private boolean finalizado;

    @PrePersist
    public void asignarHoraPedido() {
        this.horaPedido = LocalDateTime.now();
    }

    public PlatoDTO toDTO() {
        PlatoDTO platoDTO = new PlatoDTO();
        platoDTO.setId(this.id);
        platoDTO.setReceta(this.receta);
        platoDTO.setHoraPedido(this.horaPedido);
        platoDTO.setHoraPedidoIngredientes(this.horaPedidoIngredientes);
        platoDTO.setHoraReciboIngredientes(this.horaReciboIngredientes);
        platoDTO.setHoraFinalizado(this.horaFinalizado);
        platoDTO.setFinalizado(this.finalizado);
        return platoDTO;
    }

}
