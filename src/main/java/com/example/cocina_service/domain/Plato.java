package com.example.cocina_service.domain;

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

    private boolean finalizado;

    @PrePersist
    public void asignarHoraPedido() {
        this.horaPedido = LocalDateTime.now();
    }

}
