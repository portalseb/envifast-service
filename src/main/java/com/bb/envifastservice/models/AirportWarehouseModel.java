package com.bb.envifastservice.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name= "airport_warehouse")
public class AirportWarehouseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "_aw_id")
    private Long id;
    @Column(name ="_oaci_code")
    private String oaciCode;
    @Column(name="_city_name")
    private String cityName;
    @OneToMany(mappedBy = "AirportWarehouse")
    Set<FlightModel> flightModels;
    @Column(name = "_city_short_name")
    private String cityShortName;
    @Column(name="_country_name")
    private String countryName;
    @Column(name = "_continent")
    private int continent; //1 SA, 2 EU
    @Column(name = "_x_pos")
    private Double xPos;
    @Column(name = "_y_pos")
    private Double yPos;
    @Column(name = "_max_capacity")
    private int maxCapacity;
    @Column(name = "_available_capacity")
    private int availableCapacity;
    @Column(name = "_active")
    private int active;

}
