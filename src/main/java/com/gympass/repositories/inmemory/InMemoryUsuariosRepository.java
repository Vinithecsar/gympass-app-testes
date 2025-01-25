package com.gympass.repositories.inmemory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.gympass.model.Usuario;
import com.gympass.repositories.UsuariosRepository;

public class InMemoryUsuariosRepository implements UsuariosRepository {
  private final List<Usuario> usuarios;

  public InMemoryUsuariosRepository() {
    this.usuarios = new ArrayList<>();
  }

  @Override
  public Optional<Usuario> findById(String id) {
    return usuarios.stream()
        .filter(usuario -> usuario.getId().equals(id))
        .findFirst();
  }

  @Override
  public Optional<Usuario> findByEmail(String email) {
    return usuarios.stream()
        .filter(usuario -> usuario.getEmail().equals(email))
        .findFirst();
  }

  @Override
  public Usuario create(Usuario usuario) {
    this.usuarios.add(usuario);
    return usuario;
  }

}
