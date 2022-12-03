package com.bb.envifastservice.application.port.in;

import java.io.IOException;

public interface CapacityFlightService {
    int capacityFlights(String fecha, Integer origenId, Integer destinoId) throws IOException;
}
