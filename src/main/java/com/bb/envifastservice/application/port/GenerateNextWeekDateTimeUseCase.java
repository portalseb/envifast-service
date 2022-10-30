package com.bb.envifastservice.application.port;

import com.bb.envifastservice.application.port.in.GenerateNextWeekDateTimeService;
import com.bb.envifastservice.application.port.out.GenerateNextWeekDateTimePort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GenerateNextWeekDateTimeUseCase implements GenerateNextWeekDateTimeService {
    private final GenerateNextWeekDateTimePort generateNextWeekDateTimePort;
    @Override
    public void generateNextWeekDateTime(String fecha, Integer dias){generateNextWeekDateTimePort.generateNextWeekDateTime(fecha,dias);}
}
