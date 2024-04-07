package com.tinnova.gestaoveiculos.gestaoveiculos.model;

import java.time.LocalDateTime;

import com.tinnova.gestaoveiculos.gestaoveiculos.DTO.VeiculoDTO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Veiculo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String veiculo;
  private Integer ano;
  private String cor;
  private String descricao;
  private Boolean vendido;
  private LocalDateTime created;
  private LocalDateTime updated;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "marca_id", referencedColumnName = "id")
  private Marca marca;

  public VeiculoDTO toDto() {
    VeiculoDTO dto = new VeiculoDTO();
    dto.setId(this.id);
    dto.setVeiculo(this.veiculo);
    dto.setAno(this.ano);
    dto.setCor(this.cor);
    dto.setDescricao(this.descricao);
    dto.setVendido(this.vendido);
    dto.setMarcaId(this.marca.getId());

    return dto;
  }

}
