package com.bb.envifastservice.application.port.out;

public interface GenerateNextWeekFlightsPort {
    void generateNextWeekFlights(String fecha, Integer dias);
}
