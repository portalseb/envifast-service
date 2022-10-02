package com.bb.envifastservice.adapter.out.persistence.impl;

import com.bb.envifastservice.adapter.out.persistence.repos.PackageRepository;
import com.bb.envifastservice.algo.Paquete;
import com.bb.envifastservice.application.port.out.ListPackagesPort;
import com.bb.envifastservice.hexagonal.PersistenceAdapter;
import com.bb.envifastservice.models.PackageModel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@PersistenceAdapter
@RequiredArgsConstructor
public class PackageAdapter implements ListPackagesPort {
    private final PackageRepository packageRepository;
    @Override
    public List<Paquete> listByFields(String input) {
        var registros = packageRepository.findAllByFieldsLikeAndActive(input);
        var paquetes = new ArrayList<Paquete>();
        for (PackageModel pack: registros
             ) {
            var paquete = new Paquete();
            paquete.setDestino(pack.getDestino());
            paquete.setId(pack.getId());
            paquete.setOrigen(pack.getOrigen());
            paquetes.add(paquete);
        }
        return paquetes;
    }
}
