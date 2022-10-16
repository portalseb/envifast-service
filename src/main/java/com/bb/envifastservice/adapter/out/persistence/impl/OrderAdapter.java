package com.bb.envifastservice.adapter.out.persistence.impl;

import com.bb.envifastservice.adapter.out.persistence.repos.AirportRepository;
import com.bb.envifastservice.adapter.out.persistence.repos.OrderRepository;
import com.bb.envifastservice.adapter.out.persistence.repos.PackageRepository;
import com.bb.envifastservice.algo.Aeropuerto;
import com.bb.envifastservice.algo.Ciudad;
import com.bb.envifastservice.algo.Envio;
import com.bb.envifastservice.algo.Paquete;
import com.bb.envifastservice.application.port.out.InsertOrderPort;
import com.bb.envifastservice.application.port.out.ListPackagesPort;
import com.bb.envifastservice.hexagonal.PersistenceAdapter;
import com.bb.envifastservice.models.OrderModel;
import com.bb.envifastservice.models.PackageModel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@PersistenceAdapter
@RequiredArgsConstructor
public class OrderAdapter implements ListPackagesPort, InsertOrderPort {
    private final PackageRepository packageRepository;
    private final AirportRepository airportRepository;
    private final OrderRepository orderRepository;
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
        envioNuevo.setDestinatarioNombres(envio.getDestinatarioNombres());



        envioNuevo.setCantidad(envio.getCantidadPaquetes());
        envioNuevo.setFechaEnvio(envio.getFechaEnvio());

        envioNuevo.setToken(envio.getToken());

        envioNuevo.setOrigen(envio.getOrigen().getCiudad().getNombre());
        envioNuevo.setDestino(envio.getDestino().getCiudad().getNombre());

        envioNuevo.setTiempoTotal(0.0);
        envioNuevo.setActive(1);

        List<PackageModel> paquetes = new ArrayList<PackageModel>();

        for(int i=0;i<envio.getPaquetes().size();i++){
            var paqueteNuevo = new PackageModel();
            paqueteNuevo.setOrigen(envio.getPaquetes().get(i).getOrigen().getCiudad().getNombre());
            paqueteNuevo.setDestino(envio.getPaquetes().get(i).getDestino().getCiudad().getNombre());
            paquetes.add(paqueteNuevo);
            //packageRepository.save(new PackageModel());
        }

        envioNuevo.setPacks(paquetes);

        orderRepository.save(envioNuevo);
        envio.setId(envioNuevo.getId());

    return envio;
    }

}
