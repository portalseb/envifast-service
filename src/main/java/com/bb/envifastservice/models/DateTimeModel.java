package com.bb.envifastservice.models;

import com.sun.xml.bind.v2.runtime.reflect.Lister;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "dateAndTime")
public class DateTimeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idDateTime")
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private int active;

}
