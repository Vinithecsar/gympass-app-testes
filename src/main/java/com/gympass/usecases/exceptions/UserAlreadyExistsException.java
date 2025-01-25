package com.gympass.usecases.exceptions;

public class UserAlreadyExistsException extends Exception {
  public UserAlreadyExistsException() {
    super("E-mail já cadastrado.");
  }

}
