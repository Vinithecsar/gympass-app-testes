package com.gympass.unit.usecases;

import com.gympass.model.CheckIn;
import com.gympass.repositories.inmemory.InMemoryCheckInsRepository;
import com.gympass.usecases.ValidarCheckInUseCase;
import com.gympass.usecases.exceptions.LateCheckInValidationException;
import com.gympass.usecases.exceptions.ResourceNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ValidarCheckInUseCaseTest {

  private InMemoryCheckInsRepository checkInsRepository;
  private ValidarCheckInUseCase validarCheckInUseCase;

  @BeforeEach
  void setUp() {
    checkInsRepository = new InMemoryCheckInsRepository();
    validarCheckInUseCase = new ValidarCheckInUseCase(checkInsRepository);
  }

  @Test
  void deveValidarCheckIn() throws Exception {
    CheckIn createdCheckIn = checkInsRepository.create(new CheckIn("user-01", "gym-01"));

    CheckIn resultado = validarCheckInUseCase.execute(createdCheckIn.getId());

    assertNotNull(resultado);
    assertNotNull(checkInsRepository.findById(createdCheckIn.getId()).get());
  }

  @Test
  void naoDeveValidarCheckInInexistente() {
    assertThrows(ResourceNotFoundException.class, () -> {
      validarCheckInUseCase.execute("check-in-id-inexistente");
    });
  }

  @Test
  void naoDeveValidarCheckInDepoisDe20Minutos() {
    CheckIn createdCheckIn = checkInsRepository
        .create(new CheckIn("user-01", LocalDateTime.parse("2025-01-24T12:00:00"), "gym-01"));

    assertThrows(LateCheckInValidationException.class, () -> {
      validarCheckInUseCase.execute(createdCheckIn.getId(), LocalDateTime.parse("2025-01-24T12:30:00"));
    });
  }
}
