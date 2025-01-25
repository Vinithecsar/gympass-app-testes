package com.gympass.model;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Academia {
  private String id;
  private String nome;
  private String descricao;
  private String telefone;
  private double latitude;
  private double longitude;

  public Academia(String nome, String descricao, String telefone, double latitude, double longitude) {
    this.id = UUID.randomUUID().toString();
    this.nome = nome;
    this.descricao = descricao;
    this.telefone = telefone;
    this.latitude = latitude;
    this.longitude = longitude;
  }
}
