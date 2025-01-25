package com.gympass.repositories.inmemory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gympass.model.CheckIn;
import com.gympass.repositories.CheckInsRepository;

public class InMemoryCheckInsRepository implements CheckInsRepository {
  private final List<CheckIn> checkIns;

  public InMemoryCheckInsRepository() {
    this.checkIns = new ArrayList<>();
  }

  @Override
  public Optional<CheckIn> findById(String id) {
    return checkIns.stream()
        .filter(checkIn -> checkIn.getId().equals(id))
        .findFirst();
  }

  @Override
  public Optional<CheckIn> findByUserIdOnDate(String userId, LocalDate date) {

    LocalDateTime inicioDoDia = date.atStartOfDay();
    LocalDateTime fimDoDia = date.atTime(LocalTime.MAX);

    return checkIns.stream()
        .filter(checkIn -> checkIn.getUsuarioId().equals(userId))
        .filter(checkIn -> !checkIn.getCriadoEm().toLocalDate().isBefore(inicioDoDia.toLocalDate())
            && !checkIn.getCriadoEm().toLocalDate().isAfter(fimDoDia.toLocalDate()))
        .findFirst();
  }

  @Override
  public List<CheckIn> findManyByUserId(String userId) {
    return checkIns.stream()
        .filter(checkIn -> checkIn.getUsuarioId().equals(userId))
        .toList();
  }

  @Override
  public int countByUserId(String userId) {
    return (int) checkIns.stream()
        .filter(checkIn -> checkIn.getUsuarioId().equals(userId))
        .count();
  }

  @Override
  public CheckIn create(CheckIn checkIn) {
    this.checkIns.add(checkIn);
    return checkIn;
  }

  @Override
  public CheckIn save(CheckIn checkIn) {
    int index = -1;
    for (int i = 0; i < checkIns.size(); i++) {
      if (checkIns.get(i).getId().equals(checkIn.getId())) {
        index = i;
        break;
      }
    }

    if (index >= 0) {
      checkIns.set(index, checkIn);
      return checkIn;
    }

    return null;
  }

}
