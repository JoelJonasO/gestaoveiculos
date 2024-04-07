package com.tinnova.gestaoveiculos.gestaoveiculos.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.tinnova.gestaoveiculos.gestaoveiculos.model.Veiculo;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
  Page<Veiculo> findByVendidoTrueOrderByUpdatedDesc(Pageable pageable);

  @Query("SELECT v FROM Veiculo v WHERE v.ano = :ano AND v.cor = :cor AND v.marca.nome = :marca")
  Page<Veiculo> findByAnoAndMarcaAnCor(@Param("ano") int ano, @Param("cor") String cor, @Param("marca") String marca,
      Pageable pageable);

  Veiculo findById(long id);

  @SuppressWarnings("unchecked")
  Veiculo save(Veiculo veiculo);

  void delete(Veiculo veiculo);
}
