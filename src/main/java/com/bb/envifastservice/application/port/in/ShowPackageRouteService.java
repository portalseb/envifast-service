package com.bb.envifastservice.application.port.in;

import com.bb.envifastservice.adapter.out.persistence.dtos.FlightRoute;
import com.bb.envifastservice.algo.ArcoAeropuerto;

import java.util.List;

public interface ShowPackageRouteService {
    List<FlightRoute> showRoute(String id);
}
