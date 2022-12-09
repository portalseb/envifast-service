package com.bb.envifastservice.adapter.out.persistence.dtos;

import lombok.Data;

@Data
public class AirportCoord {
    Long id;
    String cityName;
    Double x_pos;
    Double y_pos;
    int maxCapacity;
}
