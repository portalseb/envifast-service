package com.bb.envifastservice.adapter.out.persistence.impl;


import com.bb.envifastservice.adapter.out.persistence.repos.AirportRepository;
import com.bb.envifastservice.adapter.out.persistence.repos.FlightRepository;
import com.bb.envifastservice.algo.*;
import com.bb.envifastservice.application.port.out.GenerateNextWeekFlightsPort;
import com.bb.envifastservice.application.port.out.ListFlightByIdPort;
import com.bb.envifastservice.hexagonal.PersistenceAdapter;
import com.bb.envifastservice.models.FlightModel;
import com.bb.envifastservice.models.PackageModel;
import lombok.RequiredArgsConstructor;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

@PersistenceAdapter
@RequiredArgsConstructor
public class FlightAdapter implements ListFlightByIdPort, GenerateNextWeekFlightsPort {
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
        arco.setId(Math.toIntExact(bdVuelo.getId()));
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

    @Override
    public void generateNextWeekFlights() {
        File planes = new File("src/main/java/com/bb/envifastservice/c.inf226.22-2.planes_vuelo.v02.txt");
        Scanner myReader = null;
        for (int i = 0; i<7;i++){
            try {
                myReader = new Scanner(planes);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
            while (myReader.hasNextLine()) {
                var vuelo = new FlightModel();
                final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                var nextLine = myReader.nextLine();
                var data=  nextLine.split("-");
                var depAirport=  airportRepository.findByCityShortNameAndActive(data[0],1);
                var arrivalAirport=  airportRepository.findByCityShortNameAndActive(data[1],1);
                vuelo.setActive(1);
                vuelo.setArrivalAirport(arrivalAirport);
                vuelo.setDepartureAirport(depAirport);
                if(arrivalAirport.getContinent().equals("EUROPA") && depAirport.getContinent().equals("EUROPA")){
                    vuelo.setMaxCapacity(250L);
                    vuelo.setAvailableCapacity(250L);
                }

                if(arrivalAirport.getContinent().equals("AMERICA DEL SUR") && depAirport.getContinent().equals("AMERICA DEL SUR"))
                {
                    vuelo.setMaxCapacity(300L);
                    vuelo.setAvailableCapacity(300L);
                }
                if(!arrivalAirport.getContinent().equals(depAirport.getContinent()))
                {
                    vuelo.setMaxCapacity(350L);
                    vuelo.setAvailableCapacity(350L);
                }
                var sale = LocalDateTime.now().with(LocalTime.parse(data[2], formatter)).plusDays(i);
                var llega = LocalDateTime.now().with(LocalTime.parse(data[3], formatter)).plusDays(i);


                vuelo.setArrivalTime(llega);
                vuelo.setDepartureTime(sale);
                flightRepository.save(vuelo);
            }
            myReader.close();
        }

    }
}