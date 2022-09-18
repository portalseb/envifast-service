package com.bb.envifastservice.algo;

import org.springframework.data.convert.Jsr310Converters;

import java.time.*;

public class ArcoAeropuerto {

    private Avion flight;
    private Aeropuerto aeropuerto1;
    private Aeropuerto aeropuerto2;
    private LocalTime horaPartida;
    private LocalTime horaLlegada;

    private ArcoAeropuerto(){

    }

    public ArcoAeropuerto(String nombreVuelo, Aeropuerto aeropuerto1, Aeropuerto aeropuerto2, String partida, String llegada){
        this.flight = new Avion(nombreVuelo);
        this.aeropuerto1 = aeropuerto1;
        this.aeropuerto2 = aeropuerto2;
        this.horaPartida = LocalTime.parse(partida);
        this.horaLlegada = LocalTime.parse(llegada);
    }

    public void setFlight(Avion flight) {
        this.flight = flight;
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

    public Avion getFlight() {
        return flight;
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

    public Integer obtenerCapacidadDeAvionYAeropuertoDestino(TablaTiempos tablaTiempos){
        Integer flightIdx = tablaTiempos.obtenerIndiceAvionPorNombre(this.flight.getNombre());
        Integer capacidadUsada = tablaTiempos.aviones.get(flightIdx).getCapacidad();
        Integer airportIdx = tablaTiempos.obtenerIndiceAeropuertoPorID(this.aeropuerto2.getId());
        capacidadUsada += tablaTiempos.aeropuertos.get(airportIdx).getCapacidad();
        return capacidadUsada;
    }

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

        return Duration.between(zdt_takeoff.toLocalDate(), zdt_arrival.toLocalDate());
    }

    public String toString(){
        return "Avion: " + getFlight().getNombre() + " desde " + getAeropuerto1().getCiudad().getNombre() +
                " hacia " + getAeropuerto2().getCiudad().getNombre() + " en "
               +  obtenerDuracionVuelo().toString();
     }

}
