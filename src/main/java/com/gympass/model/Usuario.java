package com.gympass.model;

import java.time.LocalDateTime;
import java.util.UUID;

import com.gympass.enums.FuncaoEnum;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Usuario {
  private String id;
  private String nome;
  private String email;
  private String senhaHash;
  private FuncaoEnum funcao;
  private LocalDateTime criadoEm;

  public Usuario(String nome, String email, String senhaHash) {
    this.id = UUID.randomUUID().toString();
    this.nome = nome;
    this.email = email;
    this.senhaHash = senhaHash;
    this.funcao = FuncaoEnum.MEMBRO;
    this.criadoEm = LocalDateTime.now();
  }

  public Usuario(String nome, String email, String senhaHash, FuncaoEnum funcao) {
    this.id = UUID.randomUUID().toString();
    this.nome = nome;
    this.email = email;
    this.senhaHash = senhaHash;
    this.funcao = funcao;
    this.criadoEm = LocalDateTime.now();
  }
}
