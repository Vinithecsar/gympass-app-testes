package com.gympass.usecases;

import java.util.List;

import com.gympass.model.Academia;
import com.gympass.repositories.AcademiasRepository;

public class PesquisarAcademiasUseCase {

  private AcademiasRepository academiasRepository;

  public PesquisarAcademiasUseCase(AcademiasRepository academiasRepository) {
    this.academiasRepository = academiasRepository;
  }

  public List<Academia> execute(String query) {
    return academiasRepository.searchMany(query);
  }
}
