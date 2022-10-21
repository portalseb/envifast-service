package com.bb.envifastservice.application.port.in;

import com.bb.envifastservice.algo.ArcoAeropuerto;

import java.time.LocalDate;
import java.util.List;

public interface ListAllFlightsService {
    List<ArcoAeropuerto> listAllFlights(String fecha);
}
