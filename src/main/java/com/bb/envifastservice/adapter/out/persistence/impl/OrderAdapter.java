package com.bb.envifastservice.adapter.out.persistence.impl;

import com.bb.envifastservice.adapter.out.persistence.repos.AirportRepository;
import com.bb.envifastservice.adapter.out.persistence.repos.OrderRepository;
import com.bb.envifastservice.adapter.out.persistence.repos.PackageRepository;
import com.bb.envifastservice.algo.Aeropuerto;
import com.bb.envifastservice.algo.Ciudad;
import com.bb.envifastservice.algo.Envio;
import com.bb.envifastservice.algo.Paquete;
import com.bb.envifastservice.application.port.out.ListPackagesPort;
import com.bb.envifastservice.hexagonal.PersistenceAdapter;
import com.bb.envifastservice.models.OrderModel;
import com.bb.envifastservice.models.PackageModel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@PersistenceAdapter
@RequiredArgsConstructor
public class OrderAdapter implements ListPackagesPort {
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
                cDestino2.setPais(destino.getCountryName());
                cDestino2.setNombre(destino.getCityName());
                cDestino2.setContinente(destino.getContinent());
                cDestino2.setAbreviacion(destino.getCityShortName());
                aDestino2.setCiudad(cDestino);
                aDestino2.setCodigo(destino.getAirportCode());
                aDestino2.setCapacidad(destino.getMaxCapacity());
                aDestino2.setId(Math.toIntExact(destino.getId()));
                var aOrigen2 = new Aeropuerto();
                var cOrigen2 = new Ciudad();
                cOrigen2.setPais(origen.getCountryName());
                cOrigen2.setNombre(origen.getCityName());
                cOrigen2.setContinente(origen.getContinent());
                cOrigen2.setAbreviacion(origen.getCityShortName());
                aOrigen2.setCiudad(cOrigen);
                aOrigen2.setCodigo(origen.getAirportCode());
                aOrigen2.setCapacidad(origen.getMaxCapacity());
                aOrigen2.setId(Math.toIntExact(origen.getId()));

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

}
