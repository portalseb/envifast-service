package com.bb.envifastservice.application.port;

import com.bb.envifastservice.algo.Envio;
import com.bb.envifastservice.application.port.in.GetOrderForUserService;
import com.bb.envifastservice.application.port.out.GetOrderForUserPort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class GetOrderForUserUseCase implements GetOrderForUserService {
    private final GetOrderForUserPort getOrderForUserPort;
    @Override
    public Envio getOrderForUser(String token, String docNo) {
        return getOrderForUserPort.getOrderForUser(token, docNo);
    }
}
