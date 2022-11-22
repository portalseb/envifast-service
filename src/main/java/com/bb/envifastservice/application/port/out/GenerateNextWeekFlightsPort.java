package com.bb.envifastservice.application.port.out;

import java.io.IOException;

public interface GenerateNextWeekFlightsPort {
    void generateNextWeekFlights(String fecha, Integer dias, Integer paraSim) throws IOException;
}
