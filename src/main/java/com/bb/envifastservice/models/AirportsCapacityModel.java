package com.bb.envifastservice.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name= "airportCapacity")
public class AirportsCapacityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idAirportCapacity")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idDateTime", insertable = false, updatable = false)
    private DateTimeModel dateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idAirport", insertable = false, updatable = false)
    private AirportsModel airport;

    private int availableCapacity;

    @ManyToMany
    @JoinTable(
            name = "capacity_packages",
            joinColumns = @JoinColumn(name = "airports_capacity_id"),
            inverseJoinColumns = @JoinColumn(name = "package_id")
    )
    private List<PackageModel> warehouse = new ArrayList<>();
    private int active;
}
