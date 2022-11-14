package com.bb.envifastservice.adapter.out.persistence.impl;

import com.bb.envifastservice.adapter.out.persistence.repos.*;
import com.bb.envifastservice.algo.*;
import com.bb.envifastservice.algo.antColony.Aco;
import com.bb.envifastservice.algo.antColony.AntSide;
import com.bb.envifastservice.application.port.out.*;
import com.bb.envifastservice.hexagonal.PersistenceAdapter;
import com.bb.envifastservice.models.*;
import lombok.RequiredArgsConstructor;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@PersistenceAdapter
@RequiredArgsConstructor
public class OrderAdapter implements ListPackagesPort, InsertOrderPort, PlanOrderRoutePort, GenerateSimulationOrderPort, GetOrderForUserPort, IniciarSim5DiasPort, GenerateOrderForSimPort {
    private final PackageRepository packageRepository;
    private final AirportRepository airportRepository;
    private final OrderRepository orderRepository;

    private final FlightRepository flightRepository;
    private final FlightAdapter flightAdapter;
    private final AirportAdapter airportAdapter;

    private final AirportCapacityRepository airportCapacityRepository;
    @Override
    public List<Envio> listByFields(String input) {
        var registros = orderRepository.findAllByFieldsLikeAndActive(input);
        var paquetes = new ArrayList<Paquete>();
        var envios = new ArrayList<Envio>();
        var vuelos = new ArrayList<ArcoAeropuerto>();
        for (OrderModel order: registros
             ) {
            var envio = new Envio();
            paquetes = new ArrayList<Paquete>();
            vuelos = new ArrayList<ArcoAeropuerto>();
            convertToEnvio(paquetes, order, envio, vuelos);
            envios.add(envio);
        }

        return envios;
    }

    private void convertToEnvio(ArrayList<Paquete> paquetes, OrderModel order, Envio envio, ArrayList<ArcoAeropuerto>vuelos) {
        envio.setCodigo(order.getCodigo());
        envio.setCantidadPaquetes(order.getCantidad());
        var destino = airportRepository.findByCityNameAndActive(order.getDestino(),1);
        var origen = airportRepository.findByCityNameAndActive(order.getOrigen(),1);
        var aDestino = new Aeropuerto();
        var cDestino = new Ciudad();
        cDestino.setPais(destino.getCountryName());
        cDestino.setNombre(destino.getCityName());
        cDestino.setContinente(destino.getContinent());
        cDestino.setAbreviacion(destino.getCityShortName());
        aDestino.setCiudad(cDestino);
        aDestino.setCodigo(destino.getAirportCode());
        aDestino.setCapacidad(destino.getMaxCapacity());
        aDestino.setId(Math.toIntExact(destino.getId()));
        var aOrigen = new Aeropuerto();
        var cOrigen = new Ciudad();
        cOrigen.setPais(origen.getCountryName());
        cOrigen.setNombre(origen.getCityName());
        cOrigen.setContinente(origen.getContinent());
        cOrigen.setAbreviacion(origen.getCityShortName());
        aOrigen.setCiudad(cOrigen);
        aOrigen.setCodigo(origen.getAirportCode());
        aOrigen.setCapacidad(origen.getMaxCapacity());
        aOrigen.setId(Math.toIntExact(origen.getId()));
        envio.setDestino(aDestino);
        envio.setOrigen(aOrigen);
        envio.setId(order.getId());
        envio.setFechaEnvio(order.getFechaEnvio());
        envio.setDestinatarioApellidoM(order.getDestinatarioApellidoM());
        envio.setDestinatarioApellidoP(order.getDestinatarioApellidoP());
        envio.setDestinatarioNombres(order.getDestinatarioNombres());
        envio.setDestinatarioCorreo(order.getDestinatarioCorreo());
        envio.setDestinatarioDocumentoNumero(order.getDestinatarioDocumentoNumero());
        envio.setDestinatarioDocumentoTipo(order.getDestinatarioDocumentoTipo());
        envio.setDestinatarioTelefonoNumero(order.getDestinatarioTelefonoNumero());
        envio.setEmisorApellidoM(order.getEmisorApellidoM());
        envio.setEmisorApellidoP(order.getEmisorApellidoP());
        envio.setEmisorCorreo(order.getEmisorCorreo());
        envio.setEmisorDocumentoNumero(order.getEmisorDocumentoNumero());
        envio.setEmisorDocumentoTipo(order.getEmisorDocumentoTipo());
        envio.setEmisorNombres(order.getEmisorNombres());
        envio.setEmisorTelefonoNumero(order.getEmisorTelefonoNumero());
        envio.setTiempoTotal(order.getTiempoTotal());
        envio.setToken(order.getToken());
        for (PackageModel pack: order.getPacks()
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
            for (FlightModel vuelo:pack.getRoute()
            ) {
                var arco = flightAdapter.listById(vuelo.getId());
                vuelos.add(arco);

            }
            paquetes.add(paquete);

        }
        envio.setPaquetes(paquetes);
    }

    @Override
    public Envio insertOrd(Envio envio){
        var envioNuevo =new OrderModel();
        //envioNuevo.setCodigo(envio.getCodigo());
        envioNuevo.setEmisorNombres(envio.getEmisorNombres());
        envioNuevo.setEmisorApellidoP(envio.getEmisorApellidoP());
        envioNuevo.setEmisorApellidoM(envio.getEmisorApellidoM());
        envioNuevo.setEmisorDocumentoTipo(envio.getEmisorDocumentoTipo());
        envioNuevo.setEmisorDocumentoNumero(envio.getEmisorDocumentoNumero());
        envioNuevo.setEmisorCorreo(envio.getEmisorCorreo());
        envioNuevo.setEmisorTelefonoNumero(envio.getEmisorTelefonoNumero());

        envioNuevo.setDestinatarioNombres(envio.getDestinatarioNombres());
        envioNuevo.setDestinatarioApellidoP(envio.getDestinatarioApellidoP());
        envioNuevo.setDestinatarioApellidoM(envio.getDestinatarioApellidoM());
        envioNuevo.setDestinatarioDocumentoTipo(envio.getDestinatarioDocumentoTipo());
        envioNuevo.setDestinatarioDocumentoNumero(envio.getDestinatarioDocumentoNumero());
        envioNuevo.setDestinatarioCorreo(envio.getDestinatarioCorreo());
        envioNuevo.setDestinatarioTelefonoNumero(envio.getDestinatarioTelefonoNumero());

        envioNuevo.setCantidad(envio.getCantidadPaquetes());
        envioNuevo.setFechaEnvio(envio.getFechaEnvio());

        envioNuevo.setTiempoTotal(0.0);
        envioNuevo.setForSim(0);
        envioNuevo.setPlanned(0);
        var aeropuertoOrigen = airportRepository.findByIdActive(envio.getOrigen().getId().longValue(),1);
        var aeropuertoDestino = airportRepository.findByIdActive(envio.getDestino().getId().longValue(),1);
        envioNuevo.setOrigen(aeropuertoOrigen.getCityName());
        envioNuevo.setDestino(aeropuertoDestino.getCityName());
        envioNuevo.setActive(1);

        List<PackageModel> paquetes = new ArrayList<PackageModel>();

        for(int i=0;i<envio.getCantidadPaquetes();i++){
            var paqueteNuevo = new PackageModel();
            paqueteNuevo.setDateTime(envio.getFechaEnvio());
            paqueteNuevo.setCurrentAirportId(envio.getOrigen().getId().longValue());
            paqueteNuevo.setOrigen(aeropuertoOrigen.getCityName());
            paqueteNuevo.setDestino(aeropuertoDestino.getCityName());
            paqueteNuevo.setActive(1);
            paquetes.add(paqueteNuevo);
        }

        envioNuevo.setPacks(paquetes);

        orderRepository.save(envioNuevo);
        envio.setId(envioNuevo.getId());
        envio.setPaquetes(new ArrayList<Paquete>());
        for(int i=0;i<envio.getCantidadPaquetes();i++) {
            Paquete paqueteNuevo = new Paquete();
            paqueteNuevo.setId(envioNuevo.getPacks().get(i).getId());
            envio.getPaquetes().add(paqueteNuevo);
        }

        //Generacion de codigo
        int id = (int)(long)envioNuevo.getId();
        int count = 0;
        int paddingSize,num=id;
        while(num!=0){
            num = num/10;
            count++;
        }
        paddingSize = 9-count;
        if(paddingSize>=0) {
            envioNuevo.setCodigo(aeropuertoOrigen.getAirportCode() + String.format("%0" + 9 + "d", id));
            envioNuevo.setToken(envioNuevo.getId().toString());
            orderRepository.save(envioNuevo);
            envio.setCodigo(envioNuevo.getCodigo());
            envio.setToken(envioNuevo.getToken());
        }
        return envio;
    }

    @Override
    public int planOrdRoute(List<Envio> envios){
        //Menor fecha de envios
        var envioFechaMin = envios.stream().min(Comparator.comparing(Envio::getFechaEnvio)).orElseThrow(NoSuchElementException::new).getFechaEnvio();
        var envioFechaMax = envios.stream().max(Comparator.comparing(Envio::getFechaEnvio)).orElseThrow(NoSuchElementException::new).getFechaEnvio().plusDays(2);
        //Planear y guardar en BD las rutas para los envios
        var aeropuertosRegistros = airportRepository.findAllByActive(1);
        var arcosGeneralRegistros = flightRepository.findAllByActiveRange(1,envioFechaMin, envioFechaMax,0);
        ArrayList<Aeropuerto> aeropuertos= new ArrayList<>();
        ArrayList<ArcoAeropuerto> arcosGeneral = new ArrayList<>();


        System.out.println("Ya inicializo");
        //
        for(AirportsModel airport: aeropuertosRegistros){
            Aeropuerto aeropuerto = new Aeropuerto();
            aeropuerto.setId((int)(long)airport.getId());
            aeropuerto.setCodigo(airport.getAirportCode());
            aeropuerto.setPosX(airport.getXPos());
            aeropuerto.setPosY(airport.getYPos());
            aeropuerto.setCapacidad(airport.getMaxCapacity());
            aeropuerto.setTimeZone(TimeZone.getTimeZone(airport.getCityName()).toString());

            Ciudad ciudad = new Ciudad();
            ciudad.setNombre(airport.getCityName());
            ciudad.setAbreviacion(airport.getCityShortName());
            ciudad.setContinente(airport.getContinent());
            ciudad.setPais(airport.getCountryName());
            aeropuerto.setCiudad(ciudad);
            airport.getCapacity().removeIf(c->c.getDateTime().getDate().isBefore(envioFechaMin.toLocalDate()));
            airport.getCapacity().removeIf(c->c.getForSim()==1);

            for(AirportsCapacityModel airportsCapacityModel: airport.getCapacity()){
                CapacidadAeropuerto capacidadAeropuerto = new CapacidadAeropuerto();
                capacidadAeropuerto.setId((int)(long)airportsCapacityModel.getId());
                capacidadAeropuerto.setCapacidadDisponible(airportsCapacityModel.getAvailableCapacity());

                FechaHora fechaHora = new FechaHora();
                fechaHora.setId((int)(long)airportsCapacityModel.getDateTime().getId());
                fechaHora.setDia(airportsCapacityModel.getDateTime().getDate());
                fechaHora.setHora(airportsCapacityModel.getDateTime().getTime());

                capacidadAeropuerto.setFechaHora(fechaHora);
                capacidadAeropuerto.setDeposito(new ArrayList<>());

                aeropuerto.getCapacidadDisponible().add(capacidadAeropuerto);
            }

            aeropuertos.add(aeropuerto);
        }
        for(FlightModel flight: arcosGeneralRegistros){
            ArcoAeropuerto arcoAeropuerto = new ArcoAeropuerto();
            arcoAeropuerto.setId((int)(long)flight.getId());
            arcoAeropuerto.setCapacidadMaxima((int)(long)flight.getMaxCapacity());
            arcoAeropuerto.setCapacidadDisponible((int)(long)flight.getAvailableCapacity());

            int origen =0;
            int destino = 0;
            for(int i=0;i<aeropuertos.size();i++){
                if(aeropuertos.get(i).getId()==(int)(long)flight.getDepartureAirport().getId()){
                    origen=i;
                }
                if(aeropuertos.get(i).getId()==(int)(long)flight.getArrivalAirport().getId()){
                    destino=i;
                }
            }
            arcoAeropuerto.setAeropuerto1(aeropuertos.get(origen));
            arcoAeropuerto.setAeropuerto2(aeropuertos.get(destino));

            arcoAeropuerto.setDiaPartida(LocalDate.of(flight.getDepartureTime().getYear(),flight.getDepartureTime().getMonthValue(),flight.getDepartureTime().getDayOfMonth()));
            arcoAeropuerto.setDiaLLegada(LocalDate.of(flight.getArrivalTime().getYear(),flight.getArrivalTime().getMonthValue(),flight.getArrivalTime().getDayOfMonth()));
            arcoAeropuerto.setHoraPartida(LocalTime.of(flight.getDepartureTime().getHour(),flight.getDepartureTime().getMinute()));
            arcoAeropuerto.setHoraLlegada(LocalTime.of(flight.getArrivalTime().getHour(),flight.getArrivalTime().getMinute()));

            arcoAeropuerto.setCargo(new ArrayList<>());
            arcosGeneral.add(arcoAeropuerto);
        }

        AntSide ambiente = new AntSide();

        //Setear aeropuertos, debe estar con su arreglo capacidadesAeropuertos
        ambiente.setNodos(aeropuertos);

        for(int i=0;i<envios.size();i++){
            //Variables... modificar
            ArrayList<ArcoAeropuerto> arcos = ambiente.sacarArcosPosibles(arcosGeneral,envios.get(i));
            ambiente.setCaminos(arcos);
            System.out.println("Cantidad de arcos");
            System.out.println(arcos.size());
            int origen =0;
            int destino = 0;
            for(int j=0;j<aeropuertos.size();j++){
                if(aeropuertos.get(j).getId()==envios.get(i).getOrigen().getId()){
                    origen=j;
                }
                if(aeropuertos.get(j).getId()==envios.get(i).getDestino().getId()){
                    destino=j;
                }
            }

            ambiente.setNodoInicialFinal(aeropuertos.get(origen),aeropuertos.get(destino));
            ambiente.setPaquetesEnvio(envios.get(i).getPaquetes());
            ambiente.setFechaInicial(envios.get(i).getFechaEnvio());


            Aco algoritmoHormigas = new Aco();
            long start1 = System.currentTimeMillis();
            algoritmoHormigas.activarHormigas(ambiente);
            long end1 = System.currentTimeMillis();
            System.out.println("Elapsed Time in milli seconds: " + (end1 - start1));

            //Se imprime la solucion
            System.out.println("Envio "+ i +" - Camino: " + algoritmoHormigas.getSolucionCamino().toString() + algoritmoHormigas.getSolucionCosto());

            //Actualizar capacidades
            ambiente.actualizarCapacidades(algoritmoHormigas.getSolucionCamino());
            envios.get(i).setTiempoTotal(algoritmoHormigas.getSolucionCosto());
            //Actualizar rutas de paquetes
            for(int j=0;j<envios.get(i).getPaquetes().size();j++){
                envios.get(i).getPaquetes().get(j).setRuta(algoritmoHormigas.getSolucionCamino());
            }
        }

        //Actualizar en BD:
           //Paquetes
        int plan=1;
        var packages = new ArrayList<PackageModel>();
        var orders = new ArrayList<OrderModel>();
        for (Envio envio:envios
             ) {
            plan=1;
            var orderBD = orderRepository.findByIdOfOrder(envio.getId(),1);
            for (Paquete paquete:envio.getPaquetes()
                 ) {
                var packageBD = packageRepository.findByIdOfPackage(paquete.getId(),1);
                var flights = new ArrayList<FlightModel>();
                for (ArcoAeropuerto vuelo: paquete.getRuta()
                     ) {
                    var flight = flightRepository.findByFlightId(vuelo.getId().longValue());
                    flights.add(flight);
                }
                if(flights.size()==0) plan=0;
                packageBD.setRoute(flights);
                packages.add(packageBD);
            }
            orderBD.setPlanned(plan);
            orders.add(orderBD);
        }
        orderRepository.saveAll(orders);
        packageRepository.saveAll(packages);
//        //Arcos
        var flights = new ArrayList<FlightModel>();
        for(int j=0;j<arcosGeneral.size();j++){
            if((int)(long)arcosGeneralRegistros.get(j).getAvailableCapacity()!=arcosGeneral.get(j).getCapacidadDisponible()){
                FlightModel flightModel = flightRepository.findByFlightId(arcosGeneralRegistros.get(j).getId());
                flightModel.setAvailableCapacity(arcosGeneral.get(j).getCapacidadDisponible().longValue());
                flights.add(flightModel);
            }
        }
        flightRepository.saveAll(flights);
//        //Aeropuertos
        var airports =  new ArrayList<AirportsModel>();
        for(int j=0;j<aeropuertos.size();j++){
            var aeropuertoModel = airportRepository.findByCityShortNameAndActive(aeropuertos.get(j).getCodigo(),1);
            var capacityAirport = airportCapacityRepository.findByAirportCode(aeropuertoModel.getId());
            for (int k=0;k<aeropuertos.get(j).getCapacidadDisponible().size();k++){
                if(aeropuertos.get(j).getCapacidadDisponible().get(k).getCapacidadDisponible()!=(int)(long) aeropuertosRegistros.get(j).getCapacity().get(k).getAvailableCapacity()){
                    //aeropuertoModel.getCapacity().get(k).setAvailableCapacity(aeropuertos.get(j).getCapacidadDisponible().get(k).getCapacidadDisponible());
                    capacityAirport.get(capacityAirport.indexOf(aeropuertosRegistros.get(j).getCapacity().get(k))).setAvailableCapacity(aeropuertos.get(j).getCapacidadDisponible().get(k).getCapacidadDisponible());

                }
            }
            aeropuertoModel.setCapacity(capacityAirport);
            airports.add(aeropuertoModel);
        }

        airportRepository.saveAll(airports);

        return 1;
    }


    @Override
    public int generateSimulationOrder(String fecha) throws FileNotFoundException {


        var envioFechaMin = LocalDateTime.of(LocalDate.parse(fecha),LocalTime.of(0,0));
        var envioFechaMax = LocalDateTime.of(LocalDate.parse(fecha).plusDays(5),LocalTime.of(0,0)).plusDays(2);
        //Planear y guardar en BD las rutas para los envios
        var aeropuertosRegistros = airportRepository.findAllByActive(1);
        var arcosGeneralRegistros = flightRepository.findAllByActiveRange(1,envioFechaMin, envioFechaMax,1);
        ArrayList<Aeropuerto> aeropuertos= new ArrayList<>();
        ArrayList<ArcoAeropuerto> arcosGeneral = new ArrayList<>();


        for(AirportsModel airport: aeropuertosRegistros){
            Aeropuerto aeropuerto = new Aeropuerto();
            aeropuerto.setId((int)(long)airport.getId());
            aeropuerto.setCodigo(airport.getAirportCode());
            aeropuerto.setPosX(airport.getXPos());
            aeropuerto.setPosY(airport.getYPos());
            aeropuerto.setCapacidad(airport.getMaxCapacity());
            aeropuerto.setTimeZone(TimeZone.getTimeZone(airport.getCityName()).toString());

            Ciudad ciudad = new Ciudad();
            ciudad.setNombre(airport.getCityName());
            ciudad.setAbreviacion(airport.getCityShortName());
            ciudad.setContinente(airport.getContinent());
            ciudad.setPais(airport.getCountryName());
            aeropuerto.setCiudad(ciudad);
            airport.getCapacity().removeIf(c->c.getDateTime().getDate().isBefore(envioFechaMin.toLocalDate()));

            for(AirportsCapacityModel airportsCapacityModel: airport.getCapacity()){
                CapacidadAeropuerto capacidadAeropuerto = new CapacidadAeropuerto();
                capacidadAeropuerto.setId((int)(long)airportsCapacityModel.getId());
                capacidadAeropuerto.setCapacidadDisponible(airportsCapacityModel.getAvailableCapacity());

                FechaHora fechaHora = new FechaHora();
                fechaHora.setId((int)(long)airportsCapacityModel.getDateTime().getId());
                fechaHora.setDia(airportsCapacityModel.getDateTime().getDate());
                fechaHora.setHora(airportsCapacityModel.getDateTime().getTime());

                capacidadAeropuerto.setFechaHora(fechaHora);
                capacidadAeropuerto.setDeposito(new ArrayList<>());

                aeropuerto.getCapacidadDisponible().add(capacidadAeropuerto);
            }

            aeropuertos.add(aeropuerto);
        }
        for(FlightModel flight: arcosGeneralRegistros){
            ArcoAeropuerto arcoAeropuerto = new ArcoAeropuerto();
            arcoAeropuerto.setId((int)(long)flight.getId());
            arcoAeropuerto.setCapacidadMaxima((int)(long)flight.getMaxCapacity());
            arcoAeropuerto.setCapacidadDisponible((int)(long)flight.getAvailableCapacity());

            int origen =0;
            int destino = 0;
            for(int i=0;i<aeropuertos.size();i++){
                if(aeropuertos.get(i).getId()==(int)(long)flight.getDepartureAirport().getId()){
                    origen=i;
                }
                if(aeropuertos.get(i).getId()==(int)(long)flight.getArrivalAirport().getId()){
                    destino=i;
                }
            }
            arcoAeropuerto.setAeropuerto1(aeropuertos.get(origen));
            arcoAeropuerto.setAeropuerto2(aeropuertos.get(destino));

            arcoAeropuerto.setDiaPartida(LocalDate.of(flight.getDepartureTime().getYear(),flight.getDepartureTime().getMonthValue(),flight.getDepartureTime().getDayOfMonth()));
            arcoAeropuerto.setDiaLLegada(LocalDate.of(flight.getArrivalTime().getYear(),flight.getArrivalTime().getMonthValue(),flight.getArrivalTime().getDayOfMonth()));
            arcoAeropuerto.setHoraPartida(LocalTime.of(flight.getDepartureTime().getHour(),flight.getDepartureTime().getMinute()));
            arcoAeropuerto.setHoraLlegada(LocalTime.of(flight.getArrivalTime().getHour(),flight.getArrivalTime().getMinute()));

            arcoAeropuerto.setCargo(new ArrayList<>());
            arcosGeneral.add(arcoAeropuerto);
        }


        var envios = new ArrayList<Envio>();

        LectorEnviosCorto lectorEnviosCorto = new LectorEnviosCorto(aeropuertos);
        lectorEnviosCorto.setFechaDesde(LocalDate.parse(fecha));
        lectorEnviosCorto.Leer("src/main/java/com/bb/envifastservice/data.prueba.5.dias.txt");

        AntSide ambiente = new AntSide();
        ambiente.setNodos(aeropuertos);

        for(int j=0; j<lectorEnviosCorto.getDestinos().size(); j++) {
            //Creacion objeto envio
            var envio = new Envio();
            envio.setCodigo(lectorEnviosCorto.getCodigos().get(j));
            envio.setFechaEnvio(lectorEnviosCorto.getFechasEnvio().get(j));
            envio.setOrigen(lectorEnviosCorto.getOrigenes().get(j));
            envio.setDestino(lectorEnviosCorto.getDestinos().get(j));
            envio.setCantidadPaquetes(lectorEnviosCorto.getCantPaquetes().get(j));
            envio.setPaquetes(new ArrayList<Paquete>());

            for (int k = 0; k < envio.getCantidadPaquetes(); k++) {
                Paquete paquete = new Paquete();
                envio.getPaquetes().add(paquete);
            }

            //Algoritmo seteo de datos
            ArrayList<ArcoAeropuerto> arcos = ambiente.sacarArcosPosibles(arcosGeneral,envio);
            ambiente.setCaminos(arcos);
            System.out.println("Cantidad de arcos");
            System.out.println(arcos.size());
            int origen =0;
            int destino = 0;
            for(int k=0;k<aeropuertos.size();k++){
                if(aeropuertos.get(k).getId()==envio.getOrigen().getId()){
                    origen=k;
                }
                if(aeropuertos.get(k).getId()==envio.getDestino().getId()){
                    destino=k;
                }
            }
            ambiente.setNodoInicialFinal(aeropuertos.get(origen),aeropuertos.get(destino));
            ambiente.setPaquetesEnvio(envio.getPaquetes());
            ambiente.setFechaInicial(envio.getFechaEnvio());

            //Algoritmo ejecucion
            Aco algoritmoHormigas = new Aco();
            long start1 = System.currentTimeMillis();
            algoritmoHormigas.activarHormigas(ambiente);
            long end1 = System.currentTimeMillis();
            System.out.println("Elapsed Time in milli seconds: " + (end1 - start1));

            //Se imprime la solucion
            System.out.println("Envio "+ j +" - Camino: " + algoritmoHormigas.getSolucionCamino().toString() + algoritmoHormigas.getSolucionCosto());

            //Actualizar capacidades
            ambiente.actualizarCapacidades(algoritmoHormigas.getSolucionCamino());
            envio.setTiempoTotal(algoritmoHormigas.getSolucionCosto());
            //Actualizar rutas de paquetes
            for(int k=0;k<envio.getPaquetes().size();k++){
                envio.getPaquetes().get(k).setRuta(algoritmoHormigas.getSolucionCamino());
            }
            envios.add(envio);
        }

        //Registrar en BD envios y paquetes con ruta
        var enviosNuevos = new ArrayList<OrderModel>();
        for(int i=0;i<lectorEnviosCorto.getDestinos().size();i++) {
            var envioNuevo = new OrderModel();
            //Setear todoz lo del envio
            envioNuevo.setCodigo(envios.get(i).getCodigo());
            envioNuevo.setCantidad(envios.get(i).getCantidadPaquetes());
            envioNuevo.setFechaEnvio(envios.get(i).getFechaEnvio());
            envioNuevo.setTiempoTotal(envios.get(i).getTiempoTotal());
            var aeropuertoOrigen = airportRepository.findByIdActive(envios.get(i).getOrigen().getId().longValue(),1);
            var aeropuertoDestino = airportRepository.findByIdActive(envios.get(i).getDestino().getId().longValue(),1);
            envioNuevo.setOrigen(aeropuertoOrigen.getCityName());
            envioNuevo.setDestino(aeropuertoDestino.getCityName());
            envioNuevo.setActive(1);
            List<PackageModel> paquetes = new ArrayList<PackageModel>();
            for(int j=0;j<envios.get(i).getPaquetes().size();j++){
                var paqueteNuevo = new PackageModel();
                paqueteNuevo.setDateTime(envios.get(i).getFechaEnvio());
                paqueteNuevo.setCurrentAirportId(envios.get(i).getOrigen().getId().longValue());
                paqueteNuevo.setOrigen(aeropuertoOrigen.getCityName());
                paqueteNuevo.setDestino(aeropuertoDestino.getCityName());
                paqueteNuevo.setActive(1);
                var flights = new ArrayList<FlightModel>();
                for (ArcoAeropuerto vuelo: envios.get(i).getPaquetes().get(j).getRuta()
                ) {
                    var flight = flightRepository.findByFlightId(vuelo.getId().longValue());
                    flights.add(flight);
                }
                paqueteNuevo.setRoute(flights);
                paquetes.add(paqueteNuevo);
            }
            envioNuevo.setPacks(paquetes);
            enviosNuevos.add(envioNuevo);
        }
        orderRepository.saveAll(enviosNuevos);


        //Arcos
        var flights = new ArrayList<FlightModel>();
        for(int j=0;j<arcosGeneral.size();j++){
            if((int)(long)arcosGeneralRegistros.get(j).getAvailableCapacity()!=arcosGeneral.get(j).getCapacidadDisponible()){
                FlightModel flightModel = flightRepository.findByFlightId(arcosGeneralRegistros.get(j).getId());
                flightModel.setAvailableCapacity(arcosGeneral.get(j).getCapacidadDisponible().longValue());
                flights.add(flightModel);
            }
        }
        flightRepository.saveAll(flights);


        //Aeropuertos
        var airports =  new ArrayList<AirportsModel>();
        for(int j=0;j<aeropuertos.size();j++){
            var aeropuertoModel = airportRepository.findByCityShortNameAndActive(aeropuertos.get(j).getCodigo(),1);
            for (int k=0;k<aeropuertos.get(j).getCapacidadDisponible().size();k++){
                if(aeropuertos.get(j).getCapacidadDisponible().get(k).getCapacidadDisponible()!=(int)(long) aeropuertosRegistros.get(j).getCapacity().get(k).getAvailableCapacity()){
                    aeropuertoModel.getCapacity().get(k).setAvailableCapacity(aeropuertos.get(j).getCapacidadDisponible().get(k).getCapacidadDisponible());
                }
            }
            airports.add(aeropuertoModel);
        }
        airportRepository.saveAll(airports);


        return 1;
    }

    @Override
    public Envio getOrderForUser(String token, String docNo) {
        var orderOpt = orderRepository.queryOrdersWithDocNoandToken(docNo,token);
        if(orderOpt.isPresent()){
            var order = orderOpt.get();
            var paquetes = new ArrayList<Paquete>();
            var envio = new Envio();
            var vuelos = new ArrayList<ArcoAeropuerto>();
            convertToEnvio(paquetes,order,envio, vuelos);
            return envio;

        }
        else{
            return new Envio();
        }

    }

    @Override
    public int iniciarSim5Dias(String fecha, Integer dias, Integer paraSim) throws FileNotFoundException {
        flightAdapter.generateNextWeekFlights(fecha, dias, paraSim);
        airportAdapter.generateNextWeekDateTime(fecha, dias, paraSim);
        generateSimulationOrder(fecha);

        return 1;
    }

    @Override
    public int generateSimulationOrders(String fecha, String timeInf, String timeSup, Integer forSim) throws FileNotFoundException {

        var envioFechaMin = LocalDateTime.of(LocalDate.parse(fecha),LocalTime.parse(timeInf)); //Se va a cambiar cuando necesite reprogramar envios de fechas/horas anteriores
        var envioFechaMax = LocalDateTime.of(LocalDate.parse(fecha),LocalTime.parse(timeSup)).plusDays(2);
        //Planear y guardar en BD las rutas para los envios
        var aeropuertosRegistros = airportRepository.findAllByActive(1);
        var arcosGeneralRegistros = flightRepository.findAllByActiveRange(1,envioFechaMin, envioFechaMax,forSim);
        ArrayList<Aeropuerto> aeropuertos= new ArrayList<>();
        ArrayList<ArcoAeropuerto> arcosGeneral = new ArrayList<>();


        for(AirportsModel airport: aeropuertosRegistros){
            Aeropuerto aeropuerto = new Aeropuerto();
            aeropuerto.setId((int)(long)airport.getId());
            aeropuerto.setCodigo(airport.getAirportCode());
            aeropuerto.setPosX(airport.getXPos());
            aeropuerto.setPosY(airport.getYPos());
            aeropuerto.setCapacidad(airport.getMaxCapacity());
            aeropuerto.setTimeZone(TimeZone.getTimeZone(airport.getCityName()).toString());

            Ciudad ciudad = new Ciudad();
            ciudad.setNombre(airport.getCityName());
            ciudad.setAbreviacion(airport.getCityShortName());
            ciudad.setContinente(airport.getContinent());
            ciudad.setPais(airport.getCountryName());
            aeropuerto.setCiudad(ciudad);
            airport.getCapacity().removeIf(c->c.getDateTime().getDate().isBefore(envioFechaMin.toLocalDate())); //Esto se cambiara para que tenga un limite mayor tambien
            airport.getCapacity().removeIf(c->c.getForSim()==0);
            for(AirportsCapacityModel airportsCapacityModel: airport.getCapacity()){
                CapacidadAeropuerto capacidadAeropuerto = new CapacidadAeropuerto();
                capacidadAeropuerto.setId((int)(long)airportsCapacityModel.getId());
                capacidadAeropuerto.setCapacidadDisponible(airportsCapacityModel.getAvailableCapacity());

                FechaHora fechaHora = new FechaHora();
                fechaHora.setId((int)(long)airportsCapacityModel.getDateTime().getId());
                fechaHora.setDia(airportsCapacityModel.getDateTime().getDate());
                fechaHora.setHora(airportsCapacityModel.getDateTime().getTime());

                capacidadAeropuerto.setFechaHora(fechaHora);
                capacidadAeropuerto.setDeposito(new ArrayList<>());

                aeropuerto.getCapacidadDisponible().add(capacidadAeropuerto);
            }

            aeropuertos.add(aeropuerto);
        }
        for(FlightModel flight: arcosGeneralRegistros){
            ArcoAeropuerto arcoAeropuerto = new ArcoAeropuerto();
            arcoAeropuerto.setId((int)(long)flight.getId());
            arcoAeropuerto.setCapacidadMaxima((int)(long)flight.getMaxCapacity());
            arcoAeropuerto.setCapacidadDisponible((int)(long)flight.getAvailableCapacity());

            int origen =0;
            int destino = 0;
            for(int i=0;i<aeropuertos.size();i++){
                if(aeropuertos.get(i).getId()==(int)(long)flight.getDepartureAirport().getId()){
                    origen=i;
                }
                if(aeropuertos.get(i).getId()==(int)(long)flight.getArrivalAirport().getId()){
                    destino=i;
                }
            }
            arcoAeropuerto.setAeropuerto1(aeropuertos.get(origen));
            arcoAeropuerto.setAeropuerto2(aeropuertos.get(destino));

            arcoAeropuerto.setDiaPartida(LocalDate.of(flight.getDepartureTime().getYear(),flight.getDepartureTime().getMonthValue(),flight.getDepartureTime().getDayOfMonth()));
            arcoAeropuerto.setDiaLLegada(LocalDate.of(flight.getArrivalTime().getYear(),flight.getArrivalTime().getMonthValue(),flight.getArrivalTime().getDayOfMonth()));
            arcoAeropuerto.setHoraPartida(LocalTime.of(flight.getDepartureTime().getHour(),flight.getDepartureTime().getMinute()));
            arcoAeropuerto.setHoraLlegada(LocalTime.of(flight.getArrivalTime().getHour(),flight.getArrivalTime().getMinute()));

            arcoAeropuerto.setCargo(new ArrayList<>());
            arcosGeneral.add(arcoAeropuerto);
        }


        var envios = new ArrayList<Envio>();

        LectorEnviosCorto lectorEnviosCorto = new LectorEnviosCorto(aeropuertos);
        lectorEnviosCorto.setFechaDesde(LocalDate.parse(fecha));
        lectorEnviosCorto.LeerActualizado("src/main/java/com/bb/envifastservice/historicData/",fecha,timeInf,timeSup);

        AntSide ambiente = new AntSide();
        ambiente.setNodos(aeropuertos);

        for(int j=0; j<lectorEnviosCorto.getDestinos().size(); j++) {
            //Creacion objeto envio
            var envio = new Envio();
            envio.setCodigo(lectorEnviosCorto.getCodigos().get(j));
            envio.setFechaEnvio(lectorEnviosCorto.getFechasEnvio().get(j));
            envio.setOrigen(lectorEnviosCorto.getOrigenes().get(j));
            envio.setDestino(lectorEnviosCorto.getDestinos().get(j));
            envio.setCantidadPaquetes(lectorEnviosCorto.getCantPaquetes().get(j));
            envio.setPaquetes(new ArrayList<Paquete>());

            for (int k = 0; k < envio.getCantidadPaquetes(); k++) {
                Paquete paquete = new Paquete();
                envio.getPaquetes().add(paquete);
            }

            //Algoritmo seteo de datos
            ArrayList<ArcoAeropuerto> arcos = ambiente.sacarArcosPosibles(arcosGeneral,envio);
            int origen =0;
            int destino = 0;
            for(int k=0;k<aeropuertos.size();k++){
                if(aeropuertos.get(k).getId()==envio.getOrigen().getId()){
                    origen=k;
                }
                if(aeropuertos.get(k).getId()==envio.getDestino().getId()){
                    destino=k;
                }
            }
            //Manejar los envios EU->SA
            //if(aeropuertos.get(origen).getCiudad().getContinente().equals("EUROPA") && aeropuertos.get(destino).getCiudad().getContinente().equals("AMERICA DEL SUR")){
            //    arcos = ambiente.sacarArcosPosiblesEUSA(arcos);
            //}
            ambiente.setCaminos(arcos);
            System.out.println("Cantidad de arcos");
            System.out.println(arcos.size());
            ambiente.setNodoInicialFinal(aeropuertos.get(origen),aeropuertos.get(destino));
            ambiente.setPaquetesEnvio(envio.getPaquetes());
            ambiente.setFechaInicial(envio.getFechaEnvio());

            //Algoritmo ejecucion
            Aco algoritmoHormigas = new Aco();
            long start1 = System.currentTimeMillis();
            algoritmoHormigas.activarHormigas(ambiente);
            long end1 = System.currentTimeMillis();
            System.out.println("Elapsed Time in milli seconds: " + (end1 - start1));

            //Se imprime la solucion
            System.out.println("Envio "+ j +" - Camino: " + algoritmoHormigas.getSolucionCamino().toString() + algoritmoHormigas.getSolucionCosto());

            //Actualizar capacidades
            ambiente.actualizarCapacidades(algoritmoHormigas.getSolucionCamino());
            envio.setTiempoTotal(algoritmoHormigas.getSolucionCosto());
            //Actualizar rutas de paquetes
            for(int k=0;k<envio.getPaquetes().size();k++){
                envio.getPaquetes().get(k).setRuta(algoritmoHormigas.getSolucionCamino());
            }
            envios.add(envio);
        }

        //Registrar en BD envios y paquetes con ruta
        var enviosNuevos = new ArrayList<OrderModel>();
        for(int i=0;i<lectorEnviosCorto.getDestinos().size();i++) {
            var envioNuevo = new OrderModel();
            int plan=1;
            //Setear todoz lo del envio
            envioNuevo.setCodigo(envios.get(i).getCodigo());
            envioNuevo.setCantidad(envios.get(i).getCantidadPaquetes());
            envioNuevo.setFechaEnvio(envios.get(i).getFechaEnvio());
            envioNuevo.setTiempoTotal(envios.get(i).getTiempoTotal());
            var aeropuertoOrigen = airportRepository.findByIdActive(envios.get(i).getOrigen().getId().longValue(),1);
            var aeropuertoDestino = airportRepository.findByIdActive(envios.get(i).getDestino().getId().longValue(),1);
            envioNuevo.setOrigen(aeropuertoOrigen.getCityName());
            envioNuevo.setDestino(aeropuertoDestino.getCityName());
            envioNuevo.setActive(1);
            envioNuevo.setForSim(1);
            List<PackageModel> paquetes = new ArrayList<PackageModel>();
            for(int j=0;j<envios.get(i).getPaquetes().size();j++){
                var paqueteNuevo = new PackageModel();
                paqueteNuevo.setDateTime(envios.get(i).getFechaEnvio());
                paqueteNuevo.setCurrentAirportId(envios.get(i).getOrigen().getId().longValue());
                paqueteNuevo.setOrigen(aeropuertoOrigen.getCityName());
                paqueteNuevo.setDestino(aeropuertoDestino.getCityName());
                paqueteNuevo.setActive(1);
                var flights = new ArrayList<FlightModel>();
                for (ArcoAeropuerto vuelo: envios.get(i).getPaquetes().get(j).getRuta()
                ) {
                    var flight = flightRepository.findByFlightId(vuelo.getId().longValue());
                    flights.add(flight);
                }
                if(flights.size()==0) plan=0;
                paqueteNuevo.setRoute(flights);
                paquetes.add(paqueteNuevo);
            }
            envioNuevo.setPacks(paquetes);
            envioNuevo.setPlanned(plan);
            enviosNuevos.add(envioNuevo);
        }
        orderRepository.saveAll(enviosNuevos);


        //Arcos
        var flights = new ArrayList<FlightModel>();
        for(int j=0;j<arcosGeneral.size();j++){
            if((int)(long)arcosGeneralRegistros.get(j).getAvailableCapacity()!=arcosGeneral.get(j).getCapacidadDisponible()){
                FlightModel flightModel = flightRepository.findByFlightId(arcosGeneralRegistros.get(j).getId());
                flightModel.setAvailableCapacity(arcosGeneral.get(j).getCapacidadDisponible().longValue());
                flights.add(flightModel);
            }
        }
        flightRepository.saveAll(flights);


        //Aeropuertos
        var airports =  new ArrayList<AirportsModel>();
        for(int j=0;j<aeropuertos.size();j++){
            var aeropuertoModel = airportRepository.findByCityShortNameAndActive(aeropuertos.get(j).getCodigo(),1);
            var capacityAirport = airportCapacityRepository.findByAirportCode(aeropuertoModel.getId());
            for (int k=0;k<aeropuertos.get(j).getCapacidadDisponible().size();k++){
                if(aeropuertos.get(j).getCapacidadDisponible().get(k).getCapacidadDisponible()!=(int)(long) aeropuertosRegistros.get(j).getCapacity().get(k).getAvailableCapacity()){
                    //aeropuertoModel.getCapacity().get(k).setAvailableCapacity(aeropuertos.get(j).getCapacidadDisponible().get(k).getCapacidadDisponible());
                    capacityAirport.get(capacityAirport.indexOf(aeropuertosRegistros.get(j).getCapacity().get(k))).setAvailableCapacity(aeropuertos.get(j).getCapacidadDisponible().get(k).getCapacidadDisponible());

                }
            }
            aeropuertoModel.setCapacity(capacityAirport);
            airports.add(aeropuertoModel);
        }
        airportRepository.saveAll(airports);

        return 1;
    }
}
