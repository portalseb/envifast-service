package com.bb.envifastservice.algo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.TimeZone;

public class Aeropuerto implements Comparable<Aeropuerto> {

    public static final Integer CAPACIDAD_AEROPUERTO = 500;
    private Integer id;
    private Ciudad ciudad;
    private String nombre;
    private TimeZone timeZone;
    private ArrayList<Paquete> deposito = new ArrayList<>();
    private Integer capacidad;

    public Aeropuerto(Integer id, String nombreCiudad, String ciudadAbreviada, String pais, String nombre,
                      String timeZone){
        this.id = id;
        this.ciudad = new Ciudad(nombreCiudad, ciudadAbreviada, pais);
        this.nombre = nombre;
        this.timeZone = TimeZone.getTimeZone(timeZone);
        this.capacidad = CAPACIDAD_AEROPUERTO;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTimeZone(TimeZone timeZone) {
        this.timeZone = timeZone;
    }

    public void setDeposito(ArrayList<Paquete> deposito) {
        this.deposito = deposito;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Integer getId() {
        return id;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public String getNombre() {
        return nombre;
    }

    public TimeZone getTimeZone() {
        return timeZone;
    }

    public ArrayList<Paquete> getDeposito() {
        return deposito;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public String paraImprimir(){
        return "Este es el aeropuerto " + this.nombre + ", estoy en " + this.timeZone.getID() + " en " +
                this.ciudad.paraImprimir();
    }

    public Integer getUso() {
        return this.deposito.size();
    }

    public void agregarPaquete(Paquete pac){
        this.deposito.add(pac);
        pac.actualizarEstado(this, null);
        setCapacidad(this.capacidad + 1);// aumentamos la capacidad
    }

    @Override
    public int compareTo(Aeropuerto o) {
        if(o.getUso() < this.getUso()){
            return 1; // o > este mismo
        }
        return 0;
    }
}
