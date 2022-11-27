package com.bb.envifastservice.adapter.in.web;

import com.bb.envifastservice.adapter.out.persistence.dtos.PackagePlanified;
import com.bb.envifastservice.algo.Envio;
import com.bb.envifastservice.application.port.in.*;
import com.bb.envifastservice.hexagonal.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
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
    private final IniciarSim5DiasService iniciarSim5DiasService;
    private final GenerateOrderForSimService generateOrderForSimService;
    private final RestoreUnplanOrderService restoreUnplanOrderService;
    private final GetPlanifiedOrderService getPlanifiedOrderService;
    @PostMapping(value = "/insert")
    public Envio insertarEnvio(@RequestBody Envio envio){
        return insertOrderService.insertOrder(envio);
    }

    @PostMapping(value = "/plan")
    public int planificarEnvios(){return planOrderRouteService.planOrderRoute();}

    @PostMapping(value = "/cargar")
    public int cargarEnvios(@RequestParam(name = "fecha") String fecha) throws FileNotFoundException {
        return generateSimulationOrderService.generateSimulationOrder(fecha);
    }
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
        return iniciarSim5DiasService.iniciarSim5Dias(fecha, dias, paraSim);
    }
    @PostMapping(value = "/cargarEnviosSim")
    public int cargarEnviosSim(@RequestParam(name = "fecha") String fecha,@RequestParam(name = "timeInf")  String timeInf, @RequestParam(name = "timeSup") String timeSup, @RequestParam(name = "paraSim") Integer forSim) throws IOException {
        return generateOrderForSimService.generateSimulationOrders(fecha,timeInf,timeSup,forSim);
    }
    @DeleteMapping(value = "/deleteUnplanified")
    public int restaurarOrdenesNoPlanificadas(@RequestParam(name = "planned") Integer planned, @RequestParam(name = "paraSim") Integer paraSim){
        return restoreUnplanOrderService.restoreOrders(planned,paraSim);
    }

    @GetMapping(value = "/planifiedOrders")
    @ResponseBody
    public List<PackagePlanified> getPlanifiedOrders(@RequestParam(name = "fecha")String fecha, @RequestParam(name = "timeInf") String timeInf, @RequestParam(name = "timeSup") String timeSup, @RequestParam(name = "paraSim") Integer paraSim){
        return getPlanifiedOrderService.getPlanifiedOrder(fecha, timeInf, timeSup, paraSim);
    }

}
