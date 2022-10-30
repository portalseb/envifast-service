package com.bb.envifastservice.application.port;

import com.bb.envifastservice.adapter.out.persistence.dtos.FlightMap;
import com.bb.envifastservice.algo.ArcoAeropuerto;
import com.bb.envifastservice.application.port.in.ListAllFlightsService;
import com.bb.envifastservice.application.port.out.ListAllFlightsPort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ListAllFlightsUseCase implements ListAllFlightsService {
    private final ListAllFlightsPort listAllFlightsPort;
    @Override
    public List<FlightMap> listAllFlights(String fecha, Integer per){
        var flightsListOpt = listAllFlightsPort.listAllFlights(fecha,per);
        return flightsListOpt;
    }

}
