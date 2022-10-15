package com.bb.envifastservice.application.port.out;

import com.bb.envifastservice.algo.ArcoAeropuerto;

import java.util.List;

public interface ShowPackageRoutePort {
    List<ArcoAeropuerto> showRoute(String id);
}
