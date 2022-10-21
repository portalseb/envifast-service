package com.bb.envifastservice.adapter.in.web;

import com.bb.envifastservice.adapter.out.persistence.dtos.FlightMap;
import com.bb.envifastservice.algo.ArcoAeropuerto;
import com.bb.envifastservice.application.port.in.GenerateNextWeekFlightsService;
import com.bb.envifastservice.application.port.in.ListAllFlightsService;
import com.bb.envifastservice.application.port.in.ListFlightByIdService;
import com.bb.envifastservice.application.port.out.ListFlightByIdPort;
import com.bb.envifastservice.hexagonal.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@WebAdapter
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/flights")
public class FlightController {
    private final ListFlightByIdService listFlightByIdService;
    private final GenerateNextWeekFlightsService generateNextWeekFlightsService;
    private final ListAllFlightsService listAllFlightsService;
    @GetMapping(value = "/{id}")
    public ArcoAeropuerto findVuelo(@RequestParam(name = "id") Long id){
        return listFlightByIdService.listById(id);
    }

    @GetMapping(value = "/{fecha}")
    public List<FlightMap> listAllVuelos(@RequestParam(name = "fecha") String fecha){
        return listAllFlightsService.listAllFlights(fecha);
    }

    @GetMapping(value = "/generate")
    public void generarVuelos(){
        generateNextWeekFlightsService.generateNextWeekFlights();
    }
}
