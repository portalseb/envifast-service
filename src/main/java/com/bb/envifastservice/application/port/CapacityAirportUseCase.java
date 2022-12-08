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
    public int capacityAirport(String fecha, String timeInf,String code)  throws IOException {
        return capacityAirportPort.capacityAirport(fecha, timeInf, code);
    }
}
