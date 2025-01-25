package com.gympass.usecases.exceptions;

public class MaxNumberOfCheckInsException extends Exception {
  public MaxNumberOfCheckInsException() {
    super("Número máximo de check-ins atingido.");
  }

}
