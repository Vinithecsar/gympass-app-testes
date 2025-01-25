package com.gympass.unit.usecases;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

import com.gympass.model.Usuario;
import com.gympass.repositories.inmemory.InMemoryUsuariosRepository;
import com.gympass.usecases.CadastrarUseCase;
import com.gympass.usecases.exceptions.UserAlreadyExistsException;

class CadastrarUseCaseTest {
  private InMemoryUsuariosRepository usuariosRepository;
  private CadastrarUseCase cadastrarUseCase;

  @BeforeEach
  void setUp() {
    usuariosRepository = new InMemoryUsuariosRepository();
    cadastrarUseCase = new CadastrarUseCase(usuariosRepository);
  }

  @Test
  void deveCadastrarUsuario() throws UserAlreadyExistsException {
    Usuario usuario = cadastrarUseCase.execute("John Doe", "johndoe@example.com", "123456");

    assertEquals("John Doe", usuario.getNome());
    assertEquals("johndoe@example.com", usuario.getEmail());
    assertTrue(usuario.getId() != null && !usuario.getId().isEmpty());
  }

  @Test
  void deveHashearSenhaDoUsuario() throws UserAlreadyExistsException {
    Usuario usuario = cadastrarUseCase.execute("John Doe", "johndoe@example.com", "123456");

    assertTrue(BCrypt.checkpw("123456", usuario.getSenhaHash()));
  }

  @Test
  void naoDevePermitirCadastroComMesmoEmail() throws UserAlreadyExistsException {
    String email = "johndoe@example.com";
    cadastrarUseCase.execute("John Doe", email, "123456");

    assertThrows(UserAlreadyExistsException.class, () -> {
      cadastrarUseCase.execute("John Doe 2", email, "1234567");
    });
  }
}
