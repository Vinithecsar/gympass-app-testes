package com.gympass.model;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class CheckIn {
  private String id;
  private LocalDateTime criadoEm;
  private LocalDateTime validadoEm;
  private String usuarioId;
  private String academiaId;

  public CheckIn(String usuarioId, String academiaId) {
    this.id = UUID.randomUUID().toString();
    this.criadoEm = LocalDateTime.now();
    this.usuarioId = usuarioId;
    this.academiaId = academiaId;
  }

  public CheckIn(String usuarioId, String academiaId, LocalDateTime validadoEm) {
    this.id = UUID.randomUUID().toString();
    this.criadoEm = LocalDateTime.now();
    this.usuarioId = usuarioId;
    this.academiaId = academiaId;
    this.validadoEm = validadoEm;
  }

  public CheckIn(String usuarioId, LocalDateTime criadoEm, String academiaId) {
    this.id = UUID.randomUUID().toString();
    this.criadoEm = criadoEm;
    this.usuarioId = usuarioId;
    this.academiaId = academiaId;
  }
}
