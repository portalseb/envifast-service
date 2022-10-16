package com.bb.envifastservice.application.port.in;

import com.bb.envifastservice.algo.Envio;
import com.bb.envifastservice.models.OrderModel;

public interface InsertOrderService {
    Envio insertOrder(Envio envio);
}
