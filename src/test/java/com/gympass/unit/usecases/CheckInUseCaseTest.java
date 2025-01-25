package com.gympass.unit.usecases;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.*;

import com.gympass.model.Academia;
import com.gympass.model.CheckIn;
import com.gympass.repositories.inmemory.InMemoryAcademiasRepository;
import com.gympass.repositories.inmemory.InMemoryCheckInsRepository;
import com.gympass.usecases.CheckInUseCase;
import com.gympass.usecases.exceptions.MaxDistanceException;
import com.gympass.usecases.exceptions.MaxNumberOfCheckInsException;
import com.gympass.usecases.exceptions.ResourceNotFoundException;

class CheckInUseCaseTest {

  private InMemoryCheckInsRepository checkInsRepository;
  private InMemoryAcademiasRepository academiasRepository;
  private CheckInUseCase checkInUseCase;

  @BeforeEach
  void setUp() {
    checkInsRepository = new InMemoryCheckInsRepository();
    academiasRepository = new InMemoryAcademiasRepository();
    checkInUseCase = new CheckInUseCase(checkInsRepository, academiasRepository);

    Academia academia = new Academia("Java Gym", "Descricao", "12345678", 0.0, 0.0);
    academia.setId("gym-01");
    academiasRepository.create(academia);
  }

  @Test
  void devePermitirFazerCheckIn() throws ResourceNotFoundException, MaxDistanceException, MaxNumberOfCheckInsException {
    CheckIn checkIn = checkInUseCase.execute("user-01", "gym-01", 0.0, 0.0);

    assertNotNull(checkIn.getId());
  }

  @Test
  void naoDevePermitirFazerCheckInDuasVezesNoMesmoDia()
      throws ResourceNotFoundException, MaxDistanceException, MaxNumberOfCheckInsException {
    checkInUseCase.execute("user-01", "gym-01", 0.0, 0.0);

    assertThrows(MaxNumberOfCheckInsException.class, () -> {
      checkInUseCase.execute("user-01", "gym-01", 0.0, 0.0);
    });
  }

  @Test
  void devePermitirFazerCheckInDuasVezesEmDiasDiferentes()
      throws ResourceNotFoundException, MaxDistanceException, MaxNumberOfCheckInsException {

    checkInUseCase.execute("user-01", "gym-01", 0.0, 0.0, LocalDate.parse("2025-01-20"));

    CheckIn checkIn = checkInUseCase.execute("user-01", "gym-01", 0.0, 0.0, LocalDate.parse("2025-01-21"));

    assertNotNull(checkIn.getId());
  }

  @Test
  void naoDevePermitirFazerCheckInEmAcademiaDistante() {
    Academia academia = new Academia("Java Gym 2", "", "",
        -27.0747279, -49.4889672);
    academia.setId("gym-02");
    academiasRepository.create(academia);

    assertThrows(MaxDistanceException.class, () -> {
      checkInUseCase.execute("user-01", "gym-02", -27.2092052, -49.6401091);
    });
  }
}
