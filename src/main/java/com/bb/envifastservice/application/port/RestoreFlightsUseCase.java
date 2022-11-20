package com.bb.envifastservice.application.port;
import com.bb.envifastservice.application.port.in.RestoreFlightsService;
import com.bb.envifastservice.application.port.out.RestoreFlightsPort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RestoreFlightsUseCase implements RestoreFlightsService {
    private final RestoreFlightsPort restoreFlightsPort;

    @Override
    public void restoreFlights(String fecha, Integer dias, Integer paraSim) {
        restoreFlightsPort.restoreFlights(fecha,dias,paraSim);
    }
}
