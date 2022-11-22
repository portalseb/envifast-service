package com.bb.envifastservice.application.port.in;

import java.io.IOException;

public interface GenerateNextWeekFlightsService {
    void generateNextWeekFlights(String fecha, Integer dias, Integer paraSim) throws IOException;
}
