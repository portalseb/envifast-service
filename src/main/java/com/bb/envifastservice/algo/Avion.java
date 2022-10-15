package com.bb.envifastservice.algo;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;

public class Avion {
    private String nombre;

    private Integer capacidadTotal;

    private int estaVolando;

    public Avion(String nombre){
        this.nombre = nombre;
        this.capacidadTotal = 500; // inicialmente
    }

    public Avion(String nombre, Integer capacidadTotal) {
        this.nombre = nombre;
        this.capacidadTotal = capacidadTotal;
    }

    public Avion() {

    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstaVolando(int estaVolando) {
        this.estaVolando = estaVolando;
    }

    public String getNombre() {
        return nombre;
    }


    public int getEstaVolando() {
        return estaVolando;
    }

    public Integer getCapacidadTotal() {
        return capacidadTotal;
    }

    public void setCapacidadTotal(Integer capacidadTotal) {
        this.capacidadTotal = capacidadTotal;
    }

}
