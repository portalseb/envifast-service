package com.bb.envifastservice.adapter.out.persistence.impl;

import com.bb.envifastservice.adapter.out.persistence.mappers.AirportMapper;
import com.bb.envifastservice.adapter.out.persistence.repos.AirportRepository;
import com.bb.envifastservice.algo.Aeropuerto;
import com.bb.envifastservice.algo.Ciudad;
import com.bb.envifastservice.application.port.out.ListAllAirportsPort;
import com.bb.envifastservice.hexagonal.PersistenceAdapter;
import com.bb.envifastservice.models.AirportsModel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class AirportAdapter implements ListAllAirportsPort {

    private final AirportRepository airportRepository;

    @Override
    public List<Aeropuerto> listAllAirports() {
        var table= airportRepository.findAllByActive(1);
        List<Aeropuerto> list = new ArrayList<>();
        for (AirportsModel aeropuerto:table) {
            var aerop = new Aeropuerto();
            aerop.setId(Math.toIntExact(aeropuerto.getId()));
            aerop.setCapacidad(aeropuerto.getMaxCapacity());
            aerop.setCodigo(aeropuerto.getAirportCode());
            var ciudad = new Ciudad();
            ciudad.setAbreviacion(aeropuerto.getCityShortName());
            ciudad.setContinente(aeropuerto.getContinent());
            ciudad.setNombre(aeropuerto.getCityName());
            ciudad.setPais(aeropuerto.getCountryName());
            aerop.setCiudad(ciudad);
            list.add(aerop);

        }
        return list;
    }
}
