package com.gympass.unit.usecases;

import com.gympass.model.CheckIn;
import com.gympass.repositories.inmemory.InMemoryCheckInsRepository;
import com.gympass.usecases.GetMetricasUsuarioUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetMetricasUsuarioUseCaseTest {

  private InMemoryCheckInsRepository checkInsRepository;
  private GetMetricasUsuarioUseCase getMetricasUsuarioUseCase;

  @BeforeEach
  void setUp() {
    checkInsRepository = new InMemoryCheckInsRepository();
    getMetricasUsuarioUseCase = new GetMetricasUsuarioUseCase(checkInsRepository);
  }

  @Test
  void deveObterContagemDeCheckInsDasMetricas() {
    checkInsRepository.create(new CheckIn("user-01", "gym-01"));
    checkInsRepository.create(new CheckIn("user-01", "gym-02"));

    int resultado = getMetricasUsuarioUseCase.execute("user-01");

    assertEquals(2, resultado);
  }
}
