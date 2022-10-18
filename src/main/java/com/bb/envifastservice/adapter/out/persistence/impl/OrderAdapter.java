package com.bb.envifastservice.adapter.out.persistence.impl;

import com.bb.envifastservice.adapter.out.persistence.repos.AirportRepository;
import com.bb.envifastservice.adapter.out.persistence.repos.FlightRepository;
import com.bb.envifastservice.adapter.out.persistence.repos.OrderRepository;
import com.bb.envifastservice.adapter.out.persistence.repos.PackageRepository;
import com.bb.envifastservice.algo.*;
import com.bb.envifastservice.algo.antColony.Aco;
import com.bb.envifastservice.algo.antColony.AntSide;
import com.bb.envifastservice.application.port.out.InsertOrderPort;
import com.bb.envifastservice.application.port.out.ListPackagesPort;
import com.bb.envifastservice.application.port.out.PlanOrderRoutePort;
import com.bb.envifastservice.hexagonal.PersistenceAdapter;
import com.bb.envifastservice.models.*;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

@PersistenceAdapter
@RequiredArgsConstructor
public class OrderAdapter implements ListPackagesPort, InsertOrderPort, PlanOrderRoutePort {
    private final PackageRepository packageRepository;
    private final AirportRepository airportRepository;
    private final OrderRepository orderRepository;

    private final FlightRepository flightRepository;

    @Override
    public List<Envio> listByFields(String input) {
        var registros = orderRepository.findAllByFieldsLikeAndActive(input);
        var paquetes = new ArrayList<Paquete>();
        var envios = new ArrayList<Envio>();
        for (OrderModel order: registros
             ) {
            var envio = new Envio();
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
            for (PackageModel pack:order.getPacks()
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
            envio.setPaquetes(paquetes);


            envios.add(envio);
        }

        return envios;
    }

    @Override
    public Envio insertOrd(Envio envio){
        var envioNuevo =new OrderModel();
        envioNuevo.setCodigo(envio.getCodigo());
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
        envioNuevo.setToken(envio.getToken());
        envioNuevo.setOrigen(envio.getOrigen().getCiudad().getNombre());
        envioNuevo.setDestino(envio.getDestino().getCiudad().getNombre());
        envioNuevo.setActive(1);

        List<PackageModel> paquetes = new ArrayList<PackageModel>();

        for(int i=0;i<envio.getPaquetes().size();i++){
            var paqueteNuevo = new PackageModel();
            paqueteNuevo.setDateTime(envio.getFechaEnvio());
            paqueteNuevo.setCurrentAirportId(envio.getPaquetes().get(i).getOrigen().getId().longValue());
            paqueteNuevo.setOrigen(envio.getPaquetes().get(i).getOrigen().getCiudad().getNombre());
            paqueteNuevo.setDestino(envio.getPaquetes().get(i).getDestino().getCiudad().getNombre());
            paqueteNuevo.setActive(1);
            paquetes.add(paqueteNuevo);
        }

        envioNuevo.setPacks(paquetes);

        orderRepository.save(envioNuevo);
        envio.setId(envioNuevo.getId());

    return envio;
    }

    @Override
    public int planOrdRoute(List<Envio> envios){
        //Planear y guardar en BD las rutas para los envios
        var aeropuertosRegistros = airportRepository.findAllByActive(1);
        var arcosGeneralRegistros = flightRepository.findAll();
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

            for(AirportsCapacityModel airportsCapacityModel: airport.getCapacity()){
                CapacidadAeropuerto capacidadAeropuerto = new CapacidadAeropuerto();
                capacidadAeropuerto.setId((int)(long)airportsCapacityModel.getId());
                capacidadAeropuerto.setCapacidadDisponible(airportsCapacityModel.getAvailableCapacity());

                FechaHora fechaHora = new FechaHora();
                fechaHora.setId((int)(long)airportsCapacityModel.getDateTime().getId());
                fechaHora.setDia(airportsCapacityModel.getDateTime().getDate());
                fechaHora.setHora(airportsCapacityModel.getDateTime().getTime());

                capacidadAeropuerto.setFechaHora(fechaHora);

                //Agregar el cargo (arreglo de paquetes)
                for(PackageModel packageModel: airportsCapacityModel.getWarehouse()){
                    Paquete paquete = new Paquete();
                    paquete.setId(packageModel.getId());
                    //Faltan mas campos

                    capacidadAeropuerto.getDeposito().add(paquete);
                }

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

            //Agregar el cargo (arreglo de paquetes)
            for(PackageModel packageModel: flight.getCargo()){
                Paquete paquete = new Paquete();
                paquete.setId(packageModel.getId());
                //Faltan mas campos

                arcoAeropuerto.getCargo().add(paquete);
            }

            arcosGeneral.add(arcoAeropuerto);
        }

        AntSide ambiente = new AntSide();

        //Setear aeropuertos, debe estar con su arreglo capacidadesAeropuertos
        ambiente.setNodos(aeropuertos);

        for(int i=0;i<envios.size();i++){
            //Variables... modificar
            ArrayList<ArcoAeropuerto> arcos = ambiente.sacarArcosPosibles(arcosGeneral,envios.get(i));

            ambiente.setCaminos(arcos);
            ambiente.setNodoInicialFinal(envios.get(i).getOrigen(),envios.get(i).getDestino());
            ambiente.setPaquetesEnvio(envios.get(i).getPaquetes());
            ambiente.setFechaInicial(envios.get(i).getFechaEnvio());

            System.out.println("Arcos posibles");
            System.out.println(arcos);

            Aco algoritmoHormigas = new Aco();
            long start1 = System.currentTimeMillis();
            algoritmoHormigas.activarHormigas(ambiente);
            long end1 = System.currentTimeMillis();
            System.out.println("Elapsed Time in milli seconds: " + (end1 - start1));

            //Se imprime la solucion
            System.out.println("Envio "+ i +" - Camino: " + algoritmoHormigas.getSolucionCamino().toString() + algoritmoHormigas.getSolucionCosto());

            //Actualizar capacidades
            ambiente.actualizarCapacidades(algoritmoHormigas.getSolucionCamino());

            //Actualizar rutas de paquetes
            for(int j=0;j<=envios.get(i).getPaquetes().size();j++){
                envios.get(i).getPaquetes().get(i).setRuta(algoritmoHormigas.getSolucionCamino());
            }

            //Actualizar arcosGeneral (arcos)
            //ambiente.getCaminos();
            //ambiente.getNodos();

        }

        //Actualizar en BD:
        //ambiente.getNodos();  //capacidades de ambientes
        //arcosGeneral;         //capacidades de vuelos

        //Actualizar rutas de paquetes en BD:
//        for(int i=0;i<envios.size();i++) {
//            ArrayList<Paquete> paquetes = envios.get(i).getPaquetes();
//        for(int j=0;j<=paquetes.size();j++){
//            paquetes.get(i) //ruta
//        }
//        }

        return 1;
    }

}
