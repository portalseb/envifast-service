package com.bb.envifastservice.algo;

public class Aeropuerto {
    private int id;
    private String codigo;
    private String ciudad;
    private String pais;
    private String abreviacion;

    private String continente;

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
}
