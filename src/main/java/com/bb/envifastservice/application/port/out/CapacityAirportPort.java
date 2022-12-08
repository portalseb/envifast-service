package com.bb.envifastservice.application.port.out;

import java.io.IOException;

public interface CapacityAirportPort {
    int capacityAirport(String fecha, String timeInf,String code)  throws IOException;
}
