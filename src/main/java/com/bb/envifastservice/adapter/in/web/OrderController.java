package com.bb.envifastservice.adapter.in.web;

import com.bb.envifastservice.algo.Envio;
import com.bb.envifastservice.application.port.in.GenerateSimulationOrderService;
import com.bb.envifastservice.application.port.in.InsertOrderService;
import com.bb.envifastservice.application.port.in.ListPackagesService;
import com.bb.envifastservice.application.port.in.PlanOrderRouteService;
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

    private final PlanOrderRouteService planOrderRouteService;
    private final ListPackagesService listPackagesService;
    private final GenerateSimulationOrderService generateSimulationOrderService;

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
}
