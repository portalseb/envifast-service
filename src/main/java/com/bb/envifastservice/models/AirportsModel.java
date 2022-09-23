package com.bb.envifastservice.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name= "airport")
public class AirportsModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "_id_airport")
    private Long id;
    @Column(name ="_airport_code")
    private String airportCode;
    @Column(name="_city_name")
    private String cityName;
    @Column(name = "_position_x")
    private Double xPos;
    @Column(name = "_position_y")
    private Double yPos;
    @Column(name = "_warehouse_max_capacity")
    private int maxCapacity;
    @Column(name = "_available_capacity")
    private int availableCapacity;
    @Column(name = "_city_short_name")
    private String cityShortName;
    @Column(name="_country_name")
    private String countryName;
    @Column(name = "_continent")
    private int continent; //1 SA, 2 EU
    @Column(name = "_active")
    private int active;

}
