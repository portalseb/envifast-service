package com.bb.envifastservice.algo;

import java.util.ArrayList;

public class CapacidadAeropuerto {
    private Integer id;
    private FechaHora fechaHora;
    private Integer capacidadDisponible;

    private ArrayList<Paquete> deposito;

    public CapacidadAeropuerto() {
    }

    public CapacidadAeropuerto(Integer id, FechaHora fechaHora, Integer capacidadDisponible) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.capacidadDisponible = capacidadDisponible;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public FechaHora getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(FechaHora fechaHora) {
        this.fechaHora = fechaHora;
    }

    public Integer getCapacidadDisponible() {
        return capacidadDisponible;
    }

    public void setCapacidadDisponible(Integer capacidadDisponible) {
        this.capacidadDisponible = capacidadDisponible;
    }

    public ArrayList<Paquete> getDeposito() {
        return deposito;
    }

    public void setDeposito(ArrayList<Paquete> deposito) {
        this.deposito = deposito;
    }

    public void agregarPaquete(Paquete pac){
        this.deposito.add(pac);
        //pac.actualizarEstado(this, null);
        this.capacidadDisponible = this.capacidadDisponible-1;
    }

    public void removerPaquete(Paquete pac){
        this.deposito.remove(pac);
        //pac.actualizarEstado(this, null);
        this.capacidadDisponible = this.capacidadDisponible+1;
    }


}
