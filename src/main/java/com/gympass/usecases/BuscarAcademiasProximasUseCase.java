package com.gympass.usecases;

import java.util.List;

import com.gympass.model.Academia;
import com.gympass.repositories.AcademiasRepository;
import com.gympass.utils.Coordenada;

public class BuscarAcademiasProximasUseCase {

  private AcademiasRepository academiasRepository;

  public BuscarAcademiasProximasUseCase(AcademiasRepository academiasRepository) {
    this.academiasRepository = academiasRepository;
  }

  public List<Academia> execute(double usuarioLatitude, double usuarioLongitude) {
    Coordenada coordenadaUsuario = new Coordenada(usuarioLatitude, usuarioLongitude);
    return academiasRepository.findManyNearby(coordenadaUsuario);
  }
}
