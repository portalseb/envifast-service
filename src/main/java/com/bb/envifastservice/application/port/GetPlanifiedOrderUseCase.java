package com.bb.envifastservice.application.port;

import com.bb.envifastservice.adapter.out.persistence.dtos.PackagePlanified;
import com.bb.envifastservice.application.port.in.GetPlanifiedOrderService;
import com.bb.envifastservice.application.port.out.GetPlanifiedOrderPort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetPlanifiedOrderUseCase implements GetPlanifiedOrderService {
    private final GetPlanifiedOrderPort getPlanifiedOrderPort;

    @Override
    public List<PackagePlanified> getPlanifiedOrder(String fecha, String timeInf, String timeSup, Integer paraSim, Integer indicador) {
        return getPlanifiedOrderPort.getPlanifiedOrder(fecha,timeInf,timeSup,paraSim,indicador);
    }
}
