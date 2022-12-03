package com.bb.envifastservice.application.port.in;

import java.io.IOException;

public interface CapacityAirportService {
    void capacityAirport(String fecha, String timeInf,String timeSup, Integer forSim) throws IOException;
}
