package com.bb.envifastservice.algo.antColony;

import com.bb.envifastservice.algo.Aeropuerto;
import com.bb.envifastservice.algo.ArcoAeropuerto;

import java.util.ArrayList;

public class Aco {
    private double solucionCosto;//No se deberia cambiar
    private ArrayList<String> solucionCamino; //Cambiar tipo de dato
    public Aco() {
        solucionCosto=999999;
        solucionCamino=new ArrayList<String>(); //Cambiar tipo de dato
    }
    public double getSolucionCosto() {
        return solucionCosto;
    }

    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /** Cambiar los tipos de datos*/
    public ArrayList<String> getSolucionCamino() {
        return solucionCamino;
    }
    /******************************************************************************************************************/
    /******************************************************************************************************************/

    public void activarHormigas(int cantAristas, int cantNodos, Aeropuerto nodoInicial, Aeropuerto nodoFin) {
        /**Inicializar ambiente*/
        /**Este constructor se cambiará, debe aceptar el arreglo de vuelos y aeropuertos, aeropuerto de inicio y fin, (mas adelante la cantidad de paquetes)*/
        AntSide ambiente= new AntSide(cantAristas,cantNodos,nodoInicial,nodoFin);
        /******************************************************************************************************************/
        /******************************************************************************************************************/
        /**Datos en duro para la prueba (eliminar)*/
        //Caminos (Seran los vuelos)
        Aeropuerto a1 = new Aeropuerto(1, "001", "Lima", "lima", "Peru",
                                       "Jorge Chavez", "PACIFICO", "America del sur");

        ambiente.getCaminos().add("1-2");
        ambiente.getCaminos().add("1-3");
        ambiente.getCaminos().add("1-6");
        ambiente.getCaminos().add("2-7");
        ambiente.getCaminos().add("2-3");
        ambiente.getCaminos().add("6-3");
        ambiente.getCaminos().add("6-5");
        ambiente.getCaminos().add("3-7");
        ambiente.getCaminos().add("3-5");
        ambiente.getCaminos().add("5-4");
        ambiente.getCaminos().add("7-4");

        //Costos (Seran los costos de los vuelos)
        ambiente.getCostos().add(5.00);
        ambiente.getCostos().add(3.10);
        ambiente.getCostos().add(5.20);
        ambiente.getCostos().add(5.20);
        ambiente.getCostos().add(4.90);
        ambiente.getCostos().add(3.20);
        ambiente.getCostos().add(4.70);
        ambiente.getCostos().add(3.00);
        ambiente.getCostos().add(6.00);
        ambiente.getCostos().add(5.50);
        ambiente.getCostos().add(4.80);

        //Visibiliad (Seran la inversa de los costos: 1/Costo)
        ambiente.getVisibilidad().add(0.2);
        ambiente.getVisibilidad().add(0.32);
        ambiente.getVisibilidad().add(0.19);
        ambiente.getVisibilidad().add(0.19);
        ambiente.getVisibilidad().add(0.2);
        ambiente.getVisibilidad().add(0.31);
        ambiente.getVisibilidad().add(0.21);
        ambiente.getVisibilidad().add(0.33);
        ambiente.getVisibilidad().add(0.16);
        ambiente.getVisibilidad().add(0.18);
        ambiente.getVisibilidad().add(0.21);

        //Nodos(seran los aeropuertos)
        ambiente.getNodos().add(1);
        ambiente.getNodos().add(2);
        ambiente.getNodos().add(3);
        ambiente.getNodos().add(4);
        ambiente.getNodos().add(5);
        ambiente.getNodos().add(6);
        ambiente.getNodos().add(7);

        //Nodo inicial y final (sera el aeropuerto de partida y llegada)
        ambiente.setNodoInicial(1);
        ambiente.setNodoFinal(4);
        /******************************************************************************************************************/
        /******************************************************************************************************************/


        /*****************************************************************************************************************/
        /*****************************************************************************************************************/
        /**Definir cual va a ser el limite y cambiar tipos de datos*/
        for(int i=0; i<5;i++){
            // //Inicializar hormiga 1
            Ant hormiga1 = new Ant(ambiente);
            hormiga1.explorar();

            // //Inicializar hormiga 2
            Ant hormiga2 = new Ant(ambiente);
            hormiga2.explorar();

            //// Actualizar rastro de feromonas
            ambiente.actualizarFeromonasEnElCamino(hormiga1, hormiga2);

            //Impresion para verificar el camino de las hormigas
            // Se deberia guardar el mejor camino en el ambiente tambien
            System.out.println("Camino de hormiga 1");
            System.out.println(hormiga1.getCaminoNodos().toString());
            System.out.println(hormiga1.getCostoTotal());
            System.out.println("Camino de hormiga 2");
            System.out.println(hormiga2.getCaminoNodos().toString());
            System.out.println(hormiga2.getCostoTotal());

            //System.out.println("El mejor de los dos");

            if(solucionCosto> hormiga1.getCostoTotal()|| solucionCosto>hormiga2.getCostoTotal()){
                if(hormiga1.getCostoTotal()<hormiga2.getCostoTotal()){
                    solucionCamino=new ArrayList<String>();
                    for(int j=0;j<hormiga1.getCaminoIndices().size();j++){
                        //System.out.println(ambiente.getCaminos().get(hormiga1.getCaminoIndices().get(j)));
                        solucionCamino.add(ambiente.getCaminos().get(hormiga1.getCaminoIndices().get(j)));
                    }
                    solucionCosto=hormiga1.getCostoTotal();
                }
                else {
                    solucionCamino=new ArrayList<String>();
                    for (int j = 0; j < hormiga2.getCaminoIndices().size(); j++){
                        //System.out.println(ambiente.getCaminos().get(hormiga2.getCaminoIndices().get(j)));
                        solucionCamino.add(ambiente.getCaminos().get(hormiga2.getCaminoIndices().get(j)));
                    }
                    solucionCosto= hormiga2.getCostoTotal();
                }
            }
        /*****************************************************************************************************************/
        /*****************************************************************************************************************/


        }
    }

    //Main para llamar al algoritmo
//    public static void main(String args[]) {
//        //Se debe leer los vuelos y aeropuertos
//        Aco algoritmoHormigas = new Aco();
//        algoritmoHormigas.activarHormigas(11,7,1,4);
//        System.out.println("Camino: " + algoritmoHormigas.getSolucionCamino().toString());
//    }
}



