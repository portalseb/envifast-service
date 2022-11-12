package com.bb.envifastservice.application.port.in;

import java.io.FileNotFoundException;

public interface GenerateOrderForSimService {
    int generateSimulationOrders(String fecha, String timeInf,String timeSup, Integer forSim) throws FileNotFoundException;
}
