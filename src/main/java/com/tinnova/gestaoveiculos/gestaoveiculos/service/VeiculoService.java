package com.tinnova.gestaoveiculos.gestaoveiculos.service;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;

import com.tinnova.gestaoveiculos.gestaoveiculos.DTO.MarcaDTO;
import com.tinnova.gestaoveiculos.gestaoveiculos.DTO.VeiculoDTO;
import com.tinnova.gestaoveiculos.gestaoveiculos.model.Veiculo;

public interface VeiculoService {
  Page<Veiculo> listaVeiculosPaginado(int page, int size);

  Page<Veiculo> filtrarVeiculosPorAnoMarcaCor(int ano, String marca, String cor, int page, int size);

  Veiculo buscarVeiculoId(Long id);

  VeiculoDTO cadastrarVeiculo(VeiculoDTO veiculoDto);

  VeiculoDTO atualizarVeiculo(Long id, VeiculoDTO veiculoDto);

  VeiculoDTO atualizarParcialmenteVeiculo(Long id, MarcaDTO marcaDto);

  void excluirVeiculo(Long id);
}
