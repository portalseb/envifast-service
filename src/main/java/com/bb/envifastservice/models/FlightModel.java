package com.bb.envifastservice.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name= "Flight")
public class FlightModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "_id_flight")
    private Long id;
    @Column(name = "_max_plane_capacity")
    private Long maxCapacity;
    @Column(name = "_available_capacity")
    private Long availableCapacity;
    @Column(name = "_departed")
    private Boolean departed;
    @Column(name = "_arrived")
    private Boolean arrived;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "_airport_departure_id", insertable = true, updatable = true)
    private AirportsModel departureAirport;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "_airport_arrival_id", insertable = true, updatable = true)
    private AirportsModel arrivalAirport;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "route")
    private List<PackageModel> cargo = new ArrayList<>();
    @Column(name = "_departure_date")
    private LocalDateTime departureTime;
    @Column(name = "_arrival_date")
    private LocalDateTime arrivalTime;
    private int isFlying;
    @Column(name = "_active")
    private int active;
    @Column(name = "_for_sim")
    private int forSim;
}
