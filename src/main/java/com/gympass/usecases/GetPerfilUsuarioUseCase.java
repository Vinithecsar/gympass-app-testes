package com.gympass.usecases;

import java.util.Optional;

import com.gympass.model.Usuario;
import com.gympass.repositories.UsuariosRepository;
import com.gympass.usecases.exceptions.ResourceNotFoundException;

public class GetPerfilUsuarioUseCase {
  private UsuariosRepository usuariosRepository;

  public GetPerfilUsuarioUseCase(UsuariosRepository usuariosRepository) {
    this.usuariosRepository = usuariosRepository;
  }

  public Usuario execute(String usuarioId) throws ResourceNotFoundException {
    Optional<Usuario> usuario = usuariosRepository.findById(usuarioId);

    if (usuario.isEmpty()) {
      throw new ResourceNotFoundException();
    }

    return usuario.get();
  }
}
