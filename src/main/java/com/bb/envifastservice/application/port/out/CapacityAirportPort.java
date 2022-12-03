package com.bb.envifastservice.application.port.out;

import java.io.IOException;

public interface CapacityAirportPort {
    void capacityAirport(String fecha, String timeInf,String timeSup, Integer forSim) throws IOException;
}
