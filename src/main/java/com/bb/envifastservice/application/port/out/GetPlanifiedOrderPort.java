package com.bb.envifastservice.application.port.out;

import com.bb.envifastservice.adapter.out.persistence.dtos.PackagePlanified;

import java.util.List;

public interface GetPlanifiedOrderPort {
    List<PackagePlanified> getPlanifiedOrder(String fecha, String timeInf, String timeSup, Integer paraSim, Integer indicador);
}
