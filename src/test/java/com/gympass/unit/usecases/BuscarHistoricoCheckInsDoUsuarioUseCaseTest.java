package com.gympass.unit.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.gympass.model.CheckIn;
import com.gympass.repositories.inmemory.InMemoryCheckInsRepository;
import com.gympass.usecases.BuscarHistoricoCheckInsDoUsuarioUseCase;

class BuscarHistoricoCheckInsDoUsuarioUseCaseTest {
  private InMemoryCheckInsRepository checkInsRepository;
  private BuscarHistoricoCheckInsDoUsuarioUseCase buscarHistoricoCheckInsDoUsuarioUseCase;

  @BeforeEach
  void setUp() {
    checkInsRepository = new InMemoryCheckInsRepository();
    buscarHistoricoCheckInsDoUsuarioUseCase = new BuscarHistoricoCheckInsDoUsuarioUseCase(checkInsRepository);
  }

  @Test
  void deveRetornarHistoricoDeCheckIns() {
    checkInsRepository.create(new CheckIn("user-01", "gym-01"));
    checkInsRepository.create(new CheckIn("user-01", "gym-02"));

    List<CheckIn> checkIns = buscarHistoricoCheckInsDoUsuarioUseCase.execute("user-01");

    assertEquals(2, checkIns.size());
    assertTrue(checkIns.stream().anyMatch(checkIn -> checkIn.getAcademiaId().equals("gym-01")));
    assertTrue(checkIns.stream().anyMatch(checkIn -> checkIn.getAcademiaId().equals("gym-02")));
  }
}
