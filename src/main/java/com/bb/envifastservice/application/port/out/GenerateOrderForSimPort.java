package com.bb.envifastservice.application.port.out;

import java.io.FileNotFoundException;

public interface GenerateOrderForSimPort {
    int generateSimulationOrders(String fecha, String timeInf,String timeSup, Integer forSim) throws FileNotFoundException;
}
