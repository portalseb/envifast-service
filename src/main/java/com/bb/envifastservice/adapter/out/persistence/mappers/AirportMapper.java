package com.bb.envifastservice.adapter.out.persistence.mappers;

import com.bb.envifastservice.algo.Aeropuerto;
import com.bb.envifastservice.models.AirportsModel;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel= "spring")
public interface AirportMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "airportCode", source = "codigo")
    @Mapping(target = "maxCapacity", source = "capacidad")
    AirportsModel toAirportModel(Aeropuerto aeropuerto);

    @InheritInverseConfiguration
    @Mapping(source = "id", target = "id")
    Aeropuerto toAeropuerto(AirportsModel airportsModel);

}
