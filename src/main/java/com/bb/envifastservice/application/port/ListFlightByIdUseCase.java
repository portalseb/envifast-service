package com.bb.envifastservice.application.port;

import com.bb.envifastservice.algo.ArcoAeropuerto;
import com.bb.envifastservice.application.port.in.ListFlightByIdService;
import com.bb.envifastservice.application.port.out.ListFlightByIdPort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class ListFlightByIdUseCase implements ListFlightByIdService {
    private final ListFlightByIdPort listFlightByIdPort;


    @Override
    public ArcoAeropuerto listById(Long id) {

        return listFlightByIdPort.listById(id);
    }
}
