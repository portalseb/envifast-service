package com.bb.envifastservice.algo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class Aeropuerto {
    private int id;
    private String codigo;
    private String ciudad;
    private String pais;
    private String abreviacion;

    private String continente;

    private int almacen;

    private Map<Aeropuerto, Integer> costoMinimo;

    public void inicializarCostos(ArrayList<Aeropuerto> aeropuertos){
        // inicializar los costos minimos con infinito, que para este caso
        // es el tiempo minimo de REdEx.
        // inicializar infinito = politica + 1 o poner infinito.

    }

    public void calculoCostosMinimo(ArrayList<PlanVuelo> planVuelos){
        //
    }

    // getters y setters. Entonces ya tenemos la informacion de los aeropuertos
    public int getId() {
        return id;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getPais() {
        return pais;
    }

    public String getAbreviacion() {
        return abreviacion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public int getAlmacen() {
        return almacen;
    }

    public void setAlmacen(int almacen) {
        this.almacen = almacen;
    }
}
