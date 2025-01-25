package com.gympass.unit.usecases;

import static org.junit.jupiter.api.Assertions.*;

import com.gympass.model.Academia;
import com.gympass.repositories.inmemory.InMemoryAcademiasRepository;
import com.gympass.usecases.BuscarAcademiasProximasUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class BuscarAcademiasProximasUseCaseTest {

  private InMemoryAcademiasRepository academiasRepository;
  private BuscarAcademiasProximasUseCase buscarAcademiasProximasUseCase;

  @BeforeEach
  void setUp() {
    academiasRepository = new InMemoryAcademiasRepository();
    buscarAcademiasProximasUseCase = new BuscarAcademiasProximasUseCase(academiasRepository);
  }

  @Test
  void deveBuscarApenasAcademiasProximas() {
    Academia academiaProxima = new Academia();
    academiaProxima.setId("1");
    academiaProxima.setNome("Near Gym");
    academiaProxima.setDescricao(null);
    academiaProxima.setTelefone(null);
    academiaProxima.setLatitude(-27.2092052f);
    academiaProxima.setLongitude(-49.6401091f);
    academiasRepository.create(academiaProxima);

    Academia academiaDistante = new Academia();
    academiaDistante.setId("2");
    academiaDistante.setNome("Far Gym");
    academiaDistante.setDescricao(null);
    academiaDistante.setTelefone(null);
    academiaDistante.setLatitude(-27.0610928f);
    academiaDistante.setLongitude(-49.5229501f);
    academiasRepository.create(academiaDistante);

    List<Academia> academias = buscarAcademiasProximasUseCase.execute(
        -27.2092052, -49.6401091);

    assertEquals(1, academias.size());
    assertEquals("Near Gym", academias.get(0).getNome());
  }
}
