package com.bb.envifastservice.application.port;

import com.bb.envifastservice.algo.Aeropuerto;
import com.bb.envifastservice.application.port.in.ListAllAirportsService;
import com.bb.envifastservice.application.port.out.ListAllAirportsPort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ListAllAirportsUseCase implements ListAllAirportsService {
    private final ListAllAirportsPort listAllAirportsPort;


    @Override
    public List<Aeropuerto> listAllAirpoirts(){
        var airportsListOpt = listAllAirportsPort.listAllAirports();
        return airportsListOpt;
    }
}
