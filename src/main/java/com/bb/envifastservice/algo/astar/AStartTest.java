package com.bb.envifastservice.algo.astar;


import com.bb.envifastservice.algo.*;

import java.io.IOException;
import java.util.ArrayList;

public class AStartTest {
    public static void main(String args[]) throws IOException {

        ArrayList<ArcoAeropuerto> arcos;
        ArrayList<Aeropuerto> aeropuertos;

        /* Leemos los aeropuertos y los planes de vuelo */
        LectorAeropuertos lectorAeropuertos = new LectorAeropuertos();
        lectorAeropuertos.Leer("D:\\Documentos\\Cursos\\Noveno ciclo\\DP1\\Algoritmos\\Datos_entrada\\c.inf226.22-2.lista.aeropuertos.v01.txt");
        aeropuertos = lectorAeropuertos.getAeropuertos();

        LectorArcoAeropuerto lectorArcos = new LectorArcoAeropuerto(aeropuertos);
        lectorArcos.Leer("D:\\Documentos\\Cursos\\Noveno ciclo\\DP1\\Algoritmos\\Datos_entrada\\c.inf226.22-2.planes_vuelo.v01.txt");
        arcos = lectorArcos.getArcos();

        /* Creamos una instancia de avion */
        Avion a = new Avion("EBC1EBCI000000001");
        ArrayList<Avion> aviones = new ArrayList<>();
        aviones.add(a);

        /* Llenamos los atributos de la clase grafo */
        TablaTiempos grafo = new TablaTiempos(arcos, aeropuertos, aviones);
        grafo.calcularHeuristica(aeropuertos.get(16)); // calculamos la heuristica segun el nodo final Praga

        /* Luego, instanciamos la clase que buscara la ruta mas corta segun el nodo inicial */
        AStarSearch aStarSearch = new AStarSearch(grafo);

        /* Nuestro nodo inicial sera SKBO(Bogota) y tiene que viajar a LKPR(Praga)*/
        aStarSearch.setInitialNode(aeropuertos.get(1));
        aStarSearch.setFinalNode(aeropuertos.get(38));

        /* Finalmente probamos el algoritmo */
        ArrayList<Aeropuerto> rutaConseguida;
        rutaConseguida = aStarSearch.findPath();
        if(rutaConseguida != null){
            System.out.println("Se encontro una solucion: ");
            for (Aeropuerto avion
                    :
                 rutaConseguida) {
                System.out.println(avion);
            }
        }else{
            System.out.println("No se encontro solucion");
        }

        // FALTA CORREGIR, LA RUTA SOLO BOTA EL NODO INICIAL Y EL FINAL GAA
        // MIRAR LAS FUNCIONES PARA CALCULAR LOS NODOS ADJACENTES
    }
}
