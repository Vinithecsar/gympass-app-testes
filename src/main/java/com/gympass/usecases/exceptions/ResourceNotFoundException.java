package com.gympass.usecases.exceptions;

public class ResourceNotFoundException extends Exception {
  public ResourceNotFoundException() {
    super("Recurso não encontrado.");
  }

}
