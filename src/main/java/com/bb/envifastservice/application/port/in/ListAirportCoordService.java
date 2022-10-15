package com.bb.envifastservice.application.port.in;

import com.bb.envifastservice.adapter.out.persistence.dtos.AirportCoord;

import java.util.List;

public interface ListAirportCoordService {
    List<AirportCoord> listCoordinates();
}
