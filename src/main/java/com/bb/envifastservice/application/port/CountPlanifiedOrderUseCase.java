package com.bb.envifastservice.application.port;

import com.bb.envifastservice.application.port.in.CountPlanifiedOrderService;
import com.bb.envifastservice.application.port.out.CountPlanifiedOrderPort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CountPlanifiedOrderUseCase implements CountPlanifiedOrderService {
    private final CountPlanifiedOrderPort countPlanifiedOrderPort;

    @Override
    public int countPlanifiedOrder(String fecha, String timeInf, String timeSup, Integer paraSim, Integer indicador) {
        return countPlanifiedOrderPort.countPlanifiedOrder(fecha,timeInf,timeSup,paraSim,indicador);
    }
}
