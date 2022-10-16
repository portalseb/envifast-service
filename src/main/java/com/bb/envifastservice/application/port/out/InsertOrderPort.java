package com.bb.envifastservice.application.port.out;

import com.bb.envifastservice.algo.Envio;
import com.bb.envifastservice.models.OrderModel;

public interface InsertOrderPort {
    Envio insertOrd(Envio envio);
}
