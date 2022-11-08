package com.bb.envifastservice.application.port;

import com.bb.envifastservice.adapter.out.persistence.dtos.FlightMap;
import com.bb.envifastservice.application.port.in.ListDayFlightsService;
import com.bb.envifastservice.application.port.out.ListDayFlightsPort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ListDayFlightsUseCase implements ListDayFlightsService {
    private final ListDayFlightsPort listDayFlightsPort;

    @Override
    public List<FlightMap> listDayFlights(String fecha, Integer paraSim) {
        var flightsListOpt = listDayFlightsPort.listDayFlights(fecha,paraSim);
        return flightsListOpt;
    }
}
