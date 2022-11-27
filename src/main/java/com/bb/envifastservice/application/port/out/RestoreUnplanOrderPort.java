package com.bb.envifastservice.application.port.out;

public interface RestoreUnplanOrderPort {
    int restoreOrders(Integer planned, Integer paraSim);
}
