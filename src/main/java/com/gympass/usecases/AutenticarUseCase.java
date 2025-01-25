package com.gympass.usecases;

import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;

import com.gympass.model.Usuario;
import com.gympass.repositories.UsuariosRepository;
import com.gympass.usecases.exceptions.InvalidCredentialsException;

public class AutenticarUseCase {
  private UsuariosRepository usuariosRepository;

  public AutenticarUseCase(UsuariosRepository usuariosRepository) {
    this.usuariosRepository = usuariosRepository;
  }

  public Usuario execute(String email, String senha) throws InvalidCredentialsException {
    Optional<Usuario> usuario = usuariosRepository.findByEmail(email);

    if (usuario.isEmpty()) {
      throw new InvalidCredentialsException();
    }

    boolean senhaCorreta = BCrypt.checkpw(senha, usuario.get().getSenhaHash());

    if (!senhaCorreta) {
      throw new InvalidCredentialsException();
    }

    return usuario.get();
  }
}
