package com.bb.envifastservice.application.port.in;

import java.io.IOException;

public interface CapacityAirportService {
    int capacityAirport(String fecha, String timeInf,String code) throws IOException;
}
