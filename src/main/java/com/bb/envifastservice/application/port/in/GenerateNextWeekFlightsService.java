package com.bb.envifastservice.application.port.in;

public interface GenerateNextWeekFlightsService {
    void generateNextWeekFlights(String fecha, Integer dias);
}
