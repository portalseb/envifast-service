package com.bb.envifastservice.application.port.in;

public interface CountPlanifiedOrderService {
    int countPlanifiedOrder(String fecha, String timeInf, String timeSup, Integer paraSim, Integer indicador);
}
