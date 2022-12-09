package com.bb.envifastservice.application.port.in;

import com.bb.envifastservice.adapter.out.persistence.dtos.AirportCapacity;
import com.bb.envifastservice.adapter.out.persistence.dtos.AirportCoord;

import java.io.IOException;
import java.util.List;

public interface ListAirportCapacityService {
    List<AirportCapacity> listAirportCapacity(String fecha, String hora) throws IOException;
}
