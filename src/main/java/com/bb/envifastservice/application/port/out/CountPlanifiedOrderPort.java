package com.bb.envifastservice.application.port.out;

public interface CountPlanifiedOrderPort {
    int countPlanifiedOrder(String fecha, String timeInf, String timeSup, Integer paraSim, Integer indicador);
}
