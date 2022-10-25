package com.bb.envifastservice.application.port.in;

import java.io.FileNotFoundException;

public interface GenerateSimulationOrderService {
    int generateSimulationOrder(String fecha) throws FileNotFoundException;
}
