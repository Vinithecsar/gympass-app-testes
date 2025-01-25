package com.gympass.usecases;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

import com.gympass.model.CheckIn;
import com.gympass.repositories.CheckInsRepository;
import com.gympass.usecases.exceptions.LateCheckInValidationException;
import com.gympass.usecases.exceptions.ResourceNotFoundException;

public class ValidarCheckInUseCase {
  private CheckInsRepository checkInsRepository;

  public ValidarCheckInUseCase(CheckInsRepository checkInsRepository) {
    this.checkInsRepository = checkInsRepository;
  }

  public CheckIn execute(String checkInId)
      throws ResourceNotFoundException, LateCheckInValidationException {

    Optional<CheckIn> checkIn = checkInsRepository.findById(checkInId);

    if (checkIn.isEmpty()) {
      throw new ResourceNotFoundException();
    }

    long distanciaEmMinutosDaCriacaoDoCheckIn = Duration.between(checkIn.get().getCriadoEm(), LocalDateTime.now())
        .toMinutes();

    if (distanciaEmMinutosDaCriacaoDoCheckIn > 20) {
      throw new LateCheckInValidationException();
    }

    checkIn.get().setValidadoEm(LocalDateTime.now());
    return checkInsRepository.save(checkIn.get());

  }

  public CheckIn execute(String checkInId, LocalDateTime data)
      throws ResourceNotFoundException, LateCheckInValidationException {

    Optional<CheckIn> checkIn = checkInsRepository.findById(checkInId);

    if (checkIn.isEmpty()) {
      throw new ResourceNotFoundException();
    }

    long distanciaEmMinutosDaCriacaoDoCheckIn = Duration.between(checkIn.get().getCriadoEm(), data)
        .toMinutes();

    if (distanciaEmMinutosDaCriacaoDoCheckIn > 20) {
      throw new LateCheckInValidationException();
    }

    checkIn.get().setValidadoEm(data);
    return checkInsRepository.save(checkIn.get());

  }
}
