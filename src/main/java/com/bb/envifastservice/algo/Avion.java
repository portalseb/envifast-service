package com.bb.envifastservice.algo;

import java.util.ArrayList;

public class Avion {

    private String nombre;
    private ArrayList<Paquete> cargo = new ArrayList<>();
    private Integer capacidad;
    private int estaVolando;

    public Avion(String nombre){
        this.nombre = nombre;
        this.capacidad = 500; // inicialmente
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCargo(ArrayList<Paquete> cargo) {
        this.cargo = cargo;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public void setEstaVolando(int estaVolando) {
        this.estaVolando = estaVolando;
    }

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Paquete> getCargo() {
        return cargo;
    }

    public Integer getCapacidad() {
        return this.cargo.size();
    }

    public Integer getUso() {
        return this.cargo.size();
    }
    public int getEstaVolando() {
        return estaVolando;
    }

    public void agregarPaquete(Paquete pac) {
        this.cargo.add(pac);
        pac.actualizarEstado(null, this);
    }

}
