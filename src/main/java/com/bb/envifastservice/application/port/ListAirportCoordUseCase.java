package com.bb.envifastservice.application.port;

import com.bb.envifastservice.adapter.out.persistence.dtos.AirportCoord;
import com.bb.envifastservice.application.port.in.ListAirportCoordService;
import com.bb.envifastservice.application.port.out.ListAirportCoordPort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ListAirportCoordUseCase implements ListAirportCoordService {
    private final ListAirportCoordPort listAirportCoordPort;
    @Override
    public List<AirportCoord> listCoordinates() {
        return listAirportCoordPort.listCoord();
    }
}
