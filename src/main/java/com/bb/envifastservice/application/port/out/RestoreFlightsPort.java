package com.bb.envifastservice.application.port.out;

public interface RestoreFlightsPort {
    void restoreFlights(String fecha,Integer dias, Integer paraSim);
}
