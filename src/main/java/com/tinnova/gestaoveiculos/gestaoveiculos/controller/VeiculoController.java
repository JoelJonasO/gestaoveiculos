package com.tinnova.gestaoveiculos.gestaoveiculos.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tinnova.gestaoveiculos.gestaoveiculos.DTO.MarcaDTO;
import com.tinnova.gestaoveiculos.gestaoveiculos.DTO.VeiculoDTO;
import com.tinnova.gestaoveiculos.gestaoveiculos.model.Veiculo;
import com.tinnova.gestaoveiculos.gestaoveiculos.service.impl.VeiculoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

  @Autowired
  private VeiculoServiceImpl veiculoService;

  @GetMapping
  public ResponseEntity<Page<Veiculo>> listarVeiculosVendidos(@RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    Page<Veiculo> veiculos = veiculoService.listaVeiculosPaginado(page, size);
    return new ResponseEntity<>(veiculos, HttpStatus.OK);
  }

  @GetMapping("/filtrar")
  public ResponseEntity<Page<Veiculo>> listarVeiculosFiltrado(@RequestParam(required = true) String marca,
      @RequestParam(required = true) Integer ano,
      @RequestParam(required = true) String cor,
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size) {
    Page<Veiculo> veiculos = veiculoService.filtrarVeiculosPorAnoMarcaCor(ano, marca, cor, page, size);
    return new ResponseEntity<>(veiculos, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Veiculo> buscarVeiculo(
      @PathVariable Long id) {
    Veiculo veiculo = veiculoService.buscarVeiculoId(id);
    return new ResponseEntity<>(veiculo, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<VeiculoDTO> cadastrarVeiculo(@RequestBody VeiculoDTO veiculoDto) {
    VeiculoDTO novoVeiculo = veiculoService.cadastrarVeiculo(veiculoDto);
    return new ResponseEntity<>(novoVeiculo, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<VeiculoDTO> atualizarVeiculo(@PathVariable Long id,
      @RequestBody VeiculoDTO veiculoDto) {
    VeiculoDTO veiculo = veiculoService.atualizarVeiculo(id, veiculoDto);
    return new ResponseEntity<>(veiculo, HttpStatus.OK);
  }

  @PatchMapping("/update-veiculo/marca")
  public ResponseEntity<VeiculoDTO> atualizarParcialmenteVeiculo(@RequestParam(required = true) Long id,
      @RequestBody MarcaDTO marcaDto) {
    VeiculoDTO veiculoDto = veiculoService.atualizarParcialmenteVeiculo(id, marcaDto);
    return new ResponseEntity<>(veiculoDto, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void excluirVeiculo(@PathVariable Long id) {
    veiculoService.excluirVeiculo(id);
  }

}
