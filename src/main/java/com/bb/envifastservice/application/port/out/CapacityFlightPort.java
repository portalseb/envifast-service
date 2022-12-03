package com.bb.envifastservice.application.port.out;

import java.io.IOException;

public interface CapacityFlightPort {
    int capacityFlights(String fecha, Integer origenId, Integer destinoId) throws IOException;
}
