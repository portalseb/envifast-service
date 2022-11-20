package com.bb.envifastservice.application.port.out;

public interface FindFlightPackagesPort {
    int findFlightPackages(String fechaIni, String horaIni, Integer origenId, Integer destinoId, Integer paraSim);
}
