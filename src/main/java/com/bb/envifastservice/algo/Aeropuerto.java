package com.bb.envifastservice.algo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.TimeZone;

public class Aeropuerto implements Comparable<Aeropuerto> {

    public static final Integer CAPACIDAD_AEROPUERTO = 500;
    private Integer id;
    private String codigo;
    private Ciudad ciudad;
    private String nombre;
    private TimeZone timeZone;
    private ArrayList<Paquete> deposito = new ArrayList<>();
    private Integer capacidad;

    private Aeropuerto parent;

    private Integer f;
    private Integer h;
    private Integer g;

    public Aeropuerto(){
        ciudad = new Ciudad();
//        this.timeZone = TimeZone.getTimeZone(timeZone);
//        this.capacidad = CAPACIDAD_AEROPUERTO;
    }
    public Aeropuerto(Integer id, String codigo, String nombreCiudad, String ciudadAbreviada,
                      String pais, String nombre, String timeZone, String continente){
        this.id = id;
        this.codigo = codigo;
        this.ciudad = new Ciudad(nombreCiudad, ciudadAbreviada, pais, continente);
        this.nombre = nombre;
        this.timeZone = TimeZone.getTimeZone(timeZone);
        this.capacidad = CAPACIDAD_AEROPUERTO;
    }

    public void setNodeData(Aeropuerto currentNode, int costo){
        int gCost = currentNode.getG() + costo;
        setParent(currentNode);
        setG(gCost);
        calculateFinalCost();
    }

    public void calculateFinalCost(){
        int finalCost = getG() + getH(); // tenemos que ver la manera de calcular la heuristica.
        // es mas ni creo que la calculemos en esta clase, solo la setearemos en otra
        setF(finalCost);
    }

    public boolean checkBetterPath(Aeropuerto currentNode, int cost) {
        int gCost = currentNode.getG() + cost;
        if (gCost < getG()) {
            setNodeData(currentNode, cost);
            return true;
        }
        return false;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCodigo(String codigo){
        this.codigo = codigo;
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

    public String getCodigo(){
        return codigo;
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
    public String toString() {
        return "Aeropuerto{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", ciudad=" + ciudad.getNombre() +
                ", pais='" + ciudad.getPais() + '\'' +
                ", continente'" + ciudad.getContinente() + '\'' +
                ", abreviacion=" + ciudad.getAbreviacion() + '\'' +
                ", capacidad=" + capacidad +
                '}';
    }

    @Override
    public int compareTo(Aeropuerto o) {
        if(o.getUso() < this.getUso()){
            return 1; // o > este mismo
        }
        return 0;
    }

    public Aeropuerto getParent() {
        return parent;
    }

    public void setParent(Aeropuerto parent) {
        this.parent = parent;
    }

    // para las funcioens f = g + h

    public Integer getF() {
        return f;
    }

    public Integer getH() {
        return h;
    }

    public Integer getG() {
        return g;
    }

    public void setF(Integer f) {
        this.f = f;
    }

    public void setH(Integer h) {
        this.h = h;
    }

    public void setG(Integer g) {
        this.g = g;
    }
}
