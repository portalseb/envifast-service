package com.bb.envifastservice.application.port.out;

import java.io.FileNotFoundException;

public interface GenerateSimulationOrderPort {
    int generateSimulationOrder(String fecha) throws FileNotFoundException;
}
