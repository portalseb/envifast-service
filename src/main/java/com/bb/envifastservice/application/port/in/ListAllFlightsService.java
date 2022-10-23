package com.bb.envifastservice.application.port.in;

import com.bb.envifastservice.adapter.out.persistence.dtos.FlightMap;
import com.bb.envifastservice.algo.ArcoAeropuerto;

import java.time.LocalDate;
import java.util.List;

public interface ListAllFlightsService {
    List<FlightMap> listAllFlights(String fecha,Integer per);
}
