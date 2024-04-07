package com.tinnova.gestaoveiculos.gestaoveiculos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tinnova.gestaoveiculos.gestaoveiculos.model.Marca;
import java.util.Optional;

public interface MarcaRepository extends JpaRepository<Marca, Long> {
  Optional<Marca> findById(Long id);
}
