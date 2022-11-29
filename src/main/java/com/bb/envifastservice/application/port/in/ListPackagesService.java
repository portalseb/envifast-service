package com.bb.envifastservice.application.port.in;

import com.bb.envifastservice.algo.Envio;
import com.bb.envifastservice.algo.Paquete;

import java.util.List;

public interface ListPackagesService {
    List<Envio> listByFields(String input, Integer forSim);
}
