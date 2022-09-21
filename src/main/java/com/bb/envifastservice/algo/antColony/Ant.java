package com.bb.envifastservice.algo.antColony;

import com.bb.envifastservice.algo.Aeropuerto;
import com.bb.envifastservice.algo.ArcoAeropuerto;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.Random;

public class Ant {
    private int cantidadCaminos; //De momento no se usa
    private ArrayList<Boolean> caminoElegidoPorHormiga;//De momento no se usa
    private ArrayList<Double> feromonasEntreVisibilidad; //2da columna de las tablas por cada paso, no se cambia
    private ArrayList<Double> ponderadoEscalaProbabilidades; //recta de probabilidades, no se cambia
    private ArrayList<Double> probabilidadDeCaminoEntreSumatoria; //3era columna de las tablas por cada paso, no se cambia
    private ArrayList<Integer> posiblesCaminosIndices; //Indices (en el arreglo caminos de Antside) de los caminos que puede recorrer la hormiga, no se cambia
    private ArrayList<Integer> caminoIndices; //Indices (en el arreglo caminos de Antside) de los caminos que recorre la hormiga, no se cambia
    private double costoTotal;//Costo del camino que siguio la hormiga, no se cambia
    public double cntQ= 1;//Aprendizaje
    private AntSide ambienteGlobal=null;
    private ArrayList<Aeropuerto> caminoNodos; //Cambiar tipo de dato -> Aeropuerto
    private ArrayList<Double> caminoCostos; //Cambiar tipo de dato -> ArcoAeropuerto


    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /** Cambiar tipos de dato */
    public Ant(AntSide ambienteHormiga)
    {
        this.ambienteGlobal=ambienteHormiga;
        this.feromonasEntreVisibilidad=new ArrayList<Double>(ambienteGlobal.getCostos().size());
        this.ponderadoEscalaProbabilidades=new ArrayList<Double>(ambienteGlobal.getCostos().size());
        this.probabilidadDeCaminoEntreSumatoria=new ArrayList<Double>(ambienteGlobal.getCostos().size());
        this.caminoElegidoPorHormiga=new ArrayList<Boolean>(ambienteGlobal.getCostos().size());
        this.caminoNodos = new ArrayList<Aeropuerto>(ambienteGlobal.getCostos().size()); //Este tamaño solo sera suficiente si nos aseguramos que no puede recorrer un mismo camino 2 veces
        this.caminoCostos = new ArrayList<Double>(ambienteGlobal.getCostos().size()); //Este tamaño solo sera suficiente si nos aseguramos que no puede recorrer un mismo camino 2 veces
        this.caminoNodos.add(this.ambienteGlobal.getNodoInicial());
        posiblesCaminosIndices = new ArrayList<Integer>();
        caminoIndices = new ArrayList<Integer>();
        costoTotal=0.0;
    }
    public ArrayList<Aeropuerto> getCaminoNodos() {
        return caminoNodos;
    }
    public void setCaminoNodos(ArrayList<Aeropuerto> caminoNodos) {
        this.caminoNodos = caminoNodos;
    }
    /******************************************************************************************************************/
    /******************************************************************************************************************/

    public ArrayList<Double> getCaminoCostos() {
        return caminoCostos;
    }

    public void setCaminoCostos(ArrayList<Double> caminoCostos) {
        this.caminoCostos = caminoCostos;
    }

    public ArrayList<Integer> getPosiblesCaminosIndices() {
        return posiblesCaminosIndices;
    }

    public void setPosiblesCaminosIndices(ArrayList<Integer> posiblesCaminosIndices) {
        this.posiblesCaminosIndices = posiblesCaminosIndices;
    }

    public ArrayList<Integer> getCaminoIndices() {
        return caminoIndices;
    }

    public void setCaminoIndices(ArrayList<Integer> caminoIndices) {
        this.caminoIndices = caminoIndices;
    }

    public double getCntQ() {
        return cntQ;
    }

    public double getCostoTotal(){
        return this.costoTotal;
    }

    public ArrayList<Double> probabilidadElegirUnCamino(AntSide ambiente)//El ambiente que se pasa contiene los caminos posibles desde el nodo de la hormiga
    {
        int i=0;
        double sumatoriaDeProbabilidades=0.0;
        //Se limpian las feromonasEntreVisibilidad, ponderadoEscalaProbabilidades, probabilidadDeCaminoEntreSumatoria
        feromonasEntreVisibilidad = new ArrayList<Double>(ambiente.getCaminos().size());
        ponderadoEscalaProbabilidades = new ArrayList<Double>(ambiente.getCaminos().size());
        probabilidadDeCaminoEntreSumatoria = new ArrayList<Double>(ambiente.getCaminos().size());


        for(i=0;i <= ambiente.getCantidadFeromonasCamino().size()-1;i++)
        {
            // Para calcular la probabilidad de elegir un camino primero se multiplica las feromonas en el camino por la visibilidad.
            this.feromonasEntreVisibilidad.add(ambiente.getCantidadFeromonasCamino().get(i) * ambiente.getVisibilidad().get(i));
            //Luego se realiza una sumatoria de los caminos a elegir.
            sumatoriaDeProbabilidades += this.feromonasEntreVisibilidad.get(i);
        }
        double vTemp=0.0;
        for(i=0;i < ambiente.getCantidadFeromonasCamino().size();i++)
        {
            //Se calcula el probablidad/sumatoriaDeProbabilidades para cada camino
            this.probabilidadDeCaminoEntreSumatoria.add( this.feromonasEntreVisibilidad.get(i)/sumatoriaDeProbabilidades);
            //Se realiza un ponderado para establecer una escala de probabilidades.
            this.ponderadoEscalaProbabilidades.add(this.probabilidadDeCaminoEntreSumatoria.get(i) + vTemp);
            vTemp = this.ponderadoEscalaProbabilidades.get(i);
        }
        return ponderadoEscalaProbabilidades;
    }


    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /** Cambiar el tipo de dato del nodo */
    public boolean llegoAlFinal(Aeropuerto nodoActual){
        return ambienteGlobal.getNodoFinal() == nodoActual;
    }
    /******************************************************************************************************************/
    /******************************************************************************************************************/



    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /** Cambiar los tipos de datos*/
    public int nodoSiguiente(ArrayList<Double> rectaProbabilidades){
        Random rand = new Random();
        double pos = rand.nextDouble();
        int i=0;
        for(i=0;i<rectaProbabilidades.size();i++) {
            if (i == 0){
                if (rectaProbabilidades.get(i) >= pos) return i;
            }
            else {
                if (rectaProbabilidades.get(i - 1) <= pos && rectaProbabilidades.get(i) >= pos)
                    break;
            }
        }
        return i;
    }
    /******************************************************************************************************************/
    /******************************************************************************************************************/


    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /** Cambiar los tipos de datos y poner los limites de tiempo ******************************************************/
    public AntSide posiblesCaminos(AntSide ambienteGlob, ArrayList<Aeropuerto> nodos, ArrayList<Double> costos,
                                   Aeropuerto nodoAnt, Aeropuerto nodoAct){
        AntSide caminosHormiga = new AntSide(); //no se si se puede esto
        ArcoAeropuerto camino;
        Aeropuerto origen, destino;
        posiblesCaminosIndices = new ArrayList<Integer>(); //0: 1, 1: 3

        for(int i=0;i<ambienteGlob.getCaminos().size();i++){
            camino = ambienteGlob.getCaminos().get(i);
            origen = new Aeropuerto(camino.getAeropuerto1());
            destino = new Aeropuerto(camino.getAeropuerto2());
            //Aqui se verifica que si el camino comienza con el nodo actual, el detino no puede ser el nodo anterior
            //Tambien se le podria pasar todo el camino para que no regrese por ningun nodo anterior
            /** Tambien se verificará aqui que cumpla los limites de tiempo*/
            if((origen.getId() == nodoAct.getId() && nodoAct.getId() == ambienteGlob.getNodoInicial().getId()) ||
                    (origen.getId() == nodoAct.getId() && destino.getId()!=nodoAnt.getId() )){
                caminosHormiga.getCaminos().add(camino);
                caminosHormiga.getCantidadFeromonasCamino().add(ambienteGlob.getCantidadFeromonasCamino().get(i));
                caminosHormiga.getVisibilidad().add(ambienteGlob.getVisibilidad().get(i));
                caminosHormiga.getCostos().add(ambienteGlob.getCostos().get(i));
                posiblesCaminosIndices.add(i);
            }
        }
        return caminosHormiga;
    }
    /******************************************************************************************************************/
    /******************************************************************************************************************/



    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /** Cambiar los tipos de datos*************************************************************************************/
    public void explorar()
    {
        Aeropuerto nodoActual = ambienteGlobal.getNodoInicial();
        Aeropuerto nuevoNodo, nodoAnt = new Aeropuerto();
        int pos=0;
        AntSide caminosHormiga = null;
        ArcoAeropuerto camino = null;

        while(!llegoAlFinal(nodoActual)) //While hasta que llegue al final (solucion)
        {
            // Hallar los caminos posibles desde el nodo donde esta la hormiga
            // (no puede volver y podriamos poner que no pase por nodos donde ya estuvo revisando el caminoNodos)
            caminosHormiga = posiblesCaminos(this.ambienteGlobal, this.caminoNodos, this.caminoCostos, nodoAnt, nodoActual);


            // Se genera y se usa la recta de probabilidades para sacar el nuevo nodo
            pos = nodoSiguiente(probabilidadElegirUnCamino(caminosHormiga));
            camino = caminosHormiga.getCaminos().get(pos);
            nuevoNodo = camino.getAeropuerto2();

            //Actualizar el arreglo caminoNodos y caminoCostos con el nuevo nodo
            this.caminoNodos.add(nuevoNodo);
            this.caminoCostos.add(caminosHormiga.getCostos().get(pos));
            this.costoTotal = this.costoTotal + caminosHormiga.getCostos().get(pos);

            //Arreglo que sirve para guardar los indices de los caminos del arreglo del ambiente global
            this.caminoIndices.add(posiblesCaminosIndices.get(pos));

            //Se actualiza nodoAnterior con el nodo actual
            nodoAnt = nodoActual;

            //Se actualiza nodoActual con el nuevo nodo
            nodoActual = nuevoNodo;
        }
    }
    /******************************************************************************************************************/
    /******************************************************************************************************************/
}
