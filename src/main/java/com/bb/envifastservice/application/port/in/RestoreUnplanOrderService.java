package com.bb.envifastservice.application.port.in;

public interface RestoreUnplanOrderService {
    int restoreOrders(Integer planned, Integer paraSim);
}
