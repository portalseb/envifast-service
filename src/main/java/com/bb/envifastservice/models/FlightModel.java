package com.bb.envifastservice.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name= "flight")
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_airport_departure_id", insertable = false, updatable = false)
    private AirportsModel departureAirport;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "_airport_arrival_id", insertable = false, updatable = false)
    private AirportsModel arrivalAirport;
    @Column(name = "_departure_date")
    private LocalDateTime departureDate;
    @Column(name = "_arrival_date")
    private LocalDateTime arrivalDate;
    @Column(name = "_flying_time")
    private LocalDateTime flyingTime;
    @Column(name = "_active")
    private int active;

}
