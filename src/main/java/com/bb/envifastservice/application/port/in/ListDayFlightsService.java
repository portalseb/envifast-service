package com.bb.envifastservice.application.port.in;

import com.bb.envifastservice.adapter.out.persistence.dtos.FlightMap;

import java.util.List;

public interface ListDayFlightsService {
    List<FlightMap> listDayFlights(String fecha, Integer paraSim);
}
