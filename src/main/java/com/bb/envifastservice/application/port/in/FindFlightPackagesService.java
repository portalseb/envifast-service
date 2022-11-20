package com.bb.envifastservice.application.port.in;

public interface FindFlightPackagesService {
    int findFlightPackages(String fechaIni, String horaIni, Integer origenId, Integer destinoId, Integer paraSim);
}
