package com.bb.envifastservice.application.port;

import com.bb.envifastservice.application.port.in.GenerateNextWeekFlightsService;
import com.bb.envifastservice.application.port.out.GenerateNextWeekFlightsPort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GenerateNextWeekFlightsUseCase implements GenerateNextWeekFlightsService {
    private final GenerateNextWeekFlightsPort nextWeekFlightsPort;

    @Override
    public void generateNextWeekFlights() {
        nextWeekFlightsPort.generateNextWeekFlights();
    }
}
