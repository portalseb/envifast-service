package com.bb.envifastservice.adapter.out.persistence.impl;


import com.bb.envifastservice.adapter.out.persistence.repos.AirportRepository;
import com.bb.envifastservice.adapter.out.persistence.repos.FlightRepository;
import com.bb.envifastservice.algo.*;
import com.bb.envifastservice.application.port.out.ListFlightByIdPort;
import com.bb.envifastservice.hexagonal.PersistenceAdapter;
import com.bb.envifastservice.models.PackageModel;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class FlightAdapter implements ListFlightByIdPort {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    @Override
    public ArcoAeropuerto listById(Long id) {
        var bdVueloOpt = flightRepository.findByIdAndActive(id, 1);
        if (bdVueloOpt.isEmpty()) return new ArcoAeropuerto();
        var bdVuelo = bdVueloOpt.get();
        var arco = new ArcoAeropuerto();
        var aeropuertoSalida = new Aeropuerto();
        var aeropuertoLlegada = new Aeropuerto();
        var paquetes = new ArrayList<Paquete>();
        //Aeropuertos Salida:
        aeropuertoSalida.setId(Math.toIntExact(bdVuelo.getDepartureAirport().getId()));
        aeropuertoSalida.setCapacidad(bdVuelo.getDepartureAirport().getMaxCapacity());
        aeropuertoSalida.setCodigo(bdVuelo.getDepartureAirport().getAirportCode());
        var ciudad = new Ciudad();
        ciudad.setAbreviacion(bdVuelo.getDepartureAirport().getCityShortName());
        ciudad.setContinente(bdVuelo.getDepartureAirport().getContinent());
        ciudad.setNombre(bdVuelo.getDepartureAirport().getCityName());
        ciudad.setPais(bdVuelo.getDepartureAirport().getCountryName());
        aeropuertoSalida.setCiudad(ciudad);
        //Aeropuerto Llegada:
        aeropuertoLlegada.setId(Math.toIntExact(bdVuelo.getArrivalAirport().getId()));
        aeropuertoLlegada.setCapacidad(bdVuelo.getArrivalAirport().getMaxCapacity());
        aeropuertoLlegada.setCodigo(bdVuelo.getArrivalAirport().getAirportCode());
        ciudad.setAbreviacion(bdVuelo.getArrivalAirport().getCityShortName());
        ciudad.setContinente(bdVuelo.getArrivalAirport().getContinent());
        ciudad.setNombre(bdVuelo.getArrivalAirport().getCityName());
        ciudad.setPais(bdVuelo.getArrivalAirport().getCountryName());
        aeropuertoLlegada.setCiudad(ciudad);
        arco.setAeropuerto1(aeropuertoSalida);
        arco.setAeropuerto2(aeropuertoLlegada);
        arco.setHoraPartida(bdVuelo.getDepartureTime().toLocalTime());
        arco.setHoraLlegada(bdVuelo.getArrivalTime().toLocalTime());
        arco.setDiaPartida(LocalDate.from(bdVuelo.getDepartureTime()));
        arco.setDiaLLegada(LocalDate.from(bdVuelo.getArrivalTime()));
        arco.setCapacidadDisponible(Math.toIntExact(bdVuelo.getAvailableCapacity()));
        arco.setCapacidadMaxima(Math.toIntExact(bdVuelo.getMaxCapacity()));
        for (PackageModel pack:bdVuelo.getCargo()
             ) {
            var paquete = new Paquete();
            var destino2 = airportRepository.findByCityNameAndActive(pack.getDestino(),1);
            var origen2 = airportRepository.findByCityNameAndActive(pack.getOrigen(),1);
            var aDestino2 = new Aeropuerto();
            var cDestino2 = new Ciudad();
            cDestino2.setPais(destino2.getCountryName());
            cDestino2.setNombre(destino2.getCityName());
            cDestino2.setContinente(destino2.getContinent());
            cDestino2.setAbreviacion(destino2.getCityShortName());
            aDestino2.setCiudad(cDestino2);
            aDestino2.setCodigo(destino2.getAirportCode());
            aDestino2.setCapacidad(destino2.getMaxCapacity());
            aDestino2.setId(Math.toIntExact(destino2.getId()));
            var aOrigen2 = new Aeropuerto();
            var cOrigen2 = new Ciudad();
            cOrigen2.setPais(origen2.getCountryName());
            cOrigen2.setNombre(origen2.getCityName());
            cOrigen2.setContinente(origen2.getContinent());
            cOrigen2.setAbreviacion(origen2.getCityShortName());
            aOrigen2.setCiudad(cOrigen2);
            aOrigen2.setCodigo(origen2.getAirportCode());
            aOrigen2.setCapacidad(origen2.getMaxCapacity());
            aOrigen2.setId(Math.toIntExact(origen2.getId()));

            paquete.setDestino(aDestino2);
            paquete.setId(pack.getId());
            paquete.setOrigen(aOrigen2);
            paquetes.add(paquete);
        }
        arco.setCargo(paquetes);
        return arco;
    }
}
