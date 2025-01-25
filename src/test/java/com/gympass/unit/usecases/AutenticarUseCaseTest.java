package com.gympass.unit.usecases;

import static org.junit.jupiter.api.Assertions.*;

import com.gympass.model.Usuario;
import com.gympass.repositories.inmemory.InMemoryUsuariosRepository;
import com.gympass.usecases.AutenticarUseCase;
import com.gympass.usecases.exceptions.InvalidCredentialsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;

class AutenticarUseCaseTest {

  private InMemoryUsuariosRepository usuariosRepository;
  private AutenticarUseCase autenticarUseCase;

  @BeforeEach
  void setUp() {
    usuariosRepository = new InMemoryUsuariosRepository();
    autenticarUseCase = new AutenticarUseCase(usuariosRepository);
  }

  @Test
  void deveAutenticarComCredenciaisValidas() throws InvalidCredentialsException {
    Usuario usuario = new Usuario();
    usuario.setId("1");
    usuario.setEmail("johndoe@example.com");
    usuario.setSenhaHash(BCrypt.hashpw("123456", BCrypt.gensalt(6)));

    usuariosRepository.create(usuario);

    Usuario usuarioAutenticado = autenticarUseCase.execute("johndoe@example.com", "123456");

    assertNotNull(usuarioAutenticado);
    assertEquals("1", usuarioAutenticado.getId());
  }

  @Test
  void naoDeveAutenticarComEmailInvalido() {
    assertThrows(InvalidCredentialsException.class, () -> {
      autenticarUseCase.execute("johndoe@example.com", "123456");
    });
  }

  @Test
  void naoDeveAutenticarComSenhaInvalida() {
    Usuario usuario = new Usuario();
    usuario.setId("1");
    usuario.setEmail("johndoe@example.com");
    usuario.setSenhaHash(BCrypt.hashpw("123456", BCrypt.gensalt(6)));

    usuariosRepository.create(usuario);

    assertThrows(InvalidCredentialsException.class, () -> {
      autenticarUseCase.execute("johndoe@example.com", "senhaerrada");
    });
  }
}
