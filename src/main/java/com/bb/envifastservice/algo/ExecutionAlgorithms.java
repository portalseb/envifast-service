package com.bb.envifastservice.algo;

import com.bb.envifastservice.GenerateTestData;
import com.bb.envifastservice.algo.antColony.Aco;
import com.bb.envifastservice.algo.antColony.AntSide;
import com.bb.envifastservice.algo.astar.AStarSearch;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

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

        //  Generar data de envios:
        Random rand = new Random();
        for(int i=0; i<20; i++){
            GenerateTestData test = new GenerateTestData();
            test.GenerateData("D:\\DOCUMENTOS\\FACI\\2022-2\\DP1\\DatosPruebaExperimentos\\data.prueba." + i + ".txt",aeropuertos,rand.nextInt(9500) + 501,6);

        }

        // Lectura de datos de un solo archivo
//        GenerateTestData test = new GenerateTestData();
//        test.GenerateData("D:\\Documentos\\Cursos\\Noveno ciclo\\DP1\\Algoritmos\\Datos_entrada\\data.prueba.1000.txt",aeropuertos,1000,6);

//        for(Aeropuerto a: lectorEnviosCorto.getDestinos()){
//            System.out.println(a.getCodigo());
//        }

        // Creación del archivo de tiempos de ejecución
        FileWriter fw = null;
        String resultado, resultadosAStar = "D:\\DOCUMENTOS\\FACI\\2022-2\\DP1\\Resultados.AStar.txt";
        try {
            fw = new FileWriter(resultadosAStar);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // TIEMPOS DE EJECUCION DEL A*
        for(int i=0; i<20; i++){
            // Lectura del archivo data.prueba.i.txt
            LectorEnviosCorto lectorEnviosCorto = new LectorEnviosCorto(aeropuertos);
            lectorEnviosCorto.Leer("D:\\DOCUMENTOS\\FACI\\2022-2\\DP1\\DatosPruebaExperimentos\\data.prueba." + i + ".txt");

            Avion a = new Avion("EBC1EBCI000000001");
            ArrayList<Avion> aviones = new ArrayList<>();
            aviones.add(a);
            TablaTiempos grafo = new TablaTiempos(arcos, aeropuertos, aviones);
            long tiempoTranscurrido = 0;
            System.out.println("Numero de envios del archivo data.prueba." + i + ".txt: " + lectorEnviosCorto.getDestinos().size());

            for(int j=0; j<lectorEnviosCorto.getDestinos().size(); j++){
                /* Llenamos los atributos de la clase grafo */
                grafo.calcularHeuristica(lectorEnviosCorto.getDestinos().get(j)); // calculamos la heuristica segun el nodo final Praga

                /* Luego, instanciamos la clase que buscara la ruta mas corta segun el nodo inicial */
                AStarSearch aStarSearch = new AStarSearch(grafo);

                lectorEnviosCorto.getOrigenes().get(j).setParent(null);

                /* Nodo inicial y final */
                aStarSearch.setInitialNode(lectorEnviosCorto.getOrigenes().get(j));
                aStarSearch.setFinalNode(lectorEnviosCorto.getDestinos().get(j));

                /* Finalmente probamos el algoritmo */
                ArrayList<Aeropuerto> rutaConseguida;

                double start = System.nanoTime();
                rutaConseguida = aStarSearch.findPath();
                double end = System.nanoTime();
                tiempoTranscurrido += end - start;
                System.out.println("Tiempo en milisegundos: "+ (end-start)/1000000.00);

                if(rutaConseguida != null){
                    System.out.println("Se encontro una solucion");
//                    for (Aeropuerto avion : rutaConseguida) {
//                        System.out.println(avion);
//                    }
                }else{
                    System.out.println("No se encontro solucion");
                }
            }

            resultado = String.valueOf(tiempoTranscurrido / 1000000000.00);
            System.out.println("Tiempo de ejec. del archivo Resultados.AStar." + i + ".txt en segundos: " + resultado);
            try {
                fw.write(resultado + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        Avion a = new Avion("EBC1EBCI000000001");
//        ArrayList<Avion> aviones = new ArrayList<>();
//        aviones.add(a);
//        TablaTiempos grafo = new TablaTiempos(arcos, aeropuertos, aviones);
//
//        // Creamos el archivo para obtener los datos de ejecucion de A*
//        String resultado, resultadosAStar = "D:\\Documentos\\Cursos\\Noveno ciclo\\DP1\\Algoritmos\\Datos_entrada\\Resultados.AStar.1000.txt";
//        FileWriter fw = null;
//        try {
//            fw = new FileWriter(resultadosAStar);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        double diferencia;
//        for(int i=0; i<lectorEnviosCorto.getDestinos().size(); i++){
//            /* Llenamos los atributos de la clase grafo */
//            grafo.calcularHeuristica(lectorEnviosCorto.getDestinos().get(i)); // calculamos la heuristica segun el nodo final Praga
//
//            /* Luego, instanciamos la clase que buscara la ruta mas corta segun el nodo inicial */
//            AStarSearch aStarSearch = new AStarSearch(grafo);
//
//            /* Nodo inicial y final */
//            aStarSearch.setInitialNode(lectorEnviosCorto.getOrigenes().get(i));
//            aStarSearch.setFinalNode(lectorEnviosCorto.getDestinos().get(i));
//
//            /* Finalmente probamos el algoritmo */
//            ArrayList<Aeropuerto> rutaConseguida;
//
//            double start1 = System.nanoTime();
//            rutaConseguida = aStarSearch.findPath();
//            double end1 = System.nanoTime();
//
//
//            System.out.println("Elapsed Time in milli seconds: "+ (end1-start1));
//
//            diferencia = end1 - start1;
//            diferencia = diferencia / 1000;
//            resultado = String.valueOf(diferencia);
//            System.out.println(resultado);
//            try {
//                fw.write(resultado + "\n");
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
//            if(rutaConseguida != null){
//                System.out.println("Se encontro una solucion: ");
//                for (Aeropuerto avion : rutaConseguida) {
//                    System.out.println(avion);
//                }
//            }else{
//                System.out.println("No se encontro solucion");
//            }
//        }
//        try {
//            fw.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        // TIEMPOS DE EJECUCION ACO
        FileWriter fw2 = null;
        String resultado2, resultadosACO = "D:\\DOCUMENTOS\\FACI\\2022-2\\DP1\\Resultados.ACO.txt";
        try {
            fw2 = new FileWriter(resultadosACO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(int i=0; i<20; i++){
            // Lectura del archivo data.prueba.i.txt
            LectorEnviosCorto lectorEnviosCorto = new LectorEnviosCorto(aeropuertos);
            lectorEnviosCorto.Leer("D:\\DOCUMENTOS\\FACI\\2022-2\\DP1\\DatosPruebaExperimentos\\data.prueba." + i + ".txt");
            // Creamos el ambiente
            AntSide ambiente= new AntSide(aeropuertos,arcos);

            long tiempoTranscurrido = 0;
            System.out.println("Numero de envios del archivo data.prueba." + i + ".txt: " + lectorEnviosCorto.getDestinos().size());
            for(int j=0; j<lectorEnviosCorto.getOrigenes().size(); j++){

                // Fijamos los aeropuertos de origen y destino (Ej: 0: Bogotá - 6: Lima
                ambiente.setNodoInicialFinal(aeropuertos.get(0),aeropuertos.get(11));
                // Creamos la solución
                Aco algoritmoHormigas = new Aco();
                long start1 = System.nanoTime();
                algoritmoHormigas.activarHormigas(ambiente);
                long end1 = System.nanoTime();
                System.out.println("Elapsed Time in milli seconds: "+ (end1-start1));
                tiempoTranscurrido += end1 - start1;
            }
            resultado2 = String.valueOf(tiempoTranscurrido / 1000000000.00);
            try {
                fw2.write(resultado2 + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            fw2.close();
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
