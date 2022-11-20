package com.bb.envifastservice.application.port.in;

public interface RestoreFlightsService {
    void restoreFlights(String fecha,Integer dias, Integer paraSim);
}
