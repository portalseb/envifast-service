package com.bb.envifastservice.application.port.out;

public interface GenerateNextWeekDateTimePort {
    void generateNextWeekDateTime(String fecha, Integer dias, Integer forSim);
}
