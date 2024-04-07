package com.tinnova.gestaoveiculos.gestaoveiculos.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.tinnova.gestaoveiculos.gestaoveiculos.DTO.MarcaDTO;
import com.tinnova.gestaoveiculos.gestaoveiculos.DTO.VeiculoDTO;
import com.tinnova.gestaoveiculos.gestaoveiculos.exception.MarcaNotFoundException;
import com.tinnova.gestaoveiculos.gestaoveiculos.exception.VeiculoNotFoundException;
import com.tinnova.gestaoveiculos.gestaoveiculos.model.Marca;
import com.tinnova.gestaoveiculos.gestaoveiculos.model.Veiculo;
import com.tinnova.gestaoveiculos.gestaoveiculos.repository.MarcaRepository;
import com.tinnova.gestaoveiculos.gestaoveiculos.repository.VeiculoRepository;
import com.tinnova.gestaoveiculos.gestaoveiculos.service.VeiculoService;

@Service
public class VeiculoServiceImpl implements VeiculoService {

  @Autowired
  private VeiculoRepository repository;

  @Autowired
  private MarcaRepository marcaRepository;

  @Override
  public Page<Veiculo> listaVeiculosPaginado(int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("updated").descending());
    return repository.findByVendidoTrueOrderByUpdatedDesc(pageable);
  }

  @Override
  public Page<Veiculo> filtrarVeiculosPorAnoMarcaCor(int ano, String marca, String cor, int page, int size) {
    Pageable pageable = PageRequest.of(page, size, Sort.by("updated").descending());
    return repository.findByAnoAndMarcaAnCor(ano, cor, marca, pageable);
  }

  @Override
  public Veiculo buscarVeiculoId(Long id) {
    Optional<Veiculo> optionalVeiculo = repository.findById(id);
    return optionalVeiculo
        .orElseThrow(() -> new VeiculoNotFoundException(String.format("Não encontrado o veículo com id %d", id)));
  }

  @Override
  public VeiculoDTO cadastrarVeiculo(VeiculoDTO veiculoDto) {
    Veiculo veiculo = veiculoDto.toVeiculo();
    veiculo.setCreated(LocalDateTime.now());
    Optional<Marca> optionalMarca = marcaRepository.findById(veiculoDto.getMarcaId());
    Marca marca = optionalMarca.orElseThrow(
        () -> new MarcaNotFoundException(String.format("Não encontrado o marca com id %d", veiculoDto.getMarcaId())));

    veiculo.setMarca(marca);

    return repository.save(veiculo).toDto();
  }

  @Override
  public VeiculoDTO atualizarVeiculo(Long id, VeiculoDTO veiculoDto) {
    Optional<Veiculo> optionalVeiculo = repository.findById(id);
    Veiculo veiculo = optionalVeiculo
        .orElseThrow(() -> new VeiculoNotFoundException(String.format("Não encontrado o veículo com id %d", id)));

    veiculo = veiculoDto.toVeiculo();
    veiculo.setId(id);
    veiculo.setCreated(veiculo.getCreated());
    veiculo.setUpdated(LocalDateTime.now());

    Optional<Marca> optionalMarca = marcaRepository.findById(veiculoDto.getMarcaId());
    Marca marca = optionalMarca
        .orElseThrow(() -> new MarcaNotFoundException(
            String.format("Não encontrado o marca com id %d", veiculoDto.getMarcaId())));
    veiculo.setMarca(marca);

    return repository.save(veiculo).toDto();
  }

  @Override
  public VeiculoDTO atualizarParcialmenteVeiculo(Long id, MarcaDTO marcaDto) {
    Veiculo veiculo = repository.findById(id)
        .orElseThrow(() -> new VeiculoNotFoundException(String.format("Não encontrado o veículo com id %d", id)));
    Marca marca = marcaRepository.findById(marcaDto.getId())
        .orElseThrow(
            () -> new MarcaNotFoundException(String.format("Não encontrado o marca com id %d", marcaDto.getId())));

    veiculo.setMarca(marca);
    Veiculo veiculoAtualizado = repository.save(veiculo);

    return VeiculoDTO.fromVeiculo(veiculoAtualizado);
  }

  @Override
  public void excluirVeiculo(Long id) {
    Veiculo veiculo = repository.findById(id)
        .orElseThrow(() -> new VeiculoNotFoundException(String.format("Não encontrado o veículo com id %d", id)));
    repository.delete(veiculo);
  }

}
