package com.bb.envifastservice.adapter.in.web;

import com.bb.envifastservice.adapter.out.persistence.dtos.AirportCoord;
import com.bb.envifastservice.algo.Aeropuerto;
import com.bb.envifastservice.application.port.in.GenerateNextWeekDateTimeService;
import com.bb.envifastservice.application.port.in.ListAirportCoordService;
import com.bb.envifastservice.application.port.in.ListAllAirportsService;
import com.bb.envifastservice.hexagonal.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@WebAdapter
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/airports")
public class AirportsController {
    private final ListAllAirportsService listAllAirportsService;
    private final ListAirportCoordService listAirportCoordService;
    private final GenerateNextWeekDateTimeService generateNextWeekDateTimeService;

    @GetMapping(value = "")
    public List<Aeropuerto> listAllAeropuertos(){
        return listAllAirportsService.listAllAirpoirts();
    }

    @GetMapping(value = "/coordinates")
    public List<AirportCoord>getCoordinates() {
        return  listAirportCoordService.listCoordinates();
    }

    @GetMapping(value = "/dateTimes")
    public void generarFechasHora(@RequestParam(name = "fecha") String fecha, @RequestParam(name = "dias") Integer dias){
        generateNextWeekDateTimeService.generateNextWeekDateTime(fecha, dias);
    }

}
