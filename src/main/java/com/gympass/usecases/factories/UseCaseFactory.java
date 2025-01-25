package com.gympass.usecases.factories;

import com.gympass.repositories.inmemory.*;
import com.gympass.usecases.*;

public final class UseCaseFactory {
  private static final InMemoryAcademiasRepository inMemoryAcademiasRepository = new InMemoryAcademiasRepository();
  private static final InMemoryCheckInsRepository inMemoryCheckInsRepository = new InMemoryCheckInsRepository();
  private static final InMemoryUsuariosRepository inMemoryUsuariosRepository = new InMemoryUsuariosRepository();

  private UseCaseFactory() {
  }

  public static AutenticarUseCase makeAutenticarUseCase() {
    return new AutenticarUseCase(inMemoryUsuariosRepository);
  }

  public static BuscarAcademiasProximasUseCase makeBuscarAcademiasProximasUseCase() {
    return new BuscarAcademiasProximasUseCase(inMemoryAcademiasRepository);
  }

  public static BuscarHistoricoCheckInsDoUsuarioUseCase makeBuscarHistoricoCheckInsDoUsuarioUseCase() {
    return new BuscarHistoricoCheckInsDoUsuarioUseCase(inMemoryCheckInsRepository);
  }

  public static CadastrarUseCase makeCadastrarUseCase() {
    return new CadastrarUseCase(inMemoryUsuariosRepository);
  }

  public static CheckInUseCase makeCheckInUseCase() {
    return new CheckInUseCase(inMemoryCheckInsRepository, inMemoryAcademiasRepository);
  }

  public static CriarAcademiaUseCase makeCriarAcademiaUseCase() {
    return new CriarAcademiaUseCase(inMemoryAcademiasRepository);
  }

  public static GetMetricasUsuarioUseCase makeGetMetricasUsuarioUseCase() {
    return new GetMetricasUsuarioUseCase(inMemoryCheckInsRepository);
  }

  public static GetPerfilUsuarioUseCase makeGetPerfilUsuarioUseCase() {
    return new GetPerfilUsuarioUseCase(inMemoryUsuariosRepository);
  }

  public static PesquisarAcademiasUseCase makePesquisarAcademiasUseCase() {
    return new PesquisarAcademiasUseCase(inMemoryAcademiasRepository);
  }

  public static ValidarCheckInUseCase makeValidarCheckInUseCase() {
    return new ValidarCheckInUseCase(inMemoryCheckInsRepository);
  }

}
