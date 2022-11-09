package com.bb.envifastservice.application.port;

import com.bb.envifastservice.application.port.in.IniciarSim5DiasService;
import com.bb.envifastservice.application.port.out.IniciarSim5DiasPort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

import java.io.FileNotFoundException;

@UseCase
@RequiredArgsConstructor
public class IniciarSim5DiasUseCase implements IniciarSim5DiasService {
    private final IniciarSim5DiasPort iniciarSim5DiasPort;

    @Override
    public int iniciarSim5Dias(String fecha, Integer dias, Integer paraSim) throws FileNotFoundException {
        return iniciarSim5DiasPort.iniciarSim5Dias(fecha, dias, paraSim);
    }
}
