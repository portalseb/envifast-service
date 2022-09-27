package com.bb.envifastservice.application.port.out;

import com.bb.envifastservice.algo.ArcoAeropuerto;

import java.util.List;

//TODO:
public interface ListFlightByIdPort {
    List<ArcoAeropuerto> listById(Long id);
}
