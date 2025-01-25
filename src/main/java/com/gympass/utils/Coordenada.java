package com.gympass.utils;

import lombok.Data;

@Data
public class Coordenada {
  private double latitude;
  private double longitude;

  public Coordenada(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }
}
