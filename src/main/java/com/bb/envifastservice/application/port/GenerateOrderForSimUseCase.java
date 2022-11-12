package com.bb.envifastservice.application.port;

import com.bb.envifastservice.application.port.in.GenerateOrderForSimService;
import com.bb.envifastservice.application.port.out.GenerateOrderForSimPort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

import java.io.FileNotFoundException;

@UseCase
@RequiredArgsConstructor
public class GenerateOrderForSimUseCase implements GenerateOrderForSimService {
    private final GenerateOrderForSimPort generateOrderForSimPort;

    @Override
    public int generateSimulationOrders(String fecha, String timeInf, String timeSup, Integer forSim) throws FileNotFoundException {
        return generateOrderForSimPort.generateSimulationOrders(fecha,timeInf,timeSup,forSim);
    }
}
