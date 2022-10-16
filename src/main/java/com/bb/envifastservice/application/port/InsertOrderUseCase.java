package com.bb.envifastservice.application.port;

import com.bb.envifastservice.algo.Envio;
import com.bb.envifastservice.application.port.in.InsertOrderService;
import com.bb.envifastservice.application.port.out.InsertOrderPort;
import com.bb.envifastservice.hexagonal.UseCase;
import com.bb.envifastservice.models.OrderModel;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class InsertOrderUseCase implements InsertOrderService {
    private final InsertOrderPort insertOrderPort;
    @Override
    public Envio insertOrder(Envio envio){return insertOrderPort.insertOrd(envio);}

}
