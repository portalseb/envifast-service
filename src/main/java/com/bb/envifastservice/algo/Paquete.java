package com.bb.envifastservice.algo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Paquete {

    private String id;
    private LocalDate dia;
    private LocalTime hora;
    private ArrayList<ArcoAeropuerto> ruta;
    private Aeropuerto aeropuertoActual;
    private Avion vueloActual;
    private Aeropuerto destino;
    private Aeropuerto origen;

    private int estado; // volando, enviandose, etc

    public Paquete() {
        this.ruta = new ArrayList<ArcoAeropuerto>();
    }

    public Paquete(String id, LocalDate dia, LocalTime hora, Aeropuerto destino, Aeropuerto origen) {
        this.id = id;
        this.dia = dia;
        this.hora = hora;
        this.destino = destino;
        this.origen = origen;
        this.ruta = new ArrayList<ArcoAeropuerto>();
    }



    public void setId(String id) {
        this.id = id;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public void setRuta(ArrayList<ArcoAeropuerto> ruta) {
        this.ruta = ruta;
        this.aeropuertoActual = ruta.get(0).getAeropuerto1();
        this.vueloActual = null;
    }

//    public Avion obtenerSiguienteAvion(Aeropuerto aeropuerto){
//        for(ArcoAeropuerto ec :this.ruta){
//            if(ec.getAeropuerto1().getId() == aeropuerto.getId()){
//                return ec.getFlight();
//            }
//        }
//        return null;
//    }
//
//    public Aeropuerto obtenerSiguienteAeropuerto(Avion avion){
//        for(ArcoAeropuerto ec: ruta){
//            if(ec.getFlight().getNombre() == avion.getNombre()){
//                return ec.getAeropuerto2();
//            }
//        }
//        return null;
//    }

    public void actualizarEstado(Aeropuerto aeropuerto, Avion avion){
        if(aeropuerto != null){
            setAeropuertoActual(aeropuerto);
            setVueloActual(null);
        }else if(avion != null){
            setAeropuertoActual(null);
            setVueloActual(avion);
        }else{
            throw new Error("No se coloco ni avion ni aeropuerto!");
        }
    }
    public void setAeropuertoActual(Aeropuerto aeropuertoActual) {
        this.aeropuertoActual = aeropuertoActual;
    }

    public void setVueloActual(Avion vueloActual) {
        this.vueloActual = vueloActual;
    }

    public String getId() {
        return id;
    }

    public LocalDate getDia() {
        return dia;
    }

    public LocalTime getHora() {
        return hora;
    }

    public ArrayList<ArcoAeropuerto> getRuta() {
        return ruta;
    }

    public Aeropuerto getAeropuertoActual() {
        return aeropuertoActual;
    }

    public Avion getVueloActual() {
        return vueloActual;
    }

    public Aeropuerto getDestino() {
        return destino;
    }

    public Aeropuerto getOrigen() {
        return origen;
    }

    public void setDestino(Aeropuerto destino) {
        this.destino = destino;
    }

    public void setOrigen(Aeropuerto origen) {
        this.origen = origen;
    }
}
