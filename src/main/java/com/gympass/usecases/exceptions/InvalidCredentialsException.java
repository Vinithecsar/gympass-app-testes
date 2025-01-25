package com.gympass.usecases.exceptions;

public class InvalidCredentialsException extends Exception {
  public InvalidCredentialsException() {
    super("Credenciais inválidas.");
  }
}
