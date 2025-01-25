package com.gympass.enums;

public enum FuncaoEnum {
  ADMIN("Administrador"),
  MEMBRO("Membro");

  private String descricao;

  FuncaoEnum(String descricao) {
    this.descricao = descricao;
  }

  public String getDescricao() {
    return descricao;
  }

}
