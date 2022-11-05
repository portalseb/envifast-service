package com.bb.envifastservice.application.port.out;

import com.bb.envifastservice.algo.Envio;

public interface GetOrderForUserPort {
    Envio getOrderForUser(String token, String docNo);
}
