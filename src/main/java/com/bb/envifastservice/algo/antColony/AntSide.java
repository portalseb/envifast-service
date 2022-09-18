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

    public static final double coeficienteEvaporacion = 0.01;


    /********************************************************/
    /**Nuevos atributos de inicio y final*/
    public Integer nodoInicial;
    public Integer nodoFinal;
    /********************************************************/
    /********************************************************/

    /**Constructor necesario para crear las nuevas tablas en caada paso de la hormiga*/
    public AntSide(){
        caminos = new ArrayList<String>();
        nodos = new ArrayList<Integer>();
        costos = new ArrayList<Double>();
        visibilidad = new ArrayList<Double>();
        cantidadFeromonasCamino = new ArrayList<Double>();
        probabilidadDeSerEscogido = new ArrayList<Double>();
        numeroVecesDeSerEscogigo = new ArrayList<Integer>();
    }

    public AntSide(int numeroAristas, int numeroNodos) {
        caminos = new ArrayList<String>(numeroAristas);
        nodos = new ArrayList<Integer>(numeroNodos);
        costos = new ArrayList<Double>(numeroAristas);
        visibilidad = new ArrayList<Double>(numeroAristas);
        cantidadFeromonasCamino = new ArrayList<Double>(numeroAristas);
        for(int i=0;i<numeroAristas;i++){
            cantidadFeromonasCamino.add(0.1);
        }
        probabilidadDeSerEscogido = new ArrayList<Double>(numeroAristas);
        numeroVecesDeSerEscogigo = new ArrayList<Integer>(numeroAristas);
    }

    /*******************************************************************************************/
    /**Nuevo constructor con el nodoInicio y nodoFinal *****************************************/
    public AntSide(int numeroAristas, int numeroNodos, int nodoIni, int nodoFin) {
        caminos = new ArrayList<String>(numeroAristas);
        nodos = new ArrayList<Integer>(numeroNodos);
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
    /*****************************************************************************************/
    /*****************************************************************************************/
    /** Setter y getter de nodo inicial y final*/
    public Integer getNodoInicial() {
        return nodoInicial;
    }

    public void setNodoInicial(Integer nodoInicial) {
        this.nodoInicial = nodoInicial;
    }

    public Integer getNodoFinal() {
        return nodoFinal;
    }

    public void setNodoFinal(Integer nodoFinal) {
        this.nodoFinal = nodoFinal;
    }
    /*****************************************************************************************/
    /*****************************************************************************************/




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
