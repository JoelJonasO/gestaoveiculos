package com.tinnova.gestaoveiculos.gestaoveiculos.DTO;

import java.time.LocalDateTime;

import com.tinnova.gestaoveiculos.gestaoveiculos.model.Veiculo;

import lombok.Data;

@Data
public class VeiculoDTO {
  private Long id;
  private String veiculo;
  private Integer ano;
  private String cor;
  private String descricao;
  private Boolean vendido;
  private Long marcaId;

  public static VeiculoDTO fromVeiculo(Veiculo veiculo) {
    VeiculoDTO dto = new VeiculoDTO();
    dto.setId(veiculo.getId());
    dto.setVeiculo(veiculo.getVeiculo());
    dto.setAno(veiculo.getAno());
    dto.setCor(veiculo.getCor());
    dto.setDescricao(veiculo.getDescricao());
    dto.setVendido(veiculo.getVendido());
    dto.setMarcaId(veiculo.getMarca().getId());
    return dto;
  }

  public Veiculo toVeiculo() {
    Veiculo veiculo = new Veiculo();
    veiculo.setId(this.getId());
    veiculo.setVeiculo(this.getVeiculo());
    veiculo.setAno(this.getAno());
    veiculo.setCor(this.getCor());
    veiculo.setDescricao(this.getDescricao());
    veiculo.setVendido(this.getVendido());
    return veiculo;
  }
}
