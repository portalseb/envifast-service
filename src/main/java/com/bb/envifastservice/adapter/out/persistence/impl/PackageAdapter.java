package com.bb.envifastservice.adapter.out.persistence.impl;

import com.bb.envifastservice.adapter.out.persistence.repos.AirportRepository;
import com.bb.envifastservice.adapter.out.persistence.repos.PackageRepository;
import com.bb.envifastservice.algo.ArcoAeropuerto;
import com.bb.envifastservice.application.port.out.ShowPackageRoutePort;
import com.bb.envifastservice.hexagonal.PersistenceAdapter;
import com.bb.envifastservice.models.FlightModel;
import com.bb.envifastservice.models.PackageModel;
import lombok.RequiredArgsConstructor;

import java.util.List;
//TODO: terminar, verificar que se devuelva
@PersistenceAdapter
@RequiredArgsConstructor
public class PackageAdapter implements ShowPackageRoutePort {
    private final PackageRepository packageRepository;
    private final AirportRepository airportRepository;
    @Override
    public List<ArcoAeropuerto> showRoute(String id) {
        var pack = packageRepository.findByIdAndActive(id, 1);
        for (FlightModel vuelo:pack.getRoute()
             ) {
            var arco = new ArcoAeropuerto();

        }
        return null;
    }
}
