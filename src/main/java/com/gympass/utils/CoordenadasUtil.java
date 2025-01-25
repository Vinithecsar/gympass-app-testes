package com.gympass.utils;

public final class CoordenadasUtil {

  private CoordenadasUtil() {
  }

  public static double getDistanciaEntreCoordenadas(Coordenada de, Coordenada para) {
    if (de.getLatitude() == para.getLatitude() && de.getLongitude() == para.getLongitude()) {
      return 0;
    }

    double deRadiano = (Math.PI * de.getLatitude()) / 180;
    double paraRadiano = (Math.PI * para.getLatitude()) / 180;

    double theta = de.getLongitude() - para.getLongitude();
    double thetaRadiano = (Math.PI * theta) / 180;

    double distancia = Math.sin(deRadiano) * Math.sin(paraRadiano)
        + Math.cos(deRadiano) * Math.cos(paraRadiano) * Math.cos(thetaRadiano);

    if (distancia > 1) {
      distancia = 1;
    }

    distancia = Math.acos(distancia);
    distancia = (distancia * 180) / Math.PI;
    distancia = distancia * 60 * 1.1515;
    distancia = distancia * 1.609344;

    return distancia; // em quilômetros
  }
}
