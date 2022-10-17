package com.bb.envifastservice.application.port.in;

import com.bb.envifastservice.algo.Envio;

import java.util.List;

public interface PlanOrderRouteService {
    int planOrderRoute(List<Envio> envios);
}
