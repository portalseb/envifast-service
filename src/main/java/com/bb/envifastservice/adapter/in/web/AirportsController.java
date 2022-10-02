package com.bb.envifastservice.adapter.in.web;

import com.bb.envifastservice.algo.Aeropuerto;
import com.bb.envifastservice.application.port.in.ListAllAirportsService;
import com.bb.envifastservice.hexagonal.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@WebAdapter
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/airports")
public class AirportsController {
    private final ListAllAirportsService listAllAirportsService;
    @GetMapping(value = "")
    public List<Aeropuerto> listAllAeropuertos(){
        return listAllAirportsService.listAllAirpoirts();
    }
}
