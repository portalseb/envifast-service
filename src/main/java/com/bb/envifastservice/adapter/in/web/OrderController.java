package com.bb.envifastservice.adapter.in.web;

import com.bb.envifastservice.algo.Envio;
import com.bb.envifastservice.application.port.in.*;
import com.bb.envifastservice.hexagonal.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@WebAdapter
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/orders")
public class OrderController {
    private final InsertOrderService insertOrderService;
    private final GetOrderForUserService getOrderForUserService;
    private final PlanOrderRouteService planOrderRouteService;
    private final ListPackagesService listPackagesService;
    private final GenerateSimulationOrderService generateSimulationOrderService;
    private final GenerateNextWeekFlightsService generateNextWeekFlightsService;
    private final GenerateNextWeekDateTimeService generateNextWeekDateTimeService;
    @PostMapping(value = "/insert")
    public Envio insertarEnvio(@RequestBody Envio envio){
        return insertOrderService.insertOrder(envio);
    }

    @PostMapping(value = "/plan")
    public int planificarEnvios(@RequestBody List<Envio> envios){return planOrderRouteService.planOrderRoute(envios);}

    @PostMapping(value = "/cargar")
    public int cargarEnvios(@RequestParam(name = "fecha") String fecha) throws FileNotFoundException { return generateSimulationOrderService.generateSimulationOrder(fecha);}
    @GetMapping(value = "")
    public List<Envio> listPaquetes(Optional<String> input){
        if (input.isPresent()){
            return listPackagesService.listByFields(input.get());}
        else{
            return listPackagesService.listByFields("");}
    }
    @GetMapping(value = "/user")
    public Envio getEnvioUser(@RequestParam(name="token")String token,@RequestParam(name="docNo")String docNo){
        return getOrderForUserService.getOrderForUser(token,docNo);
    }
    @PostMapping(value = "/iniciar")
    public int iniciarSimCincoDias(@RequestParam(name = "fecha") String fecha, @RequestParam(name = "dias") Integer dias, @RequestParam(name = "paraSim") Integer paraSim) throws FileNotFoundException {
        generateNextWeekFlightsService.generateNextWeekFlights(fecha, dias, paraSim);
        generateNextWeekDateTimeService.generateNextWeekDateTime(fecha, dias,paraSim);
        return generateSimulationOrderService.generateSimulationOrder(fecha);
    }
}
