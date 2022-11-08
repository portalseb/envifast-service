package com.bb.envifastservice.adapter.in.web;

import com.bb.envifastservice.adapter.out.persistence.dtos.FlightMap;
import com.bb.envifastservice.algo.ArcoAeropuerto;
import com.bb.envifastservice.application.port.in.GenerateNextWeekFlightsService;
import com.bb.envifastservice.application.port.in.ListAllFlightsService;
import com.bb.envifastservice.application.port.in.ListDayFlightsService;
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
    private final ListDayFlightsService listDayFlightsService;
    @GetMapping(value = "/{id}")
    public ArcoAeropuerto findVuelo(@RequestParam(name = "id") Long id){
        return listFlightByIdService.listById(id);
    }

    @GetMapping(value = "/periodFlights/{fecha} {per} {paraSim}")
    public List<FlightMap> listAllVuelos(@RequestParam(name = "fecha") String fecha, @RequestParam(name = "per") Integer per, @RequestParam(name = "paraSim") Integer paraSim){
        return listAllFlightsService.listAllFlights(fecha,per,paraSim);
    }

    @GetMapping(value = "/dayFlightsFive/{fecha} {paraSim}")
    public List<FlightMap> listDayVuelosFive(@RequestParam(name = "fecha") String fecha, @RequestParam(name = "paraSim") Integer paraSim){
        return listDayFlightsService.listDayFlights(fecha,paraSim);
    }

    @PostMapping(value = "/generate")
    public void generarVuelos(@RequestParam(name = "fecha") String fecha,  @RequestParam(name = "dias") Integer dias, @RequestParam(name = "paraSim") Integer paraSim){
        generateNextWeekFlightsService.generateNextWeekFlights(fecha, dias,paraSim);
    }
}
