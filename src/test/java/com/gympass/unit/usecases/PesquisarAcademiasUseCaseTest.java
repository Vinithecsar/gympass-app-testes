package com.gympass.unit.usecases;

import com.gympass.model.Academia;
import com.gympass.repositories.inmemory.InMemoryAcademiasRepository;
import com.gympass.usecases.PesquisarAcademiasUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

class PesquisarAcademiasUseCaseTest {

  private InMemoryAcademiasRepository academiasRepository;
  private PesquisarAcademiasUseCase pesquisarAcademiasUseCase;

  @BeforeEach
  void setUp() {
    academiasRepository = new InMemoryAcademiasRepository();
    pesquisarAcademiasUseCase = new PesquisarAcademiasUseCase(academiasRepository);
  }

  @Test
  void deveProcurarAcademiasPorNome() {
    academiasRepository.create(new Academia("Java Gym", null, null, -5.9235173, -35.2628753));
    academiasRepository.create(new Academia("C++ Gym", null, null, -5.9235173, -35.2628753));

    List<Academia> resultado = pesquisarAcademiasUseCase.execute("Java");

    assertEquals(1, resultado.size());
    assertTrue(resultado.stream().anyMatch(academia -> academia.getNome().equals("Java Gym")));
  }
}
