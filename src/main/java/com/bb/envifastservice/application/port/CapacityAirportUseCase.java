package com.bb.envifastservice.application.port;

import com.bb.envifastservice.application.port.in.CapacityAirportService;
import com.bb.envifastservice.application.port.out.CapacityAirportPort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@UseCase
@RequiredArgsConstructor
public class CapacityAirportUseCase implements CapacityAirportService {
    private final CapacityAirportPort capacityAirportPort;

    @Override
    public void capacityAirport(String fecha, String timeInf, String timeSup, Integer forSim) throws IOException {
        capacityAirportPort.capacityAirport(fecha, timeInf, timeSup, forSim);
    }
}
