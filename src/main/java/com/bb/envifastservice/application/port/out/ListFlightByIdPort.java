package com.bb.envifastservice.application.port.out;

import com.bb.envifastservice.algo.ArcoAeropuerto;

import java.util.List;
import java.util.Optional;


public interface ListFlightByIdPort {
    ArcoAeropuerto listById(Long id);
}
