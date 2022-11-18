package com.bb.envifastservice.models;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name= "capacidad")
public class CapacityModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "_id_capacity")
    private Long id;
    @Column(name = "_capacity_value")
    private Long value;
    @Column(name = "_capacity_description")
    private String description;
    @Column(name = "_active")
    private int active;
}
