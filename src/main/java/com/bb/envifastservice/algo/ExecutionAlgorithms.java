package com.bb.envifastservice.algo;

import com.bb.envifastservice.GenerateTestData;
import com.bb.envifastservice.algo.astar.AStarSearch;

import java.io.IOException;
import java.util.ArrayList;

public class ExecutionAlgorithms {

    public static void main(String[] args) throws IOException {

        ArrayList<ArcoAeropuerto> arcos;
        ArrayList<Aeropuerto> aeropuertos;

        /* Leemos los aeropuertos y los planes de vuelo */
        LectorAeropuertos lectorAeropuertos = new LectorAeropuertos();
        lectorAeropuertos.Leer("D:\\DOCUMENTOS\\FACI\\2022-2\\DP1\\c.inf226.22-2.lista.aeropuertos.v01.txt");
        aeropuertos = lectorAeropuertos.getAeropuertos();

        LectorArcoAeropuerto lectorArcos = new LectorArcoAeropuerto(aeropuertos);
        lectorArcos.Leer("D:\\DOCUMENTOS\\FACI\\2022-2\\DP1\\c.inf226.22-2.planes_vuelo.v01.txt");
        arcos = lectorArcos.getArcos();

        // Generar data de envios:
//        GenerateTestData test = new GenerateTestData();
//        test.GenerateData("D:\\DOCUMENTOS\\FACI\\2022-2\\DP1\\data.prueba.01.txt",aeropuertos,100,6);

        LectorEnviosCorto lectorEnviosCorto = new LectorEnviosCorto(aeropuertos);
        lectorEnviosCorto.Leer("D:\\DOCUMENTOS\\FACI\\2022-2\\DP1\\data.prueba.01.txt");

//        for(Aeropuerto a: lectorEnviosCorto.getDestinos()){
//            System.out.println(a.getCodigo());
//        }

        Avion a = new Avion("EBC1EBCI000000001");
        ArrayList<Avion> aviones = new ArrayList<>();
        aviones.add(a);
        TablaTiempos grafo = new TablaTiempos(arcos, aeropuertos, aviones);

        for(int i=0; i<lectorEnviosCorto.getDestinos().size(); i++){
            /* Llenamos los atributos de la clase grafo */
            grafo.calcularHeuristica(lectorEnviosCorto.getDestinos().get(i)); // calculamos la heuristica segun el nodo final Praga

            /* Luego, instanciamos la clase que buscara la ruta mas corta segun el nodo inicial */
            AStarSearch aStarSearch = new AStarSearch(grafo);

            /* Nodo inicial y final */
            aStarSearch.setInitialNode(lectorEnviosCorto.getOrigenes().get(i));
            aStarSearch.setFinalNode(lectorEnviosCorto.getDestinos().get(i));

            /* Finalmente probamos el algoritmo */
            ArrayList<Aeropuerto> rutaConseguida;

            long start1 = System.currentTimeMillis();
            rutaConseguida = aStarSearch.findPath();
            long end1 = System.currentTimeMillis();
            System.out.println("Elapsed Time in milli seconds: "+ (end1-start1));

            if(rutaConseguida != null){
                System.out.println("Se encontro una solucion: ");
                for (Aeropuerto avion : rutaConseguida) {
                    System.out.println(avion);
                }
            }else{
                System.out.println("No se encontro solucion");
            }
        }

    }
}
