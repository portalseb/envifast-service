package com.bb.envifastservice.algo;

public class PlanVuelo {
    private Aeropuerto origen;
    private Aeropuerto destino;
    private String horaInicio;
    private String horaFin;

    private int almacen;
    private int costo;

    public PlanVuelo(){
        this.origen = new Aeropuerto();
        this.destino = new Aeropuerto();
        this.costo = 0;
    }

    public int calcularCosto(){
        // obtenemos los arreglos de las horas
        String[] inicio = this.horaInicio.split(":");
        String[] fin = this.horaFin.split(":");

        int departure = Integer.parseInt(inicio[0])*60 + Integer.parseInt(inicio[1]); // hora salida
        int arrival = Integer.parseInt(fin[0])*60 + Integer.parseInt(fin[1]); // hora llegada

//        System.out.println(departure + " -" + arrival);
        if(departure > arrival){// si la hora de salida es mayor a la hora de llegada
            this.costo = arrival + 24 * 60 - departure;
            return arrival + 24 * 60 - departure;
        }else{
            this.costo = arrival - departure;
            return arrival - departure;
        }

    }



    public void setOrigen(Aeropuerto origen) {
        this.origen = origen;
    }

    public void setDestino(Aeropuerto destino) {
        this.destino = destino;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public Aeropuerto getOrigen() {
        return origen;
    }

    public Aeropuerto getDestino() {
        return destino;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getAlmacen() {
        return almacen;
    }

    public void setAlmacen(int almacen) {
        this.almacen = almacen;
    }

    @Override
    public String toString() {
        return "PlanVuelo{" +
                "origen='" + origen.getCodigo() + "' - " + origen.getCiudad().getNombre() +
                ", destino='" + destino.getCodigo() + "' - " + destino.getCiudad().getNombre() +
                ", horaInicio='" + horaInicio + '\'' +
                ", horaFin='" + horaFin + '\'' +
                ", almacen=" + almacen +
                ", costo=" + costo +
                '}';
    }
}
