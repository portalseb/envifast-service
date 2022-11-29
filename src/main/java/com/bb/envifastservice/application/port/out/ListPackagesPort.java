package com.bb.envifastservice.application.port.out;

import com.bb.envifastservice.algo.Envio;
import com.bb.envifastservice.algo.Paquete;

import java.util.List;

public interface ListPackagesPort {
    List<Envio> listByFields(String input, Integer forSim);
}
