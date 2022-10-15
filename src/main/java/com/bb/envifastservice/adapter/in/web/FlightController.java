package com.bb.envifastservice.adapter.in.web;

import com.bb.envifastservice.algo.ArcoAeropuerto;
import com.bb.envifastservice.application.port.in.ListFlightByIdService;
import com.bb.envifastservice.application.port.out.ListFlightByIdPort;
import com.bb.envifastservice.hexagonal.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@WebAdapter
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/flights")
public class FlightController {
    private final ListFlightByIdService listFlightByIdService;
    @GetMapping(value = "/{id}")
    public ArcoAeropuerto findVuelo(@RequestParam(name = "id") Long id){
        return listFlightByIdService.listById(id);
    }
}
