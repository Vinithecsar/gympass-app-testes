package com.gympass.usecases;

import org.mindrot.jbcrypt.BCrypt;

import com.gympass.model.Usuario;
import com.gympass.repositories.UsuariosRepository;
import com.gympass.usecases.exceptions.UserAlreadyExistsException;

public class CadastrarUseCase {
  private UsuariosRepository usuariosRepository;

  public CadastrarUseCase(UsuariosRepository usuariosRepository) {
    this.usuariosRepository = usuariosRepository;
  }

  public Usuario execute(String nome, String email, String senha) throws UserAlreadyExistsException {
    String senhaHash = BCrypt.hashpw(senha, BCrypt.gensalt(6));

    boolean usuarioComMesmoEmail = usuariosRepository.findByEmail(email).isPresent();

    if (usuarioComMesmoEmail) {
      throw new UserAlreadyExistsException();
    }

    Usuario usuario = new Usuario(nome, email, senhaHash);
    return usuariosRepository.create(usuario);
  }
}
