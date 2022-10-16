package com.bb.envifastservice.models;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name= "simulation")
public class SimulationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idSimulation")
    private Long id;
    private LocalDate fechaActualSimulacion;
    private String nombreSimulacion;
    private Long estado;
    private int active;
}
