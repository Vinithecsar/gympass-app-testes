package com.gympass.repositories;

import java.util.List;
import java.util.Optional;

import com.gympass.model.Academia;
import com.gympass.utils.Coordenada;

public interface AcademiasRepository {
  Optional<Academia> findById(String id);

  List<Academia> findManyNearby(Coordenada coordenada);

  List<Academia> searchMany(String query);

  Academia create(Academia academia);
}
