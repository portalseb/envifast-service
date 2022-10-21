package com.bb.envifastservice.algo;

import org.springframework.data.convert.Jsr310Converters;

import java.time.*;
import java.util.ArrayList;
import java.util.Date;

public class ArcoAeropuerto {

    private Integer id;
    private Aeropuerto aeropuerto1;
    private Aeropuerto aeropuerto2;
    private LocalTime horaPartida;
    private LocalTime horaLlegada;


    private LocalDate diaPartida;
    private LocalDate diaLLegada;

    private ArrayList<Paquete> cargo;

    private Integer capacidadDisponible;

    private Integer capacidadMaxima;

    private int duracion;


    public ArcoAeropuerto(){
        this.cargo = new ArrayList<Paquete>();
    }

    public ArcoAeropuerto(String nombreVuelo, Aeropuerto aeropuerto1, Aeropuerto aeropuerto2, String partida, String llegada){
        //this.flight = new Avion(nombreVuelo);
        this.aeropuerto1 = aeropuerto1;
        this.aeropuerto2 = aeropuerto2;
        this.horaPartida = LocalTime.parse(partida);
        this.horaLlegada = LocalTime.parse(llegada);
        if(aeropuerto1.getCiudad().getContinente().equals(aeropuerto2.getCiudad().getContinente())) {
            if(aeropuerto1.getCiudad().getContinente().equals("Europa")){
                //this.flight = new Avion(nombreVuelo, 250);
                this.capacidadMaxima = 250;
                this.capacidadDisponible = 250;
            }
            else {
                //this.flight = new Avion(nombreVuelo, 300);
                this.capacidadMaxima = 250;
                this.capacidadDisponible = 300;
            }
        }
        else {
            //this.flight = new Avion(nombreVuelo, 350);
            this.capacidadMaxima = 250;
            this.capacidadDisponible = 350;
        }
        this.cargo = new ArrayList<Paquete>();
    }

    public ArcoAeropuerto(Integer idArco, String nombreVuelo, Aeropuerto aeropuerto1, Aeropuerto aeropuerto2, String partida, String llegada){
        //this.flight = new Avion(nombreVuelo);
        this.id = idArco;
        this.aeropuerto1 = aeropuerto1;
        this.aeropuerto2 = aeropuerto2;
        this.horaPartida = LocalTime.parse(partida);
        this.horaLlegada = LocalTime.parse(llegada);
        if(aeropuerto1.getCiudad().getContinente().equals(aeropuerto2.getCiudad().getContinente())) {
            if(aeropuerto1.getCiudad().getContinente().equals("Europa")){
                //this.flight = new Avion(nombreVuelo, 250);
                this.capacidadMaxima = 250;
                this.capacidadDisponible = 250;
            }
            else {
                //this.flight = new Avion(nombreVuelo, 300);
                this.capacidadMaxima = 250;
                this.capacidadDisponible = 300;
            }
        }
        else {
            //this.flight = new Avion(nombreVuelo, 350);
            this.capacidadMaxima = 250;
            this.capacidadDisponible = 350;
        }
        this.cargo = new ArrayList<Paquete>();
    }




    public ArcoAeropuerto(String nombreVuelo, Aeropuerto aeropuerto1, Aeropuerto aeropuerto2, String partida, String llegada, String dPartida, String dLlegada){
        //this.flight = new Avion(nombreVuelo);
        this.aeropuerto1 = aeropuerto1;
        this.aeropuerto2 = aeropuerto2;
        this.horaPartida = LocalTime.parse(partida);
        this.horaLlegada = LocalTime.parse(llegada);
        this.diaPartida = LocalDate.parse(dPartida);
        this.diaLLegada = LocalDate.parse(dLlegada);

        if(aeropuerto1.getCiudad().getContinente().equals(aeropuerto2.getCiudad().getContinente())) {
            if(aeropuerto1.getCiudad().getContinente().equals("Europa")){
                //this.flight = new Avion(nombreVuelo, 250);
                this.capacidadMaxima = 250;
                this.capacidadDisponible = 250;
            }
            else {
                //this.flight = new Avion(nombreVuelo, 300);
                this.capacidadMaxima = 300;
                this.capacidadDisponible = 300;
            }
        }
        else {
            //this.flight = new Avion(nombreVuelo, 350);
            this.capacidadMaxima = 350;
            this.capacidadDisponible = 350;
        }
        this.cargo = new ArrayList<Paquete>();
    }


    public ArrayList<Paquete> getCargo() {
        return cargo;
    }

    public void setCargo(ArrayList<Paquete> cargo) {
        this.cargo = cargo;
    }

    public Integer getCapacidadDisponible() {
        return capacidadDisponible;
    }

    public void setCapacidadDisponible(Integer capacidadDisponible) {
        this.capacidadDisponible = capacidadDisponible;
    }


    public void agregarPaquete(Paquete pac) {
        this.cargo.add(pac);
        this.capacidadDisponible=this.capacidadDisponible-1;
        //pac.actualizarEstado(null, this.flight); //Esto se deberia hacer en tiempo real
    }


    public void removerPaquete(Paquete pac) {
        this.cargo.remove(pac);
        this.capacidadDisponible=this.capacidadDisponible+1;
    }




    public void setAeropuerto1(Aeropuerto aeropuerto1) {
        this.aeropuerto1 = aeropuerto1;
    }

    public void setAeropuerto2(Aeropuerto aeropuerto2) {
        this.aeropuerto2 = aeropuerto2;
    }

    public void setHoraPartida(LocalTime horaPartida) {
        this.horaPartida = horaPartida;
    }

    public void setHoraLlegada(LocalTime horaLlegada) {
        this.horaLlegada = horaLlegada;
    }


    public LocalDate getDiaPartida() {
        return diaPartida;
    }

    public void setDiaPartida(LocalDate diaPartida) {
        this.diaPartida = diaPartida;
    }

    public LocalDate getDiaLLegada() {
        return diaLLegada;
    }

    public void setDiaLLegada(LocalDate diaLLegada) {
        this.diaLLegada = diaLLegada;
    }



    public Aeropuerto getAeropuerto1() {
        return aeropuerto1;
    }

    public Aeropuerto getAeropuerto2() {
        return aeropuerto2;
    }

    public LocalTime getHoraPartida() {
        return horaPartida;
    }

    public LocalTime getHoraLlegada() {
        return horaLlegada;
    }

//    public Integer obtenerCapacidadDeAvionYAeropuertoDestino(TablaTiempos tablaTiempos){
//        Integer flightIdx = tablaTiempos.obtenerIndiceAvionPorNombre(this.flight.getNombre());
//        Integer capacidadUsada = tablaTiempos.aviones.get(flightIdx).getCapacidad();
//        Integer airportIdx = tablaTiempos.obtenerIndiceAeropuertoPorID(this.aeropuerto2.getId());
//        capacidadUsada += tablaTiempos.aeropuertos.get(airportIdx).getCapacidad();
//        return capacidadUsada;
//    }

    public Duration obtenerDuracionVuelo(){
//        LocalDateTime ldt_takeoff = this.horaPartida.atDate(LocalDate.now());
//        ZonedDateTime zdt_takeoff = ldt_takeoff.atZone(this.aeropuerto1.getTimeZone().toZoneId());
//
//        LocalDateTime ldt_arrival = this.horaLlegada.atDate(LocalDate.now());
//        ZonedDateTime zdt_arrival = ldt_arrival.atZone(this.aeropuerto2.getTimeZone().toZoneId());
        LocalDateTime ldt_takeoff = this.horaPartida.atDate(LocalDate.now());
        ZonedDateTime zdt_takeoff = ldt_takeoff.atZone(this.aeropuerto1.getTimeZone().toZoneId());

        LocalDateTime ldt_arrival = this.horaLlegada.atDate(LocalDate.now());
        ZonedDateTime zdt_arrival = ldt_arrival.atZone(this.aeropuerto2.getTimeZone().toZoneId());
        if(ldt_takeoff.compareTo(ldt_arrival) > 0){
            zdt_arrival = zdt_arrival.plusDays(1);
        }

        return Duration.between(zdt_takeoff.toLocalDateTime(), zdt_arrival.toLocalDateTime());
    }

    public String toString(){
//        return "Avion desde " + getAeropuerto1().getCiudad().getNombre() +
//                " hacia " + getAeropuerto2().getCiudad().getNombre() + " en "
//               +  obtenerDuracionVuelo().toString();

        return getDiaPartida() + "-"+ getHoraPartida() + " / " + getAeropuerto1().getCiudad().getNombre() + " --> " +getDiaLLegada() + "-" + getHoraLlegada()+ " / " +getAeropuerto2().getCiudad().getNombre() + "\n";
     }

    public Integer getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setCapacidadMaxima(Integer capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
}
