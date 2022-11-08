package com.bb.envifastservice.application.port.out;

import com.bb.envifastservice.adapter.out.persistence.dtos.FlightMap;

import java.util.List;

public interface ListDayFlightsPort {
    List<FlightMap> listDayFlights(String fecha, Integer paraSim);
}
