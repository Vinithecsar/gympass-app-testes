package com.gympass.usecases;

import com.gympass.model.Academia;
import com.gympass.repositories.AcademiasRepository;

public class CriarAcademiaUseCase {

  private AcademiasRepository academiasRepository;

  public CriarAcademiaUseCase(AcademiasRepository academiasRepository) {
    this.academiasRepository = academiasRepository;
  }

  public Academia execute(String nome, String descricao, String telefone, double academiaLatitude,
      double academiaLongitude) {
    Academia academia = new Academia(nome, descricao, telefone, academiaLatitude, academiaLongitude);
    return academiasRepository.create(academia);
  }
}
