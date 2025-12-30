package com.example.cocina_service.services;

import com.example.cocina_service.common.Menu;
import com.example.cocina_service.TriggerSink;
import com.example.cocina_service.domain.Plato;
import com.example.cocina_service.domain.dtos.PlatoDTO;
import com.example.cocina_service.repositories.PlatosRepository;
import org.example.common.Recipe;
import org.example.dtos.DevolucionBodegaDTO;
import org.example.dtos.SolicitudBodegaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PlatosService {
    @Autowired
    ProductorKafka productorKafka;

    @Autowired
    TriggerSink sink;

    @Autowired
    PlatosRepository platosRepository;

    public void newPlate(){
        Plato plato = new Plato();
        Recipe recipe = Menu.getRandomMenu();
        plato.setReceta(recipe.getName());
        plato.setHoraPedidoIngredientes(LocalDateTime.now());
        Long idPlato = platosRepository.save(plato).getId();
        sink.emit("Nuevo plato "+idPlato + " -> Pedir ingredientes a bodega");

        productorKafka.pedirABodega(new SolicitudBodegaDTO(idPlato,recipe));

    }

    public void prepararPlato(DevolucionBodegaDTO devolucionBodegaDTO) {
        Plato plato = platosRepository.findById(devolucionBodegaDTO.getIdPlato()).orElseThrow(()->new NullPointerException("Plato no encontrado"));
        System.out.println("LLego de bodega plato: " +plato.getId() + " - Receta: "+plato.getReceta());
        plato.setHoraReciboIngredientes(LocalDateTime.now());
        plato.setHoraFinalizado(LocalDateTime.now());
        plato.setFinalizado(true);
        platosRepository.save(plato);
        sink.emit("Plato finalizado " +plato.getId());

    }

    public Page<PlatoDTO> findAll(int pageNumber, int limit) {
        Pageable pageable = PageRequest.of(pageNumber, limit, Sort.by(Sort.Direction.DESC, "id"));

        return platosRepository
            .findAll(pageable)
            .map(Plato::toDTO);
    }
}


