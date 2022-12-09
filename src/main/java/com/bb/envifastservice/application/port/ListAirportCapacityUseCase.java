package com.bb.envifastservice.application.port;

import com.bb.envifastservice.adapter.out.persistence.dtos.AirportCapacity;
import com.bb.envifastservice.adapter.out.persistence.dtos.AirportCoord;
import com.bb.envifastservice.application.port.in.ListAirportCapacityService;
import com.bb.envifastservice.application.port.out.ListAirportCapacityPort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ListAirportCapacityUseCase implements ListAirportCapacityService {
    private final ListAirportCapacityPort listAirportCapacityPort;

    @Override
    public List<AirportCapacity> listAirportCapacity(String fecha, String hora) throws IOException {
        return listAirportCapacityPort.listAirportCapacity(fecha, hora);
    }
}
