package com.example.cocina_service.repositories;

import com.example.cocina_service.domain.Plato;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlatosRepository extends JpaRepository<Plato,Long> {

}
