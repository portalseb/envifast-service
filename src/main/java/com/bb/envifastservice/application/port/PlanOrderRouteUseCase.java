package com.bb.envifastservice.application.port;

import com.bb.envifastservice.algo.Envio;
import com.bb.envifastservice.application.port.in.PlanOrderRouteService;
import com.bb.envifastservice.application.port.out.PlanOrderRoutePort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class PlanOrderRouteUseCase implements PlanOrderRouteService {
    private final PlanOrderRoutePort planOrderRoutePort;
    @Override
    public int planOrderRoute(List<Envio> envios){return planOrderRoutePort.planOrdRoute(envios);}

}
