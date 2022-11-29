package com.bb.envifastservice.adapter.in.web;

import com.bb.envifastservice.adapter.out.persistence.dtos.FlightRoute;
import com.bb.envifastservice.algo.ArcoAeropuerto;
import com.bb.envifastservice.algo.Envio;
import com.bb.envifastservice.algo.Paquete;
import com.bb.envifastservice.application.port.in.GenerateOrderForSimService;
import com.bb.envifastservice.application.port.in.IniciarSim5DiasService;
import com.bb.envifastservice.application.port.in.ListPackagesService;
import com.bb.envifastservice.application.port.in.ShowPackageRouteService;
import com.bb.envifastservice.hexagonal.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@WebAdapter
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/packages")
public class PackageController {

    private final ShowPackageRouteService showPackageRouteService;
    private final IniciarSim5DiasService iniciarSim5DiasService;

    @GetMapping(value = "/route/{id}")
    public List<FlightRoute> getPackageRoute(@RequestParam(name = "id") String id){
        return showPackageRouteService.showRoute(id);
    }
    @PostMapping(value = "/cargarEnviosSim")
    public int cargarEnviosSim(@RequestParam(name = "fecha") String fecha,@RequestParam(name = "timeInf")  String timeInf, @RequestParam(name = "timeSup") String timeSup, @RequestParam(name = "paraSim") Integer forSim) throws FileNotFoundException {
        iniciarSim5DiasService.iniciarSim5Dias(fecha,5,forSim);
        if(LocalDateTime.of(LocalDate.parse(fecha), LocalTime.parse(timeSup)).isAfter(LocalDateTime.of(LocalDate.of(2023,3,13),LocalTime.of(15,59)))){
            return 0;
        }
        return 1;
    }
}
