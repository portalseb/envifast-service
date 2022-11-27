package com.bb.envifastservice.adapter.in.web;

import com.bb.envifastservice.adapter.out.persistence.dtos.FlightRoute;
import com.bb.envifastservice.algo.ArcoAeropuerto;
import com.bb.envifastservice.algo.Envio;
import com.bb.envifastservice.algo.Paquete;
import com.bb.envifastservice.application.port.in.ListPackagesService;
import com.bb.envifastservice.application.port.in.ShowPackageRouteService;
import com.bb.envifastservice.hexagonal.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@WebAdapter
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/packages")
public class PackageController {

    private final ShowPackageRouteService showPackageRouteService;

    @GetMapping(value = "/route/{id}")
    public List<FlightRoute> getPackageRoute(@RequestParam(name = "id") String id){
        return showPackageRouteService.showRoute(id);
    }
}
