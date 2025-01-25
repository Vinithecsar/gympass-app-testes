package com.gympass.usecases.exceptions;

public class LateCheckInValidationException extends Exception {
  public LateCheckInValidationException() {
    super("O check-in apenas pode ser validado até 20 minutos depois de ser criado.");
  }
}
