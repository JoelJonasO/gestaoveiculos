package com.tinnova.gestaoveiculos.gestaoveiculos.DTO;

import com.tinnova.gestaoveiculos.gestaoveiculos.model.Marca;

import lombok.Data;

@Data
public class MarcaDTO {
  private Long id;
  private String nome;

  public static MarcaDTO fromMarca(Marca marca) {
    MarcaDTO dto = new MarcaDTO();
    dto.setId(marca.getId());
    dto.setNome(marca.getNome());
    return dto;
  }
}
