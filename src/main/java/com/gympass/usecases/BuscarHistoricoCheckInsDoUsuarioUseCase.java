package com.gympass.usecases;

import java.util.List;

import com.gympass.model.CheckIn;
import com.gympass.repositories.CheckInsRepository;

public class BuscarHistoricoCheckInsDoUsuarioUseCase {

  private CheckInsRepository checkInsRepository;

  public BuscarHistoricoCheckInsDoUsuarioUseCase(CheckInsRepository checkInsRepository) {
    this.checkInsRepository = checkInsRepository;
  }

  public List<CheckIn> execute(String usuarioId) {
    return checkInsRepository.findManyByUserId(usuarioId);
  }
}
