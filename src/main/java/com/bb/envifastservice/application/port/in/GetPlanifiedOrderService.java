package com.bb.envifastservice.application.port.in;

import com.bb.envifastservice.adapter.out.persistence.dtos.PackagePlanified;

import java.util.List;

public interface GetPlanifiedOrderService {
    List<PackagePlanified> getPlanifiedOrder(String fecha, String timeInf, String timeSup, Integer paraSim, Integer indicador);
}
