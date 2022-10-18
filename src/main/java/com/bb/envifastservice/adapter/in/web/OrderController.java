package com.bb.envifastservice.adapter.in.web;

import com.bb.envifastservice.algo.Envio;
import com.bb.envifastservice.application.port.in.InsertOrderService;
import com.bb.envifastservice.application.port.in.PlanOrderRouteService;
import com.bb.envifastservice.hexagonal.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@WebAdapter
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
public class OrderController {
    private final InsertOrderService insertOrderService;

    private final PlanOrderRouteService planOrderRouteService;

    @PostMapping(value = "/insert")
    public Envio insertarEnvio(@RequestBody Envio envio){
        return insertOrderService.insertOrder(envio);
    }

    @PostMapping(value = "/plan")
    public int planificarEnvios(@RequestBody List<Envio> envios){return planOrderRouteService.planOrderRoute(envios);}

}