package com.bb.envifastservice.algo.antColony;

import com.bb.envifastservice.algo.Aeropuerto;
import com.bb.envifastservice.algo.ArcoAeropuerto;
import com.bb.envifastservice.algo.LectorAeropuertos;
import com.bb.envifastservice.algo.LectorArcoAeropuerto;

import java.io.IOException;
import java.util.ArrayList;

public class Aco {
    private double solucionCosto;//No se deberia cambiar
    private ArrayList<ArcoAeropuerto> solucionCamino; //Cambiar tipo de dato
    public Aco() {
        solucionCosto=999999;
        solucionCamino=new ArrayList<ArcoAeropuerto>(); //Cambiar tipo de dato
    }
    public double getSolucionCosto() {
        return solucionCosto;
    }
    public ArrayList<ArcoAeropuerto> getSolucionCamino() {
        return solucionCamino;
    }
    public void activarHormigas(AntSide ambiente) {
        /*****************************************************************************************************************/
        /**Definir cual va a ser el limite y la cantidad de hormigas*/
        int contProb1=0, contProb2=0;
        //for(int i=0; i<100;i++){
        while(true) {
            // Inicializar hormiga 1
            Ant hormiga1 = new Ant(ambiente);
            hormiga1.explorar();

            // Inicializar hormiga 2
            Ant hormiga2 = new Ant(ambiente);
            hormiga2.explorar();


            // Impresion para verificar el camino de las hormigas, se deberia guardar el mejor camino en el ambiente tambien
//            System.out.println("Camino de hormiga 1");
//            System.out.println(hormiga1.getCaminoNodos().toString());
//            System.out.println(hormiga1.getCostoTotal());

//            System.out.println(hormiga1.getCaminoProbabilidades().toString());

//            System.out.println("Camino de hormiga 2");
//            System.out.println(hormiga2.getCaminoNodos().toString());
//            System.out.println(hormiga2.getCostoTotal());

//            System.out.println(hormiga2.getCaminoProbabilidades().toString());

            // Actualizar rastro de feromonas
            if(hormiga1.isEncontroCamino() && hormiga2.isEncontroCamino())
            {
            ambiente.actualizarFeromonasEnElCamino(hormiga1, hormiga2);

            if (solucionCosto > hormiga1.getCostoTotal() || solucionCosto > hormiga2.getCostoTotal()) {
                if (hormiga1.getCostoTotal() < hormiga2.getCostoTotal()) {
                    solucionCamino = new ArrayList<ArcoAeropuerto>();
                    for (int j = 0; j < hormiga1.getCaminoIndices().size(); j++) {
                        //System.out.println(ambiente.getCaminos().get(hormiga1.getCaminoIndices().get(j)));
                        solucionCamino.add(ambiente.getCaminos().get(hormiga1.getCaminoIndices().get(j)));
                    }
                    solucionCosto = hormiga1.getCostoTotal();
                } else {
                    solucionCamino = new ArrayList<ArcoAeropuerto>();
                    for (int j = 0; j < hormiga2.getCaminoIndices().size(); j++) {
                        //System.out.println(ambiente.getCaminos().get(hormiga2.getCaminoIndices().get(j)));
                        solucionCamino.add(ambiente.getCaminos().get(hormiga2.getCaminoIndices().get(j)));
                    }
                    solucionCosto = hormiga2.getCostoTotal();
                }
            }
            contProb1 = 0;
            for (int p = 0; p < hormiga1.getCaminoProbabilidades().size(); p++)
                if (hormiga1.getCaminoProbabilidades().get(p) >= 0.9)
                    contProb1++;
            contProb2 = 0;
            for (int p = 0; p < hormiga2.getCaminoProbabilidades().size(); p++)
                if (hormiga2.getCaminoProbabilidades().get(p) >= 0.9)
                    contProb2++;
            if (contProb1 == hormiga1.getCaminoProbabilidades().size() || contProb2 == hormiga2.getCaminoProbabilidades().size()) {
                System.out.println("Se llego al limite");
                break;
            }
        }
        }
    }

    //Main para llamar al algoritmo
    public static void main(String args[]) throws IOException {
        ArrayList<ArcoAeropuerto> arcos;
        ArrayList<Aeropuerto> aeropuertos;

        /* Leemos los aeropuertos y los planes de vuelo */
        LectorAeropuertos lectorAeropuertos = new LectorAeropuertos();
        lectorAeropuertos.Leer("C:\\Users\\Fernando\\Desktop\\fernando\\Archivos Pucp\\ciclo 9\\dp1\\algoritmos\\c.inf226.22-2.lista.aeropuertos.v01.txt");
//        lectorAeropuertos.Leer("D:\\Documentos\\Cursos\\Noveno ciclo\\DP1\\Algoritmos\\Datos_entrada\\c.inf226.22-2.lista.aeropuertos.v01.txt");
        aeropuertos = lectorAeropuertos.getAeropuertos();

        LectorArcoAeropuerto lectorArcos = new LectorArcoAeropuerto(aeropuertos);
        lectorArcos.Leer("C:\\Users\\Fernando\\Desktop\\fernando\\Archivos Pucp\\ciclo 9\\dp1\\algoritmos\\c.inf226.22-2.planes_vuelo.v01.txt");
//        lectorArcos.Leer("D:\\Documentos\\Cursos\\Noveno ciclo\\DP1\\Algoritmos\\Datos_entrada\\c.inf226.22-2.planes_vuelo.v01.txt");
        arcos = lectorArcos.getArcos();

        //Se crea los arcos para los siguientes 3 dias:
//        for(int i =0;i<arcos.size();i++){
//            ArcoAeropuerto arco = new ArcoAeropuerto();
//            arcos.add(arco);
//
//        }



        // Creamos el ambiente
        /**Este constructor se cambiará, debe aceptar el arreglo de vuelos y aeropuertos, aeropuerto de inicio y fin, (mas adelante la cantidad de paquetes)*/
        AntSide ambiente= new AntSide(aeropuertos,arcos);


        // Fijamos los aeropuertos de origen y destino (Ej: 0: Bogotá - 6: Lima
        ambiente.setNodoInicialFinal(aeropuertos.get(0),aeropuertos.get(11));
        // Creamos la solución
        Aco algoritmoHormigas = new Aco();
        long start1 = System.currentTimeMillis();
        algoritmoHormigas.activarHormigas(ambiente);
        long end1 = System.currentTimeMillis();
        System.out.println("Elapsed Time in milli seconds: "+ (end1-start1));

        //Se imprime la solucion
        System.out.println("Camino: " + algoritmoHormigas.getSolucionCamino().toString() + algoritmoHormigas.getSolucionCosto());

    }
}



