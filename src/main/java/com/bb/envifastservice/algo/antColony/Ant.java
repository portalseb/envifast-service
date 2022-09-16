package com.bb.envifastservice.algo.antColony;

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


//    public Ant()
//    {
//        this.ambienteGlobal=new AntSide();
//        this.feromonasEntreVisibilidad=new ArrayList<Double>(ambienteGlobal.getCostos().size());
//        this.ponderadoEscalaProbabilidades=new ArrayList<Double>(ambienteGlobal.getCostos().size());
//        this.probabilidadDeCaminoEntreSumatoria=new ArrayList<Double>(ambienteGlobal.getCostos().size());
//        this.caminoElegidoPorHormiga=new ArrayList<Boolean>(ambienteGlobal.getCostos().size());
//        //this.vCantidad_de_caminos=3;
//    }//Fin del cosntructor.


    public Ant(AntSide ambienteHormiga)
    {
        this.ambienteGlobal=ambienteHormiga;//new Hormigas_ambiente();
        this.feromonasEntreVisibilidad=new ArrayList<Double>(ambienteGlobal.getCostos().size());
        this.ponderadoEscalaProbabilidades=new ArrayList<Double>(ambienteGlobal.getCostos().size());
        this.probabilidadDeCaminoEntreSumatoria=new ArrayList<Double>(ambienteGlobal.getCostos().size());
        this.caminoElegidoPorHormiga=new ArrayList<Boolean>(ambienteGlobal.getCostos().size());
        //this.vCantidad_de_caminos=3;
    }


    public ArrayList<Double> probabilidadElegirUnCamino(AntSide ambiente)//El ambiente que se le pasa solo contiene los caminos posibles desde el nodo de la hormiga
    {
        int i=0;
        double sumatoriaDeProbabilidades=0.0;
        //Aqui se deberia limpiar las feromonasEntreVisibilidad, ponderadoEscalaProbabilidades, probabilidadDeCaminoEntreSumatoria


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



    public void explorar()
    {

        for (int i=0;i<4;i++) //Seria un while hasta que llegue al final (solucion)
        {
            // Hallar los caminos posibles desde el nodo donde esta la hormiga (no puede volver)
            //...metodo...
            //...

            //AntSide caminosHormiga = new AntSide(...)
            //probabilidadElegirUnCamino(caminosHormiga);
            //


            //Se usa el numero Random y se coloca el siguiente nodo: 1,2,3,4,5,6,7,8,9...
            //Se actualizaria un arreglo para guardar los nodos del camino de la hormiga y otro para guardar los costos.


        }

    }







}
