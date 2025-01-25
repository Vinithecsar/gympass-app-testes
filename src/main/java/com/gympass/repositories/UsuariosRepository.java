package com.gympass.repositories;

import java.util.Optional;

import com.gympass.model.Usuario;

public interface UsuariosRepository {
  Optional<Usuario> findById(String id);

  Optional<Usuario> findByEmail(String email);

  Usuario create(Usuario usuario);
}
