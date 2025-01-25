package com.gympass.usecases;

import com.gympass.repositories.CheckInsRepository;

public class GetMetricasUsuarioUseCase {

  private CheckInsRepository checkInsRepository;

  public GetMetricasUsuarioUseCase(CheckInsRepository checkInsRepository) {
    this.checkInsRepository = checkInsRepository;
  }

  public int execute(String usuarioId) {
    return checkInsRepository.countByUserId(usuarioId);
  }
}
