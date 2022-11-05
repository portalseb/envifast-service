package com.bb.envifastservice.application.port.in;

import com.bb.envifastservice.algo.Envio;

public interface GetOrderForUserService {
    Envio getOrderForUser(String token, String docNo);
}
