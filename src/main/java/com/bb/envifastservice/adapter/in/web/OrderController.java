package com.bb.envifastservice.adapter.in.web;

import com.bb.envifastservice.algo.Envio;
import com.bb.envifastservice.application.port.in.InsertOrderService;
import com.bb.envifastservice.hexagonal.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
public class OrderController {
    private final InsertOrderService insertOrderService;

    @PostMapping(value = "/orders")
    public Envio insertarEnvio(@RequestBody Envio envio){
        return insertOrderService.insertOrder(envio);
    }
}
