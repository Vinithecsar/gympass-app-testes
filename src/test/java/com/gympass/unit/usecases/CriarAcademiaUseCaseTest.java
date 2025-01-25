package com.gympass.unit.usecases;

import static org.junit.jupiter.api.Assertions.assertTrue;

import com.gympass.model.Academia;
import com.gympass.repositories.inmemory.InMemoryAcademiasRepository;
import com.gympass.usecases.CriarAcademiaUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CriarAcademiaUseCaseTest {

  private InMemoryAcademiasRepository academiasRepository;
  private CriarAcademiaUseCase criarAcademiaUseCase;

  @BeforeEach
  void setUp() {
    academiasRepository = new InMemoryAcademiasRepository();
    criarAcademiaUseCase = new CriarAcademiaUseCase(academiasRepository);
  }

  @Test
  void deveCriarAcademia() {
    Academia academia = criarAcademiaUseCase.execute("Java Gym", "Academia de Java", "123456789", -27.2092052,
        -49.6401091);

    assertTrue(academia.getId() != null && !academia.getId().isEmpty());
  }
}
