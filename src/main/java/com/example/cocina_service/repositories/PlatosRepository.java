package com.example.cocina_service.repositories;

import com.example.cocina_service.domain.Plato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlatosRepository extends JpaRepository<Plato,Long> {
}
