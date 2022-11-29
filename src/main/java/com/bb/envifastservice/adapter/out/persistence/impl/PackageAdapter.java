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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        var ultimaLlegada = LocalDateTime.of(LocalDate.of(2022,8,4), LocalTime.of(0,0));
        for (FlightModel vuelo:flights
             ) {
            var arco = new FlightRoute();
            arco.setCiudadOrigen(vuelo.getDepartureAirport().getCityName());
            arco.setCiudadDestino(vuelo.getArrivalAirport().getCityName());
            arco.setHoraSalida(vuelo.getDepartureTime());
            arco.setHoraLLegada(vuelo.getArrivalTime());
            if(arco.getHoraSalida().isBefore(ultimaLlegada)){
                for(int i=0;i<5;i++) {
                    arco.setHoraSalida(arco.getHoraSalida().plusDays(i+1));
                    arco.setHoraLLegada(arco.getHoraLLegada().plusDays(i+1));
                    if(arco.getHoraSalida().isAfter(ultimaLlegada)){
                        break;
                    }
                }
            }
            vuelos.add(arco);
            ultimaLlegada = arco.getHoraLLegada();
        }
        return vuelos;
    }
}
