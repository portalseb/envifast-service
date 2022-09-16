package com.bb.envifastservice.algo;

public class Ciudad {
    private String nombre;
    private String abreviacion;

    private String pais;
    private String continente;


    public Ciudad(String nombre, String abreviacion, String pais) {
        this.nombre = nombre;
        this.abreviacion = abreviacion;
        this.pais = pais;
//        this.continente = continente;
    }

    public String getNombre() {
        return nombre;
    }

    public String getAbreviacion() {
        return abreviacion;
    }

    public String getContinente() {
        return continente;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setAbreviacion(String abreviacion) {
        this.abreviacion = abreviacion;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    public String paraImprimir() {
        return this.nombre + " - " + this.pais + " - " + this.continente;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
