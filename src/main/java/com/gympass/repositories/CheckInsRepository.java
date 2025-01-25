package com.gympass.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.gympass.model.CheckIn;

public interface CheckInsRepository {
  Optional<CheckIn> findById(String id);

  Optional<CheckIn> findByUserIdOnDate(String userId, LocalDate date);

  List<CheckIn> findManyByUserId(String userId);

  int countByUserId(String userId);

  CheckIn create(CheckIn checkIn);

  CheckIn save(CheckIn checkIn);
}
