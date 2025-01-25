package com.gympass.usecases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import com.gympass.model.Academia;
import com.gympass.model.CheckIn;
import com.gympass.repositories.AcademiasRepository;
import com.gympass.repositories.CheckInsRepository;
import com.gympass.usecases.exceptions.MaxDistanceException;
import com.gympass.usecases.exceptions.MaxNumberOfCheckInsException;
import com.gympass.usecases.exceptions.ResourceNotFoundException;
import com.gympass.utils.Coordenada;
import com.gympass.utils.CoordenadasUtil;

public class CheckInUseCase {
  private static final double DISTANCIA_MAXIMA_EM_QUILOMETROS = 0.1;
  private CheckInsRepository checkInsRepository;
  private AcademiasRepository academiasRepository;

  public CheckInUseCase(CheckInsRepository checkInsRepository, AcademiasRepository academiasRepository) {
    this.checkInsRepository = checkInsRepository;
    this.academiasRepository = academiasRepository;
  }

  public CheckIn execute(String usuarioId, String academiaId, double usuarioLatitude, double usuarioLongitude)
      throws ResourceNotFoundException, MaxDistanceException, MaxNumberOfCheckInsException {

    Optional<Academia> academia = academiasRepository.findById(academiaId);

    if (academia.isEmpty()) {
      throw new ResourceNotFoundException();
    }

    Coordenada coordenadaUsuario = new Coordenada(usuarioLatitude, usuarioLongitude);
    Coordenada coordenadaAcademia = new Coordenada(academia.get().getLatitude(), academia.get().getLongitude());
    double distancia = CoordenadasUtil.getDistanciaEntreCoordenadas(coordenadaUsuario, coordenadaAcademia);

    if (distancia > DISTANCIA_MAXIMA_EM_QUILOMETROS) {
      throw new MaxDistanceException();
    }

    Optional<CheckIn> checkInNoMesmoDia = checkInsRepository.findByUserIdOnDate(usuarioId, LocalDate.now());

    if (checkInNoMesmoDia.isPresent()) {
      throw new MaxNumberOfCheckInsException();
    }

    CheckIn checkIn = new CheckIn(usuarioId, academiaId);
    return checkInsRepository.create(checkIn);

  }

  public CheckIn execute(String usuarioId, String academiaId, double usuarioLatitude, double usuarioLongitude,
      LocalDate date)
      throws ResourceNotFoundException, MaxDistanceException, MaxNumberOfCheckInsException {

    Optional<Academia> academia = academiasRepository.findById(academiaId);

    if (academia.isEmpty()) {
      throw new ResourceNotFoundException();
    }

    Coordenada coordenadaUsuario = new Coordenada(usuarioLatitude, usuarioLongitude);
    Coordenada coordenadaAcademia = new Coordenada(academia.get().getLatitude(), academia.get().getLongitude());
    double distancia = CoordenadasUtil.getDistanciaEntreCoordenadas(coordenadaUsuario, coordenadaAcademia);

    if (distancia > DISTANCIA_MAXIMA_EM_QUILOMETROS) {
      throw new MaxDistanceException();
    }

    Optional<CheckIn> checkInNoMesmoDia = checkInsRepository.findByUserIdOnDate(usuarioId, date);

    if (checkInNoMesmoDia.isPresent()) {
      throw new MaxNumberOfCheckInsException();
    }

    LocalDateTime dataComHorario = date.atStartOfDay();
    CheckIn checkIn = new CheckIn(usuarioId, dataComHorario, academiaId);
    return checkInsRepository.create(checkIn);

  }
}
