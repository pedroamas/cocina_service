package com.example.cocina_service.services;

import com.example.cocina_service.common.Menu;
import com.example.cocina_service.common.Recipe;
import com.example.cocina_service.domain.Plato;
import com.example.cocina_service.domain.dtos.SolicitudBodegaDTO;
import com.example.cocina_service.repositories.PlatosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeService {
    @Autowired
    ProductorKafka productorKafka;

    @Autowired
    PlatosRepository platosRepository;

    public void newPlate(){
        Plato plato = new Plato();
        Recipe recipe = Menu.getRandomMenu();
        plato.setReceta(recipe.getName());
        Long idPlato = platosRepository.save(plato).getId();
        productorKafka.pedirABodega(new SolicitudBodegaDTO(idPlato,recipe));

    }


}
