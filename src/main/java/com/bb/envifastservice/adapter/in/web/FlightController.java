package com.bb.envifastservice.adapter.in.web;

import com.bb.envifastservice.adapter.out.persistence.dtos.FlightMap;
import com.bb.envifastservice.algo.ArcoAeropuerto;
import com.bb.envifastservice.application.port.in.*;
import com.bb.envifastservice.application.port.out.ListFlightByIdPort;
import com.bb.envifastservice.hexagonal.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    private final RestoreFlightsService restoreFlightsService;
    private final FindFlightPackagesService findFlightPackagesService;
    private final CapacityFlightService capacityFlightService;
    private final CapacityAirportService capacityAirportService;
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
    public void generarVuelos(@RequestParam(name = "fecha") String fecha,  @RequestParam(name = "dias") Integer dias, @RequestParam(name = "paraSim") Integer paraSim) throws IOException {
        generateNextWeekFlightsService.generateNextWeekFlights(fecha, dias,paraSim);
    }
    @PutMapping(value = "/restore")
    public void restaurarVuelos(@RequestParam(name = "fecha") String fecha,  @RequestParam(name = "dias") Integer dias, @RequestParam(name = "paraSim") Integer paraSim){
        restoreFlightsService.restoreFlights(fecha,dias,paraSim);
    }
    @GetMapping(value = "/cantPaquetes")
    public int paquetesDeVuelo(@RequestParam(name = "fechaIni")String fechaIni,@RequestParam(name = "horaIni") String horaIni,@RequestParam(name = "origenId") Integer origenId,@RequestParam(name = "destinoId") Integer destinoId,@RequestParam(name = "paraSim") Integer paraSim){
        return findFlightPackagesService.findFlightPackages(fechaIni, horaIni, origenId, destinoId, paraSim);
    }
    @GetMapping(value = "/cantPaquetesAirport")
    public int cantPaquetesAirport(@RequestParam(name = "fecha") String fecha,@RequestParam(name = "timeInf")  String timeInf, @RequestParam(name = "code") String code) throws IOException{
        return capacityAirportService.capacityAirport(fecha, timeInf, code);
    }

    @GetMapping(value = "/cantPaquetesFlight")
    public int cantPaquetesFlight(@RequestParam(name = "fecha") String fecha,@RequestParam(name = "origenId")  Integer origenId, @RequestParam(name = "destinoId") Integer destinoId) throws IOException{
        return capacityFlightService.capacityFlights(fecha, origenId,destinoId);
    }

    @GetMapping(value = "/generateColapsoData")
    public int generateColapsoData(@RequestParam(name = "fecha") String fecha) throws InterruptedException {
        Thread.sleep(135000);
        return 1;
    }

}
