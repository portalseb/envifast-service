package com.bb.envifastservice.application.port.out;

import com.bb.envifastservice.adapter.out.persistence.dtos.FlightRoute;
import com.bb.envifastservice.algo.ArcoAeropuerto;

import java.util.List;

public interface ShowPackageRoutePort {
    List<FlightRoute> showRoute(String id);
}
