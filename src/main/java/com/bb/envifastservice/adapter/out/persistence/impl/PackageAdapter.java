package com.bb.envifastservice.adapter.out.persistence.impl;

import com.bb.envifastservice.adapter.out.persistence.dtos.FlightRoute;
import com.bb.envifastservice.adapter.out.persistence.repos.AirportRepository;
import com.bb.envifastservice.adapter.out.persistence.repos.PackageRepository;
import com.bb.envifastservice.algo.ArcoAeropuerto;
import com.bb.envifastservice.application.port.out.ShowPackageRoutePort;
import com.bb.envifastservice.hexagonal.PersistenceAdapter;
import com.bb.envifastservice.models.FlightModel;
import com.bb.envifastservice.models.PackageModel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
//TODO: verificar que se devuelva
@PersistenceAdapter
@RequiredArgsConstructor
public class PackageAdapter implements ShowPackageRoutePort {
    private final PackageRepository packageRepository;
    private final AirportRepository airportRepository;
    private final FlightAdapter flightAdapter;
    @Override
    public List<FlightRoute> showRoute(String id) {
        var vuelos = new ArrayList<FlightRoute>();
        var flights = packageRepository.findFlightsInPackageRouteAndActive(id,1);
        for (FlightModel vuelo:flights
             ) {
            var arco = new FlightRoute();
            arco.setCiudadOrigen(vuelo.getDepartureAirport().getCityName());
            arco.setCiudadDestino(vuelo.getArrivalAirport().getCityName());
            arco.setHoraSalida(vuelo.getDepartureTime());
            arco.setHoraLLegada(vuelo.getArrivalTime());
            vuelos.add(arco);
        }
        return vuelos;
    }
}
