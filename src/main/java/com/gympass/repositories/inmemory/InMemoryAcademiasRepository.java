package com.gympass.repositories.inmemory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gympass.model.Academia;
import com.gympass.repositories.AcademiasRepository;
import com.gympass.utils.Coordenada;
import com.gympass.utils.CoordenadasUtil;

public class InMemoryAcademiasRepository implements AcademiasRepository {
  private final List<Academia> academias;

  public InMemoryAcademiasRepository() {
    this.academias = new ArrayList<>();
  }

  @Override
  public Optional<Academia> findById(String id) {
    return academias.stream()
        .filter(academia -> academia.getId().equals(id))
        .findFirst();
  }

  @Override
  public List<Academia> findManyNearby(Coordenada coordenada) {
    return academias.stream()
        .filter(academia -> {
          double distancia = CoordenadasUtil.getDistanciaEntreCoordenadas(coordenada,
              new Coordenada(academia.getLatitude(), academia.getLongitude()));
          return distancia <= 10;
        })
        .toList();
  }

  @Override
  public List<Academia> searchMany(String query) {
    return academias.stream()
        .filter(academia -> academia.getNome().contains(query))
        .toList();
  }

  @Override
  public Academia create(Academia academia) {
    academias.add(academia);
    return academia;
  }
}
