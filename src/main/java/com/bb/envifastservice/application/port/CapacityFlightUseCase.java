package com.bb.envifastservice.application.port;

import com.bb.envifastservice.application.port.in.CapacityFlightService;
import com.bb.envifastservice.application.port.out.CapacityFlightPort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
@UseCase
@RequiredArgsConstructor
public class CapacityFlightUseCase implements CapacityFlightService {
    private final CapacityFlightPort capacityFlightPort;

    @Override
    public int capacityFlights(String fecha, Integer origenId, Integer destinoId) throws IOException {
        return capacityFlightPort.capacityFlights(fecha,origenId,destinoId);
    }
}
