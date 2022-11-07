package com.bb.envifastservice.application.port.in;

public interface GenerateNextWeekDateTimeService {
    void generateNextWeekDateTime(String fecha, Integer dias, Integer paraSim);
}
