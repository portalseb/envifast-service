package com.bb.envifastservice.application.port.out;

import com.bb.envifastservice.adapter.out.persistence.dtos.AirportCoord;

import java.util.List;

public interface
ListAirportCoordPort {
    List<AirportCoord> listCoord();
}
