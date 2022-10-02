package com.bb.envifastservice.application.port.in;

import com.bb.envifastservice.algo.ArcoAeropuerto;

import java.util.List;
import java.util.Optional;

public interface ListFlightByIdService {
    ArcoAeropuerto listById(Long id);
}
