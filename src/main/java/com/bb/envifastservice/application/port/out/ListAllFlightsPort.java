package com.bb.envifastservice.application.port.out;

import com.bb.envifastservice.algo.ArcoAeropuerto;

import java.time.LocalDate;
import java.util.List;

public interface ListAllFlightsPort {
    List<ArcoAeropuerto> listAllFlights(String fecha);
}
