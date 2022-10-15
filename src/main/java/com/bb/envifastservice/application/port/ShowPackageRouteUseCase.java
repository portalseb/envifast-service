package com.bb.envifastservice.application.port;

import com.bb.envifastservice.algo.ArcoAeropuerto;
import com.bb.envifastservice.application.port.in.ShowPackageRouteService;
import com.bb.envifastservice.application.port.out.ShowPackageRoutePort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase@RequiredArgsConstructor
public class ShowPackageRouteUseCase implements ShowPackageRouteService {
    private final ShowPackageRoutePort showPackageRoutePort;

    @Override
    public List<ArcoAeropuerto> showRoute(String id) {
        return showPackageRoutePort.showRoute(id);
    }
}
