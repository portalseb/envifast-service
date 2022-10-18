package com.bb.envifastservice.algo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;
import java.util.TimeZone;

public class Aeropuerto {//implements Comparable<Aeropuerto> {

    public static final Integer CAPACIDAD_AEROPUERTO = 500;
    private Integer id;
    private String codigo;
    private Ciudad ciudad;
    private String nombre;
    private TimeZone timeZone;
    //private ArrayList<Paquete> deposito;
    private Integer capacidad;

    private ArrayList<CapacidadAeropuerto> capacidadDisponible;

    private double posX;

    private double posY;


    private Integer f;
    private Integer h;
    private Integer g;

    public Aeropuerto(){
        ciudad = new Ciudad();
        this.capacidadDisponible = new ArrayList<CapacidadAeropuerto>();
//        this.timeZone = TimeZone.getTimeZone(timeZone);
//        this.capacidad = CAPACIDAD_AEROPUERTO;
    }

    public Aeropuerto(Aeropuerto aeropuerto){
        this.id = aeropuerto.getId();
        this.codigo = aeropuerto.getCodigo();
        this.ciudad = new Ciudad(aeropuerto.getCiudad().getNombre(), aeropuerto.getCiudad().getAbreviacion(),
                                 aeropuerto.getCiudad().getPais(), aeropuerto.getCiudad().getContinente());
        this.nombre = aeropuerto.getNombre();
        this.timeZone = aeropuerto.getTimeZone();
        this.capacidad = aeropuerto.getCapacidad();
        this.capacidadDisponible = aeropuerto.getCapacidadDisponible();
    }

    public Aeropuerto(Integer id, String codigo, String nombreCiudad, String ciudadAbreviada,
                      String pais, String nombre, String timeZone, String continente){
        this.id = id;
        this.codigo = codigo;
        this.ciudad = new Ciudad(nombreCiudad, ciudadAbreviada, pais, continente);
        this.nombre = nombre;
        this.g = 0;
        this.h = 0;
        this.timeZone = TimeZone.getTimeZone(timeZone);

        if(continente.equals("Europa")) {
            this.capacidad = 900; //almacenes de EU
        }
        else {
            this.capacidad = 850; //almacenes de SA
        }

        this.capacidadDisponible = new ArrayList<CapacidadAeropuerto>();

        //this.capacidad = CAPACIDAD_AEROPUERTO;
    }

    public ArrayList<CapacidadAeropuerto> getCapacidadDisponible() {
        return capacidadDisponible;
    }

    public void setCapacidadDisponible(ArrayList<CapacidadAeropuerto> capacidadDisponible) {
        this.capacidadDisponible = capacidadDisponible;
    }


    public void calculateFinalCost(){
        int finalCost = getG() + getH();
        setF(finalCost);
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

    public void setTimeZone(String timeZone) {
        this.timeZone = TimeZone.getTimeZone(timeZone);
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


    public Integer getCapacidad() {
        return capacidad;
    }

    public String paraImprimir(){
        return "Este es el aeropuerto " + this.nombre + ", estoy en " + this.timeZone.getID() + " en " +
                this.ciudad.paraImprimir();
    }



//    public void agregarPaquete(Paquete pac){
//        this.deposito.add(pac);
//        //pac.actualizarEstado(this, null);
//        setCapacidad(this.capacidad + 1);// aumentamos la capacidad
//    }

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

//    @Override
//    public int compareTo(Aeropuerto o) {
//        if(o.getUso() < this.getUso()){
//            return 1; // o > este mismo
//        }
//        return 0;
//    }

    @Override
    public boolean equals(Object obj) {
        // me parece que solo hay que calcular si son iguales por el nombre, pero bueno eso lo veo despues
        Aeropuerto otroAeropuerto = (Aeropuerto) obj;

        return otroAeropuerto.getCodigo() == this.getCodigo();
    }


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

    public int getCapacidadIndex(int hora, int minuto, int dia, int mes, int anio){
        int i=0;
        for(i=0;i<this.capacidadDisponible.size();i++){
            if(capacidadDisponible.get(i).getFechaHora().getHora().getMinute() == minuto
            && capacidadDisponible.get(i).getFechaHora().getHora().getHour() == hora
            && capacidadDisponible.get(i).getFechaHora().getDia().getDayOfMonth()==dia
            && capacidadDisponible.get(i).getFechaHora().getDia().getMonth().getValue()==mes
            && capacidadDisponible.get(i).getFechaHora().getDia().getYear()==anio)
                return i;
        }
        return -1;
    }


    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }
}
