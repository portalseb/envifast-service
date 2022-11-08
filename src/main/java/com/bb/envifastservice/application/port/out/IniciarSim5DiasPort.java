package com.bb.envifastservice.application.port.out;

import java.io.FileNotFoundException;

public interface IniciarSim5DiasPort {
    int iniciarSim5Dias(String fecha, Integer dias, Integer paraSim) throws FileNotFoundException;
}
