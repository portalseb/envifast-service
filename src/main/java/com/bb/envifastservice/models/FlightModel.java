package com.bb.envifastservice.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name= "flights")
public class FlightModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_flight_id")
    private Long id;
    @Column(name = "_max_capacity")
    private Long maxCapacity;
    @Column(name = "_available_capacity")
    private Long availableCapacity;
    @Column(name = "_departure_date")
    private LocalDateTime departureDate;
    @Column(name = "_arrival_date")
    private LocalDateTime arrivalDate;
    @ManyToOne
    @JoinColumn(name = "_arrival_airport", nullable = false)
    AirportWarehouseModel arrivalAirport;
//    @ManyToOne
//    @JoinColumn(name = "_departure_airport", nullable = false)
//    AirportWarehouse departureAirport;
    @Column(name = "_departure_hour")
    private int departureHour;
    @Column(name = "_departure_minutes")
    private int departureMinutes;
    @Column(name = "_arrival_hour")
    private int arrivalHour;
    @Column(name = "_arrival_minutes")
    private int arrivalMinutes;

}
