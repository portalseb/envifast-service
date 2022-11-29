package com.bb.envifastservice.adapter.out.persistence.impl;


import com.bb.envifastservice.adapter.out.persistence.dtos.FlightMap;
import com.bb.envifastservice.adapter.out.persistence.repos.AirportRepository;
import com.bb.envifastservice.adapter.out.persistence.repos.FlightRepository;
import com.bb.envifastservice.adapter.out.persistence.repos.OrderRepository;
import com.bb.envifastservice.adapter.out.persistence.repos.PackageRepository;
import com.bb.envifastservice.algo.*;
import com.bb.envifastservice.application.port.out.*;
import com.bb.envifastservice.hexagonal.PersistenceAdapter;
import com.bb.envifastservice.models.FlightModel;
import com.bb.envifastservice.models.PackageModel;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

@PersistenceAdapter
@RequiredArgsConstructor
public class FlightAdapter implements ListFlightByIdPort, GenerateNextWeekFlightsPort, ListAllFlightsPort, ListDayFlightsPort, RestoreFlightsPort, FindFlightPackagesPort {
    private final FlightRepository flightRepository;
    private final AirportRepository airportRepository;
    private final PackageRepository packageRepository;
    private final OrderRepository orderRepository;
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
        var ciudad2 = new Ciudad();
        ciudad2.setAbreviacion(bdVuelo.getArrivalAirport().getCityShortName());
        ciudad2.setContinente(bdVuelo.getArrivalAirport().getContinent());
        ciudad2.setNombre(bdVuelo.getArrivalAirport().getCityName());
        ciudad2.setPais(bdVuelo.getArrivalAirport().getCountryName());
        aeropuertoLlegada.setCiudad(ciudad2);
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
    @Transactional
    public void generateNextWeekFlights(String fecha,Integer dias, Integer paraSim) throws IOException {
        //InputStream inputStream = getClass().getResourceAsStream("/c.inf226.22-2.planes_vuelo.v02.txt");
        //URL resource = getClass().getClassLoader().getResource("c.inf226.22-2.planes_vuelo.v02.txt");
        ClassPathResource classPathResource = new ClassPathResource("c.inf226.22-2.planes_vuelo.v03.txt");
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("c.inf226.22-2.planes_vuelo.v03.txt");
        //Resource resource = new ClassPathResource("c.inf226.22-2.planes_vuelo.v02.txt");
        //File planes = resource.getFile();


        if(resourceAsStream == null) {System.out.println("Es nulo"); return;}
        //else{System.out.println("No es nulo");}
        //File planes = new File("src/main/java/com/bb/envifastservice/c.inf226.22-2.planes_vuelo.v02.txt");
        Scanner myReader = null;
        var vuelos = new ArrayList<FlightModel>();
        if(paraSim==1 && dias==3){
        //    //probar si se puede o si se debe borrar primero la tabla route
            flightRepository.deleteByParaSimRange(paraSim,1);
            packageRepository.deleteByParaSimRange(paraSim,1);
            orderRepository.deleteByParaSimRange(paraSim,1);
        }
        var airportsBD = airportRepository.findAllByActive(1);
        int origen=0,destino=0;
        //Pendiente: agregar que no se repita para el dia a dia...
        for (int i = 0; i<dias;i++){
            myReader = new Scanner(classPathResource.getInputStream());
            while (myReader.hasNextLine()) {
                var vuelo = new FlightModel();
                final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                var nextLine = myReader.nextLine();
                var data=  nextLine.split("-");
                for(int k=0;k<airportsBD.size();k++){
                    if(airportsBD.get(k).getAirportCode().equals(data[0]))
                        origen = k;
                    if(airportsBD.get(k).getAirportCode().equals(data[1]))
                        destino = k;
                }
                //var depAirport=  airportRepository.findByCityShortNameAndActive(data[0],1);
                //var arrivalAirport=  airportRepository.findByCityShortNameAndActive(data[1],1);
                vuelo.setActive(1);
                vuelo.setForSim(paraSim);
                vuelo.setArrivalAirport(airportsBD.get(destino));
                vuelo.setDepartureAirport(airportsBD.get(origen));
                if(airportsBD.get(destino).getContinent().equals("EUROPA") && airportsBD.get(origen).getContinent().equals("EUROPA")){
                    vuelo.setMaxCapacity(250L);
                    vuelo.setAvailableCapacity(250L);
                }

                if(airportsBD.get(destino).getContinent().equals("AMERICA DEL SUR") && airportsBD.get(origen).getContinent().equals("AMERICA DEL SUR"))
                {
                    vuelo.setMaxCapacity(300L);
                    vuelo.setAvailableCapacity(300L);
                }
                if(!airportsBD.get(destino).getContinent().equals(airportsBD.get(origen).getContinent()))
                {
                    vuelo.setMaxCapacity(350L);
                    vuelo.setAvailableCapacity(350L);
                }
                var sale = LocalDateTime.of(LocalDate.parse(fecha),LocalTime.parse(data[2], formatter)).plusDays(i);
                var llega =LocalDateTime.of(LocalDate.parse(fecha),LocalTime.parse(data[3], formatter)).plusDays(i);
                if(LocalTime.parse(data[3], formatter).compareTo(LocalTime.parse(data[2], formatter))<=0){
                    llega = LocalDateTime.of(LocalDate.parse(fecha),LocalTime.parse(data[3], formatter)).plusDays(i+1);
                }
                vuelo.setArrivalTime(llega);
                vuelo.setDepartureTime(sale);
                vuelo.setDeparted(false);
                vuelo.setArrived(false);
                vuelos.add(vuelo);
            }

            myReader.close();
        }
        flightRepository.saveAll(vuelos);
    }

    @Override
    public List<FlightMap> listAllFlights(String fecha,Integer per, Integer paraSim){
        var table= flightRepository.findAllByActiveRange(1,LocalDateTime.of(LocalDate.parse(fecha),LocalTime.of(6*(per-1),0)),LocalDateTime.of(LocalDate.parse(fecha),LocalTime.of(6*(per)-1,59)),paraSim);
        List<FlightMap> list = new ArrayList<>();

        for(FlightModel flight: table){
            var origen = airportRepository.findByCityShortNameAndActive(flight.getDepartureAirport().getAirportCode(),1);
            var destino = airportRepository.findByCityShortNameAndActive(flight.getArrivalAirport().getAirportCode(),1);
            FlightMap flightMap = new FlightMap();
            flightMap.setId((int)(long)flight.getId());
            flightMap.setDuracion((int) ChronoUnit.MINUTES.between(flight.getDepartureTime(),flight.getArrivalTime()));
            flightMap.setIdAeropuertoOrigen((int)(long)origen.getId());
            flightMap.setIdAeropuertoDestino((int)(long)destino.getId());
            flightMap.setHoraSalida(LocalDateTime.of(LocalDate.of(flight.getDepartureTime().getYear(),flight.getDepartureTime().getMonthValue(),flight.getDepartureTime().getDayOfMonth()),LocalTime.of(flight.getDepartureTime().getHour(),flight.getDepartureTime().getMinute())));
            flightMap.setHoraLLegada(LocalDateTime.of(LocalDate.of(flight.getArrivalTime().getYear(),flight.getArrivalTime().getMonthValue(),flight.getArrivalTime().getDayOfMonth()),LocalTime.of(flight.getArrivalTime().getHour(),flight.getArrivalTime().getMinute())));
            //flightMap.setCantPaquetes((int)(long)(flight.getMaxCapacity()-flight.getAvailableCapacity()));
            //flightMap.setCantMax((int)(long)(flight.getMaxCapacity()));
            list.add(flightMap);
        }
        System.out.println(table.size());
        System.out.println("Ya paso por todos");
        return list;
    }
    @Override
    public List<FlightMap> listDayFlights(String fecha, Integer paraSim) {
        var table= flightRepository.findAllByActiveRange(1,LocalDateTime.of(LocalDate.parse(fecha),LocalTime.of(0,0)),LocalDateTime.of(LocalDate.parse(fecha),LocalTime.of(23,59)),paraSim);
        List<FlightMap> list = new ArrayList<>();

        for(FlightModel flight: table){
            var origen = airportRepository.findByCityShortNameAndActive(flight.getDepartureAirport().getAirportCode(),1);
            var destino = airportRepository.findByCityShortNameAndActive(flight.getArrivalAirport().getAirportCode(),1);
            FlightMap flightMap = new FlightMap();
            flightMap.setId((int)(long)flight.getId());
            flightMap.setDuracion((int) ChronoUnit.MINUTES.between(flight.getDepartureTime(),flight.getArrivalTime()));
            flightMap.setIdAeropuertoOrigen((int)(long)origen.getId());
            flightMap.setIdAeropuertoDestino((int)(long)destino.getId());
            flightMap.setHoraSalida(LocalDateTime.of(LocalDate.of(flight.getDepartureTime().getYear(),flight.getDepartureTime().getMonthValue(),flight.getDepartureTime().getDayOfMonth()),LocalTime.of(flight.getDepartureTime().getHour(),flight.getDepartureTime().getMinute())));
            flightMap.setHoraLLegada(LocalDateTime.of(LocalDate.of(flight.getArrivalTime().getYear(),flight.getArrivalTime().getMonthValue(),flight.getArrivalTime().getDayOfMonth()),LocalTime.of(flight.getArrivalTime().getHour(),flight.getArrivalTime().getMinute())));
            //flightMap.setCantPaquetes((int)(long)(flight.getMaxCapacity()-flight.getAvailableCapacity()));
            //flightMap.setCantMax((int)(long)(flight.getMaxCapacity()));
            list.add(flightMap);
        }
        System.out.println(table.size());
        System.out.println("Ya paso por todos");
        return list;
    }
    @Override
    public void restoreFlights(String fecha, Integer dias, Integer paraSim) {
        LocalDateTime fechaIni = LocalDateTime.of(LocalDate.parse(fecha),LocalTime.of(0,0,0));
        LocalDateTime fechaFin = LocalDateTime.of(LocalDate.parse(fecha).plusDays(dias),LocalTime.of(23,59,0));
        flightRepository.restoreFlights(fechaIni,fechaFin,paraSim,1);
    }
    @Override
    public int findFlightPackages(String fechaIni, String horaIni, Integer origenId, Integer destinoId, Integer paraSim) {
        var origenBD = airportRepository.findByIdActive(origenId.longValue(),1);
        var destinoBD = airportRepository.findByIdActive(destinoId.longValue(),1);
        var flightBD = flightRepository.findByDepartureTimeAndDepartureAirportAndArrivalAirportAndForSimAndActive(LocalDateTime.of(LocalDate.parse(fechaIni),LocalTime.parse(horaIni)),origenBD,destinoBD,paraSim,1);
        if(flightBD!=null)
            return (int) (flightBD.getMaxCapacity()-flightBD.getAvailableCapacity());
        return -1;
    }
}
