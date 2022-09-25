package com.bb.envifastservice.algo;

import com.bb.envifastservice.GenerateTestData;
import com.bb.envifastservice.algo.astar.AStarSearch;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ExecutionAlgorithms {

    public static void main(String[] args) throws IOException {

        ArrayList<ArcoAeropuerto> arcos;
        ArrayList<Aeropuerto> aeropuertos;

        /* Leemos los aeropuertos y los planes de vuelo */
        LectorAeropuertos lectorAeropuertos = new LectorAeropuertos();
        lectorAeropuertos.Leer("D:\\Documentos\\Cursos\\Noveno ciclo\\DP1\\Algoritmos\\Datos_entrada\\c.inf226.22-2.lista.aeropuertos.v01.txt");
        aeropuertos = lectorAeropuertos.getAeropuertos();

        LectorArcoAeropuerto lectorArcos = new LectorArcoAeropuerto(aeropuertos);
        lectorArcos.Leer("D:\\Documentos\\Cursos\\Noveno ciclo\\DP1\\Algoritmos\\Datos_entrada\\c.inf226.22-2.planes_vuelo.v01.txt");
        arcos = lectorArcos.getArcos();

        // Generar data de envios:
//        GenerateTestData test = new GenerateTestData();
//        test.GenerateData("D:\\Documentos\\Cursos\\Noveno ciclo\\DP1\\Algoritmos\\Datos_entrada\\data.prueba.1000.txt",aeropuertos,1000,6);

        LectorEnviosCorto lectorEnviosCorto = new LectorEnviosCorto(aeropuertos);
        lectorEnviosCorto.Leer("D:\\Documentos\\Cursos\\Noveno ciclo\\DP1\\Algoritmos\\Datos_entrada\\data.prueba.1000.txt");

//        for(Aeropuerto a: lectorEnviosCorto.getDestinos()){
//            System.out.println(a.getCodigo());
//        }

        Avion a = new Avion("EBC1EBCI000000001");
        ArrayList<Avion> aviones = new ArrayList<>();
        aviones.add(a);
        TablaTiempos grafo = new TablaTiempos(arcos, aeropuertos, aviones);

        // Creamos el archivo para obtener los datos de ejecucion de A*
        String resultado, resultadosAStar = "D:\\Documentos\\Cursos\\Noveno ciclo\\DP1\\Algoritmos\\Datos_entrada\\Resultados.AStar.1000.txt";
        FileWriter fw = null;
        try {
            fw = new FileWriter(resultadosAStar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        double diferencia;
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

            double start1 = System.nanoTime();
            rutaConseguida = aStarSearch.findPath();
            double end1 = System.nanoTime();


            System.out.println("Elapsed Time in milli seconds: "+ (end1-start1));

            diferencia = end1 - start1;
            diferencia = diferencia / 1000;
            resultado = String.valueOf(diferencia);
            System.out.println(resultado);
            try {
                fw.write(resultado + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if(rutaConseguida != null){
                System.out.println("Se encontro una solucion: ");
                for (Aeropuerto avion : rutaConseguida) {
                    System.out.println(avion);
                }
            }else{
                System.out.println("No se encontro solucion");
            }
        }
        try {
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        // Creamos el archivo para guardar los resultados de Colonia de hormigas
//        String resultadosACO = "D:\\Documentos\\Cursos\\Noveno ciclo\\DP1\\Algoritmos\\Datos_entrada\\Resultados.ACO.txt";
//        FileWriter fw2 = null;
//        try {
//            fw2 = new FileWriter(resultadosACO);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        for (int i = 0 ; i < lectorEnviosCorto.getDestinos().size(); i++){
//            double start1 = System.nanoTime();
////          rutaConseguida = aStarSearch.findPath();
//            double end1 = System.nanoTime();
//            System.out.println("Elapsed Time in milli seconds: "+ (end1-start1));
//
//            diferencia = end1 - start1;
//            diferencia = diferencia / 1000;
//            resultado = String.valueOf(diferencia);
//            try {
//                fw2.write(resultado + "\n");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//        }
//        try {
//            fw2.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
