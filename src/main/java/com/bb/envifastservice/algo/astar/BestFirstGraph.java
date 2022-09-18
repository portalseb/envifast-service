package com.bb.envifastservice.algo.astar;

import com.bb.envifastservice.algo.Aeropuerto;
import com.bb.envifastservice.algo.TablaTiempos;

import java.util.*;

public class BestFirstGraph {
    private PriorityQueue<Aeropuerto> openList;
    private Set<Aeropuerto> closedList;
    private ArrayList<Aeropuerto> visitedNodes ; // lista de nodos visitados
    private Aeropuerto initialNode;
    private Aeropuerto finalNode;
    private TablaTiempos graph;
    // tendremos que usar si o si este constructor
    public BestFirstGraph(TablaTiempos graph){
        this.graph = graph;
        this.initialNode = new Aeropuerto();
        this.finalNode = new Aeropuerto();
        this.closedList = new HashSet<>();
        this.openList = new PriorityQueue<>();

            // necesitamos una funcion en cada nodo que nos calcule la heuristica segun el nodo final
        // en el ejemplo de la pagina web, ellos usaron la distancia euclidiana.
            // en nuesto caso tendra que ser la capacidad que tiene el aeropuerto(o no?)

        // calculoHeuristica(); // aqui calculara la heuristica para cada nodo
    }


    // en nuestro caso no hay bloqueos

    public void setInitialNode(Aeropuerto initialNode) {
        this.initialNode = initialNode;
    }

    public void setFinalNode(Aeropuerto finalNode) {
        this.finalNode = finalNode;
    }

    public void setGraph(TablaTiempos graph) {
        this.graph = graph;
    }
}
