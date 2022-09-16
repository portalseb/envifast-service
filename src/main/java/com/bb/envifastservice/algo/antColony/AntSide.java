package com.bb.envifastservice.algo.antColony;

import com.bb.envifastservice.algo.Aeropuerto;

import java.util.ArrayList;

public class AntSide {
    public ArrayList<String> caminos;
    public ArrayList<Integer> nodos;
    public ArrayList<Double> costos;
    public ArrayList<Double> visibilidad;
    public ArrayList<Double> cantidadFeromonasCamino;
    public ArrayList<Double> probabilidadDeSerEscogido;
    public ArrayList<Integer> numeroVecesDeSerEscogigo;

    public static final double coeficienteEvaporacion = 986;


    public AntSide(int numeroAristas, int numeroNodos) {
        caminos = new ArrayList<String>(numeroAristas);
        nodos = new ArrayList<Integer>(numeroNodos);
        costos = new ArrayList<Double>(numeroAristas);
        visibilidad = new ArrayList<Double>(numeroAristas);
        cantidadFeromonasCamino = new ArrayList<Double>(numeroAristas);
        for(Double ferom: cantidadFeromonasCamino){
            ferom = 0.1;
        }
        probabilidadDeSerEscogido = new ArrayList<Double>(numeroAristas);
        numeroVecesDeSerEscogigo = new ArrayList<Integer>(numeroAristas);
    }

    public ArrayList<String> getCaminos() {
        return caminos;
    }

    public void setCaminos(ArrayList<String> caminos) {
        this.caminos = caminos;
    }

    public ArrayList<Integer> getNodos() {
        return nodos;
    }

    public void setNodos(ArrayList<Integer> nodos) {
        this.nodos = nodos;
    }

    public ArrayList<Double> getCostos() {
        return costos;
    }

    public void setCostos(ArrayList<Double> costos) {
        this.costos = costos;
    }

    public ArrayList<Double> getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(ArrayList<Double> visibilidad) {
        this.visibilidad = visibilidad;
    }

    public ArrayList<Double> getCantidadFeromonasCamino() {
        return cantidadFeromonasCamino;
    }

    public void setCantidadFeromonasCamino(ArrayList<Double> cantidadFeromonasCamino) {
        this.cantidadFeromonasCamino = cantidadFeromonasCamino;
    }

    public ArrayList<Double> getProbabilidadDeSerEscogido() {
        return probabilidadDeSerEscogido;
    }

    public void setProbabilidadDeSerEscogido(ArrayList<Double> probabilidadDeSerEscogido) {
        this.probabilidadDeSerEscogido = probabilidadDeSerEscogido;
    }

    public ArrayList<Integer> getNumeroVecesDeSerEscogigo() {
        return numeroVecesDeSerEscogigo;
    }

    public void setNumeroVecesDeSerEscogigo(ArrayList<Integer> numeroVecesDeSerEscogigo) {
        this.numeroVecesDeSerEscogigo = numeroVecesDeSerEscogigo;
    }

    public void actualizarFeromonasEnElCamino(ArrayList<Double> recorridoHormiga) {
        int i=0;
        while (i < getCantidadFeromonasCamino().size()) {
            this.cantidadFeromonasCamino.add((1 - coeficienteEvaporacion) * getCantidadFeromonasCamino().get(i) + recorridoHormiga.get(i));
            i++;
        }
    }
}
