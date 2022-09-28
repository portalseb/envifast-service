package com.bb.envifastservice.models;

import com.bb.envifastservice.algo.Aeropuerto;
import com.bb.envifastservice.algo.ArcoAeropuerto;
import com.bb.envifastservice.algo.Avion;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Package")
public class PackageModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDateTime dateTime;
    @ManyToMany
    @JoinTable(
            name = "route",
            joinColumns = @JoinColumn(name = "package_id"),
            inverseJoinColumns = @JoinColumn(name = "flight_id")
    )
    private List<FlightModel> route = new ArrayList<>();

    private AirportsModel currentAirport;
    private FlightModel currentFlight;
    private String destino;
    private String origen;

}
