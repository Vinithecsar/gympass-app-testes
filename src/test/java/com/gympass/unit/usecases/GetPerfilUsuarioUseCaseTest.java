package com.gympass.unit.usecases;

import com.gympass.model.Usuario;
import com.gympass.repositories.inmemory.InMemoryUsuariosRepository;
import com.gympass.usecases.GetPerfilUsuarioUseCase;
import com.gympass.usecases.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetPerfilUsuarioUseCaseTest {

  private InMemoryUsuariosRepository usuariosRepository;
  private GetPerfilUsuarioUseCase getPerfilUsuarioUseCase;

  @BeforeEach
  void setUp() {
    usuariosRepository = new InMemoryUsuariosRepository();
    getPerfilUsuarioUseCase = new GetPerfilUsuarioUseCase(usuariosRepository);
  }

  @Test
  void deveObterPerfilDoUsuario() throws Exception {
    Usuario usuarioCriado = usuariosRepository.create(new Usuario("John Doe", "johndoe@example.com", "hashedPassword"));

    Usuario resultado = getPerfilUsuarioUseCase.execute(usuarioCriado.getId());

    assertEquals("John Doe", resultado.getNome());
  }

  @Test
  void naoDeveObterPerfilComIdIncorreto() {
    assertThrows(ResourceNotFoundException.class, () -> {
      getPerfilUsuarioUseCase.execute("id-nao-existente");
    });
  }
}
