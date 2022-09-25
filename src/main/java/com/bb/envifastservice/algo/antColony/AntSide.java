package com.bb.envifastservice.algo.antColony;

import com.bb.envifastservice.algo.Aeropuerto;
import com.bb.envifastservice.algo.ArcoAeropuerto;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;

public class AntSide {
    public ArrayList<ArcoAeropuerto> caminos;//0: 1-2, 1:1-3, 2: 3-4, 3: 3-5, se debe cambiar
    public ArrayList<Integer> posiblesCaminosIndices;
    public ArrayList<Aeropuerto> nodos;//aeropuertos, se debe cambiar
    public ArrayList<Double> costos;//costos (duracion de vuelos), no se cambia
    public ArrayList<Double> visibilidad; //inversa de los costos, no se cambia
    public ArrayList<Double> cantidadFeromonasCamino; //feromonas, no se cambia
    public ArrayList<Double> probabilidadDeSerEscogido; //recta de probabilidad, no se cambia
    public ArrayList<Integer> numeroVecesDeSerEscogigo; //no se esta usando
    public static final double coeficienteEvaporacion = 0.01; //para actualizar feromonas, no se cambia
    public Aeropuerto nodoInicial; //cambiar tipo de dato
    public Aeropuerto nodoFinal; //cambiar tipo de dato
    public int tipoEnvio; //1: mismo continente, 2: diferentes contientes
    public double plazoMaximoEntrega;

    public AntSide(){
        caminos = new ArrayList<ArcoAeropuerto>();
        //caminos = new ArrayList<ArcoAeropuerto>();
        nodos = new ArrayList<Aeropuerto>();
        //nodos = new ArrayList <Aeropuerto>();
        costos = new ArrayList<Double>();
        visibilidad = new ArrayList<Double>();
        cantidadFeromonasCamino = new ArrayList<Double>();
        probabilidadDeSerEscogido = new ArrayList<Double>();
        numeroVecesDeSerEscogigo = new ArrayList<Integer>();
        posiblesCaminosIndices = new ArrayList<Integer>();
    }

    public AntSide(ArrayList<Aeropuerto> aeropuertos, ArrayList<ArcoAeropuerto> vuelos){
        caminos = new ArrayList<ArcoAeropuerto>(vuelos);
        nodos = new ArrayList<Aeropuerto>(aeropuertos);
        costos = new ArrayList<Double>(vuelos.size());
        visibilidad = new ArrayList<Double>(vuelos.size());
        cantidadFeromonasCamino = new ArrayList<Double>();
        for(int i=0;i<vuelos.size();i++)
            cantidadFeromonasCamino.add(0.1);
        probabilidadDeSerEscogido = new ArrayList<Double>(vuelos.size());
        numeroVecesDeSerEscogigo = new ArrayList<Integer>(vuelos.size());
        posiblesCaminosIndices = new ArrayList<Integer>();
    }

    public AntSide(int numeroAristas, int numeroNodos) {
        caminos = new ArrayList<ArcoAeropuerto>(numeroAristas);
        //caminos = new ArrayList<ArcoAeropuerto>(numeroAristas);
        nodos = new ArrayList<Aeropuerto>(numeroNodos);
        //nodos = new ArrayList <Aeropuerto>(numeroNodos);
        costos = new ArrayList<Double>(numeroAristas);
        visibilidad = new ArrayList<Double>(numeroAristas);
        cantidadFeromonasCamino = new ArrayList<Double>(numeroAristas);
        for(int i=0;i<numeroAristas;i++){
            cantidadFeromonasCamino.add(0.1);
        }
        probabilidadDeSerEscogido = new ArrayList<Double>(numeroAristas);
        numeroVecesDeSerEscogigo = new ArrayList<Integer>(numeroAristas);
        posiblesCaminosIndices = new ArrayList<Integer>();
    }

    public AntSide(int numeroAristas, int numeroNodos, Aeropuerto nodoIni, Aeropuerto nodoFin) {
        caminos = new ArrayList<ArcoAeropuerto>(numeroAristas);
        //caminos = new ArrayList<ArcoAeropuerto>(numeroAristas);
        nodos = new ArrayList<Aeropuerto>(numeroNodos);
        //nodos = new ArrayList <Aeropuerto>(numeroNodos);
        costos = new ArrayList<Double>(numeroAristas);
        visibilidad = new ArrayList<Double>(numeroAristas);
        cantidadFeromonasCamino = new ArrayList<Double>(numeroAristas);
        for(int i=0;i<numeroAristas;i++){
            cantidadFeromonasCamino.add(0.1);
        }
        probabilidadDeSerEscogido = new ArrayList<Double>(numeroAristas);
        numeroVecesDeSerEscogigo = new ArrayList<Integer>(numeroAristas);

        this.nodoInicial = nodoIni;
        this.nodoFinal = nodoFin;
        if(nodoInicial.getCiudad().getContinente().equals(nodoFinal.getCiudad().getContinente())) {
            this.tipoEnvio = 1;
            this.plazoMaximoEntrega= 1440.00; //1 dia en minutos
        }
        else {
            this.tipoEnvio = 2;
            this.plazoMaximoEntrega=2880.00; //2 dias en minutos
        }
        posiblesCaminosIndices = new ArrayList<Integer>();
    }

    public Aeropuerto getNodoInicial() {
        return nodoInicial;
    }

    public void setNodoInicial(Aeropuerto nodoInicial) {
        this.nodoInicial = nodoInicial;
    }

    public Aeropuerto getNodoFinal() {
        return nodoFinal;
    }

    public void setNodoFinal(Aeropuerto nodoFinal) {
        this.nodoFinal = nodoFinal;
    }


    public void setNodoInicialFinal(Aeropuerto nodoInicial, Aeropuerto nodoFinal) {
        this.nodoInicial = nodoInicial;
        this.nodoFinal = nodoFinal;
        if(nodoInicial.getCiudad().getContinente().equals(nodoFinal.getCiudad().getContinente())) {
            this.tipoEnvio = 1;
            this.plazoMaximoEntrega= 1440.00; //1 dia en minutos
        }
        else {
            this.tipoEnvio = 2;
            this.plazoMaximoEntrega=2880.00; //2 dias en minutos
        }
    }
    public int getTipoEnvio() {
        return tipoEnvio;
    }
    public double getPlazoMaximoEntrega() {
        return plazoMaximoEntrega;
    }
    public ArrayList<Integer> getPosiblesCaminosIndices() {
        return posiblesCaminosIndices;
    }

    public void setPosiblesCaminosIndices(ArrayList<Integer> posiblesCaminosIndices) {
        this.posiblesCaminosIndices = posiblesCaminosIndices;
    }

    public ArrayList<ArcoAeropuerto> getCaminos() {
        return caminos;
    }

    public void setCaminos(ArrayList<ArcoAeropuerto> caminos) {
        this.caminos = caminos;
    }

    public ArrayList<Aeropuerto> getNodos() {
        return nodos;
    }

    public void setNodos(ArrayList<Aeropuerto> nodos) {
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

    public void actualizarFeromonasEnElCamino(Ant hormiga1, Ant hormiga2) {
        int i=0;
        while (i < getCantidadFeromonasCamino().size()) {
            if(hormiga1.getCaminoIndices().contains(i) && hormiga2.getCaminoIndices().contains(i))
                this.cantidadFeromonasCamino.set(i,(1 - coeficienteEvaporacion) * getCantidadFeromonasCamino().get(i) +
                        hormiga1.getCntQ()/hormiga1.getCostoTotal() +
                        hormiga2.getCntQ()/hormiga2.getCostoTotal());
            else if (hormiga1.getCaminoIndices().contains(i)) {
                this.cantidadFeromonasCamino.set(i,(1 - coeficienteEvaporacion) * getCantidadFeromonasCamino().get(i) +
                        hormiga1.getCntQ()/hormiga1.getCostoTotal());
            }
            else if (hormiga2.getCaminoIndices().contains(i)) {
                this.cantidadFeromonasCamino.set(i,(1 - coeficienteEvaporacion) * getCantidadFeromonasCamino().get(i) +
                        hormiga2.getCntQ()/hormiga2.getCostoTotal());
            }
            else{
                this.cantidadFeromonasCamino.set(i,(1 - coeficienteEvaporacion) * getCantidadFeromonasCamino().get(i));
            }
            i++;
        }
    }
}
