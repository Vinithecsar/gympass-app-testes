package com.gympass.usecases.exceptions;

public class MaxDistanceException extends Exception {
  public MaxDistanceException() {
    super("Distância máxima atingida.");
  }
}
