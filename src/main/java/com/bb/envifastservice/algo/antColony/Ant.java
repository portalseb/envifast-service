package com.bb.envifastservice.algo.antColony;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;

public class Ant {
    //private String vNombre_de_la_ormiga="";//No se crea un set ya que el nombre de la hormiga unicamente se pedirá al inicializar el objeto.

    private int cantidadCaminos;
    private ArrayList<Boolean> caminoElegidoPorHormiga;//
    private ArrayList<Double> feromonasEntreVisibilidad;
    private ArrayList<Double> ponderadoEscalaProbabilidades;
    private ArrayList<Double> probabilidadDeCaminoEntreSumatoria;

    public final double cntQ=0.0001;//Aprendizaje
    private AntSide ambienteGlobal=null;

    /*******************************************************************************************/
    /**Nuevo atributos para guardar el camino de una hormiga ***********************************/
    private ArrayList<Integer> caminoNodos;
    private ArrayList<Double> caminoCostos;
    /******************************************************************************************/
    /******************************************************************************************/


    /******************************************************************************************/
    /**Se agregan los nuevos atributos al constructor */
    public Ant(AntSide ambienteHormiga)
    {
        this.ambienteGlobal=ambienteHormiga;
        this.feromonasEntreVisibilidad=new ArrayList<Double>(ambienteGlobal.getCostos().size());
        this.ponderadoEscalaProbabilidades=new ArrayList<Double>(ambienteGlobal.getCostos().size());
        this.probabilidadDeCaminoEntreSumatoria=new ArrayList<Double>(ambienteGlobal.getCostos().size());
        this.caminoElegidoPorHormiga=new ArrayList<Boolean>(ambienteGlobal.getCostos().size());
        this.caminoNodos = new ArrayList<Integer>(ambienteGlobal.getCostos().size()); //Este tamaño solo sera suficiente si nos aseguramos que no puede recorrer un mismo camino 2 veces
        this.caminoCostos = new ArrayList<Double>(ambienteGlobal.getCostos().size()); //Este tamaño solo sera suficiente si nos aseguramos que no puede recorrer un mismo camino 2 veces
        this.caminoNodos.add(this.ambienteGlobal.getNodoInicial());

    }

    /******************************************************************************************/
    /******************************************************************************************/
    /**Se agregan los setter y getter de los nuevos atributos */
    public ArrayList<Integer> getCaminoNodos() {
        return caminoNodos;
    }

    public void setCaminoNodos(ArrayList<Integer> caminoNodos) {
        this.caminoNodos = caminoNodos;
    }

    public ArrayList<Double> getCaminoCostos() {
        return caminoCostos;
    }

    public void setCaminoCostos(ArrayList<Double> caminoCostos) {
        this.caminoCostos = caminoCostos;
    }

    /******************************************************************************************/
    /******************************************************************************************/


    public ArrayList<Double> probabilidadElegirUnCamino(AntSide ambiente)//El ambiente que se le pasa solo contiene los caminos posibles desde el nodo de la hormiga
    {
        int i=0;
        double sumatoriaDeProbabilidades=0.0;
        //Aqui se deberia limpiar las feromonasEntreVisibilidad, ponderadoEscalaProbabilidades, probabilidadDeCaminoEntreSumatoria
        //...
        //...
        //

        for(i=0;i <= ambiente.getCantidadFeromonasCamino().size()-1;i++)
        {
            // Para calcular la probabilidad de elegir un camino primero se multiplica las feromonas en el camino por la visibilidad.
            this.feromonasEntreVisibilidad.set(i, ambiente.getCantidadFeromonasCamino().get(i) * ambiente.getVisibilidad().get(i));
            //Luego se realiza una sumatoria de los caminos a elegir.
            sumatoriaDeProbabilidades += this.feromonasEntreVisibilidad.get(i);
        }
        double vTemp=0.0;
        for(i=0;i < ambiente.getCantidadFeromonasCamino().size();i++)
        {
            //Se calcula el Pxy.
            this.probabilidadDeCaminoEntreSumatoria.set(i, this.feromonasEntreVisibilidad.get(i)/sumatoriaDeProbabilidades);
            //Se realiza un ponderado para establecer una escala de probabilidades.
            this.ponderadoEscalaProbabilidades.set(i,this.probabilidadDeCaminoEntreSumatoria.get(i) + vTemp);
            vTemp = this.ponderadoEscalaProbabilidades.get(i);
        }
        return ponderadoEscalaProbabilidades;
    }

    /******************************************************************************************/
    /******************************************************************************************/
    /** Nuevo metodo para saber si la hormiga llego al final
    Se le pasaria el ultimo nodo al que ha saltado */
    public boolean llegoAlFinal(Integer nodoActual){
        return ambienteGlobal.getNodoFinal() == nodoActual;
    }
    /******************************************************************************************/
    /******************************************************************************************/

    public void explorar()
    {
        Integer nodoActual = ambienteGlobal.getNodoInicial();

        while(!llegoAlFinal(nodoActual)) //While hasta que llegue al final (solucion)
        {
            // Metodo para hallar los caminos posibles desde el nodo donde esta la hormiga
            // (no puede volver y podriamos poner que no pase por donde ya vino revisando el caminoNodos)
            //...metodo (Falta)...
            //...

            //Se crea un ambiente con los posibles caminos
            //AntSide caminosHormiga = new AntSide(...)


            // Metodo para generar la recta de probabilidades
            //probabilidadElegirUnCamino(caminosHormiga); //Esto se podria poner como parametro directamente en el metodo de abajo


            //Metodo para usar el numero Random y sacar el nuevo nodo
            ////...metodo (Falta)...
            //...


            //Metodo para actualizar el arreglo caminoNodos y caminoCostos con el nuevo nodo
            ////...metodo (Falta)...
            //...


            //Se actualiza nodoActual con el nuevo nodo
        }

    }







}
