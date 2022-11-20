package com.bb.envifastservice.application.port;

import com.bb.envifastservice.application.port.in.FindFlightPackagesService;
import com.bb.envifastservice.application.port.out.FindFlightPackagesPort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class FindFlightPackagesUseCase implements FindFlightPackagesService {
    private final FindFlightPackagesPort findFlightPackagesPort;

    @Override
    public int findFlightPackages(String fechaIni, String horaIni, Integer origenId, Integer destinoId, Integer paraSim) {
        return findFlightPackagesPort.findFlightPackages(fechaIni,horaIni,origenId,destinoId,paraSim);
    }
}
