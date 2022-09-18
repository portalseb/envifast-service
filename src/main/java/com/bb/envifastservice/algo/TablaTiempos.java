package com.bb.envifastservice.algo;

import com.bb.envifastservice.algo.Aeropuerto;
import com.bb.envifastservice.algo.ArcoAeropuerto;
import com.bb.envifastservice.algo.Avion;
import com.bb.envifastservice.algo.Paquete;

import java.time.LocalTime;
import java.util.ArrayList;

// Esta es la clase del grafo
public class TablaTiempos {
    ArrayList<ArcoAeropuerto> arcos;
    ArrayList<Aeropuerto> aeropuertos;
    ArrayList<Avion> aviones;

    public TablaTiempos(ArrayList<ArcoAeropuerto> arcos, ArrayList<Aeropuerto> aeropuertos, ArrayList<Avion> aviones){
        this.arcos = arcos;
        this.aeropuertos = aeropuertos;
        this.aviones = aviones;
    }

    public Integer obtenerCapacidadDeAvionesYAeropuertos(){
        Integer uso = 0;
        // uso de los aeropuertos
        for(Aeropuerto air:this.aeropuertos){
            uso += air.getUso();
        }

        // cargo de los aviones
        for(Avion avion : this.aviones) {
            uso += avion.getUso();
        }
        return uso;
    }

    public void agregarPaquete(Paquete pack){
        // Agregar paquete al primer aeropuerto
        Aeropuerto air = pack.getRuta().get(0).getAeropuerto1();
        Integer indice = this.aeropuertos.indexOf(air);
        pack.actualizarEstado(air, null);
        this.aeropuertos.get(indice).agregarPaquete(pack);
    }

    public Integer obtenerIndiceAeropuertoPorID(Integer id){
        for(Integer idx = 0; idx < this.aeropuertos.size(); idx++){
            if(aeropuertos.get(idx).getId() == id){
                return idx;
            }
        }
        return -1;
    }

    public Integer obtenerIndiceAvionPorNombre(String nombreAvion) {
        for (Integer idx = 0; idx < aviones.size(); idx++) {
            if (aviones.get(idx).getNombre() == nombreAvion) {
                return idx;
            }
        }
        return -1;
    }

    public Aeropuerto obtenerAeropuertoPorNombre(String name) {
        for (Aeropuerto air: this.aeropuertos) {
            if (air.getNombre().equals(name)) {
                return air;
            }
        }
        return null;
    }

    public ArrayList<Aeropuerto> obtenerArcosDespegandoDe(LocalTime t){
        ArrayList<Aeropuerto> salidas = new ArrayList<>();
        for(ArcoAeropuerto e: this.arcos){
            if(e.getHoraPartida().equals(t)){
                salidas.add(e.getAeropuerto1());
            }
        }
        return salidas;
    }

    // me revienta la chimba este metodo
    public void actualizarTodosLosPaquetesQueDespeganDe(ArrayList<Aeropuerto> aeropuertos, LocalTime t){
        ArrayList<Paquete> paquetes = new ArrayList<>();
        for(Aeropuerto a: this.aeropuertos){
            Integer idx = this.aeropuertos.indexOf(a);
            if(idx != -1){
                ArrayList<Paquete> aux = this.aeropuertos.get(idx).getDeposito();
                for(Paquete p: aux){
                    Integer pkidx = aux.indexOf(p);
                    Avion f = p.obtenerSiguienteAvion(a);
                    for(ArcoAeropuerto e: p.getRuta()){
                        if(e.getFlight() == f && e.getHoraPartida().equals(t)){
                            p.actualizarEstado(null, f);
                            Integer flightIdx = this.aviones.indexOf(f);
                            this.aviones.get(flightIdx).agregarPaquete(p);
                            aux.remove(pkidx);// lo sacamos
                        }
                    }
                }
                this.aeropuertos.get(idx).setDeposito(aux);
                this.aeropuertos.get(idx).setCapacidad(aux.size());
            }
        }
    }

    public void actualizarTodosLosPaquetesAterrizanEn(ArrayList<Avion> aviones, LocalTime t){
        ArrayList<Paquete> paquetes = new ArrayList<>();
        aviones.stream().map(f -> f.getCargo().stream().map(p -> paquetes.add(p)));
        for(Avion f: this.aviones){
            Integer idx = this.aviones.indexOf(f);
            if(idx != -1){
                this.aviones.get(idx).setCargo(new ArrayList<>());
            }
        }

        for(Paquete pkg : paquetes){
            Integer idx = this.aeropuertos.indexOf(pkg.obtenerSiguienteAeropuerto(pkg.getVueloActual()));
            if(idx != -1){
                pkg.actualizarEstado(this.aeropuertos.get(idx), null);
                this.aeropuertos.get(idx).agregarPaquete(pkg);
            }
        }
    }

    public void imprimirArcos(){
        this.arcos.forEach(ec -> System.out.println(ec));
    }


    public ArrayList<ArcoAeropuerto> getArcos(){
        return this.arcos;
    }

    public void calcularHeuristica(){
        // una vez que ya tenemos todos los arcos leidos, entonces podemos calcular el menor
        // costo entre cada par de nodos.

    }
}


















