package com.bb.envifastservice.application.port.in;

import com.bb.envifastservice.algo.ArcoAeropuerto;

import java.util.List;

public interface ShowPackageRouteService {
    List<ArcoAeropuerto> showRoute(String id);
}
