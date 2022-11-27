package com.bb.envifastservice.application.port;

import com.bb.envifastservice.application.port.in.RestoreUnplanOrderService;
import com.bb.envifastservice.application.port.out.RestoreUnplanOrderPort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class RestoreUnplanOrderUseCase implements RestoreUnplanOrderService {
    private final RestoreUnplanOrderPort restoreUnplanOrderPort;

    @Override
    public int restoreOrders(Integer planned, Integer paraSim) {
        return restoreUnplanOrderPort.restoreOrders(planned,paraSim);
    }
}
