package com.bb.envifastservice.algo.antColony;

import com.bb.envifastservice.algo.Aeropuerto;
import com.bb.envifastservice.algo.ArcoAeropuerto;

import java.util.ArrayList;

public class AntSide {
    public ArrayList<String> caminos;//0: 1-2, 1:1-3, 2: 3-4, 3: 3-5, se debe cambiar
    //public ArrayList <ArcoAeropuerto> caminos;
    public ArrayList<Integer> nodos;//aeropuertos, se debe cambiar
    // public ArrayList <Aeropuerto> nodos;
    public ArrayList<Double> costos;//costos (duracion de vuelos), no se cambia
    public ArrayList<Double> visibilidad; //inversa de los costos, no se cambia
    public ArrayList<Double> cantidadFeromonasCamino; //feromonas, no se cambia
    public ArrayList<Double> probabilidadDeSerEscogido; //recta de probabilidad, no se cambia
    public ArrayList<Integer> numeroVecesDeSerEscogigo; //no se esta usando
    public static final double coeficienteEvaporacion = 0.01; //para actualizar feromonas, no se cambia
    public Integer nodoInicial; //cambiar tipo de dato
    //public Aeropuerto nodoInicial;
    public Integer nodoFinal; //cambiar tipo de dato
    //public Aeropuerto nodoFinal;

    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /**Cambiar tipos de dato de caminos y nodos -> arcoAeropuerto y aeropuertos*/
    public AntSide(){
        caminos = new ArrayList<String>();
        //caminos = new ArrayList<ArcoAeropuerto>();
        nodos = new ArrayList<Integer>();
        //nodos = new ArrayList <Aeropuerto>();
        costos = new ArrayList<Double>();
        visibilidad = new ArrayList<Double>();
        cantidadFeromonasCamino = new ArrayList<Double>();
        probabilidadDeSerEscogido = new ArrayList<Double>();
        numeroVecesDeSerEscogigo = new ArrayList<Integer>();
    }

    public AntSide(int numeroAristas, int numeroNodos) {
        caminos = new ArrayList<String>(numeroAristas);
        //caminos = new ArrayList<ArcoAeropuerto>(numeroAristas);
        nodos = new ArrayList<Integer>(numeroNodos);
        //nodos = new ArrayList <Aeropuerto>(numeroNodos);
        costos = new ArrayList<Double>(numeroAristas);
        visibilidad = new ArrayList<Double>(numeroAristas);
        cantidadFeromonasCamino = new ArrayList<Double>(numeroAristas);
        for(int i=0;i<numeroAristas;i++){
            cantidadFeromonasCamino.add(0.1);
        }
        probabilidadDeSerEscogido = new ArrayList<Double>(numeroAristas);
        numeroVecesDeSerEscogigo = new ArrayList<Integer>(numeroAristas);
    }
    /******************************************************************************************************************/
    /******************************************************************************************************************/



    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /**Se debería modificar los constructores para recibir los ArrayList de aeropuertos y arcoAeropuertos*/
    /**Cambiar tipos de dato*/
    public AntSide(int numeroAristas, int numeroNodos, int nodoIni, int nodoFin) {
        caminos = new ArrayList<String>(numeroAristas);
        //caminos = new ArrayList<ArcoAeropuerto>(numeroAristas);
        nodos = new ArrayList<Integer>(numeroNodos);
        //nodos = new ArrayList <Aeropuerto>(numeroNodos);
        costos = new ArrayList<Double>(numeroAristas);
        visibilidad = new ArrayList<Double>(numeroAristas);
        cantidadFeromonasCamino = new ArrayList<Double>(numeroAristas);
        for(int i=0;i<numeroAristas;i++){
            cantidadFeromonasCamino.add(0.1);
        }
        probabilidadDeSerEscogido = new ArrayList<Double>(numeroAristas);
        numeroVecesDeSerEscogigo = new ArrayList<Integer>(numeroAristas);
        nodoInicial = nodoIni;
        nodoFinal = nodoFin;
    }
    /******************************************************************************************************************/
    /*****************************************************************************************************************/


    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /**Estos getter y setter se deben cambiar de tipo de datos*/
    public Integer getNodoInicial() {
        return nodoInicial;
    }
    //public Aeropuerto getNodoInicial() {
    //        return nodoInicial;
    //    }
    public void setNodoInicial(Integer nodoInicial) {
        this.nodoInicial = nodoInicial;
    }
    // public void setNodoInicial(Aeropuerto nodoInicial) {
    //        this.nodoInicial = nodoInicial;
    //    }
    public Integer getNodoFinal() {
        return nodoFinal;
    }
    //public Aeropuerto getNodoFinal() {
    //        return nodoFinal;
    //    }
    public void setNodoFinal(Integer nodoFinal) {
        this.nodoFinal = nodoFinal;
    }
    //public void setNodoFinal(Aeropuerto nodoFinal) {
    //        this.nodoFinal = nodoFinal;
    //    }

    public ArrayList<String> getCaminos() {
        return caminos;
    }
    //public ArrayList<ArcoAeropuerto> getCaminos() {
    //        return caminos;
    //    }
    public void setCaminos(ArrayList<String> caminos) {
        this.caminos = caminos;
    }
    //public void setCaminos(ArrayList<ArcoAeropuerto> caminos) {
    //        this.caminos = caminos;
    //    }

    public ArrayList<Integer> getNodos() {
        return nodos;
    }
    //public ArrayList<Aeropuerto> getNodos() {
    //        return nodos;
    //    }

    public void setNodos(ArrayList<Integer> nodos) {
        this.nodos = nodos;
    }
    //public void setNodos(ArrayList<Aeropuerto> nodos) {
    //        this.nodos = nodos;
    //    }
    /******************************************************************************************************************/
    /******************************************************************************************************************/


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
                        hormiga1.getCntQ()/hormiga1.getCaminoCostos().get(hormiga1.getCaminoIndices().indexOf(i)) +
                        hormiga2.getCntQ()/hormiga2.getCaminoCostos().get(hormiga2.getCaminoIndices().indexOf(i)));
            else if (hormiga1.getCaminoIndices().contains(i)) {
                this.cantidadFeromonasCamino.set(i,(1 - coeficienteEvaporacion) * getCantidadFeromonasCamino().get(i) +
                        hormiga1.getCntQ()/hormiga1.getCaminoCostos().get(hormiga1.getCaminoIndices().indexOf(i)));
            }
            else if (hormiga2.getCaminoIndices().contains(i)) {
                this.cantidadFeromonasCamino.set(i,(1 - coeficienteEvaporacion) * getCantidadFeromonasCamino().get(i) +
                        hormiga2.getCntQ()/hormiga2.getCaminoCostos().get(hormiga2.getCaminoIndices().indexOf(i)));
            }
            i++;
        }
    }
}
