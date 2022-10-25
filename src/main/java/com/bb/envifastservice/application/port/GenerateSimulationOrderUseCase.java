package com.bb.envifastservice.application.port;
import com.bb.envifastservice.application.port.in.GenerateSimulationOrderService;
import com.bb.envifastservice.application.port.out.GenerateSimulationOrderPort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

import java.io.FileNotFoundException;

@UseCase
@RequiredArgsConstructor
public class GenerateSimulationOrderUseCase implements GenerateSimulationOrderService {
    private final GenerateSimulationOrderPort simulationOrderPort;
    @Override
    public int generateSimulationOrder(String fecha) throws FileNotFoundException {
        return simulationOrderPort.generateSimulationOrder(fecha);
    }

}
