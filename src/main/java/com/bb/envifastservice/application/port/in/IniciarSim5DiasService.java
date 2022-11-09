package com.bb.envifastservice.application.port.in;

import java.io.FileNotFoundException;

public interface IniciarSim5DiasService {
    int iniciarSim5Dias(String fecha, Integer dias, Integer paraSim) throws FileNotFoundException;
}
