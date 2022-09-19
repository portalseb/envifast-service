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

    public void calculoHeuristica(){
        // Calculamos la heuristica con el nodo final que tenemos como atributo

        // Aqui tenemos que recorrer todos los nodos de los aeropuertos y encontrar el costo minimo
        // entre cada aeropuerto y el nodo final.
    }

    public ArrayList<Aeropuerto> findPath(){
        openList.add(this.initialNode);// inicializamos la lista de nodos abiertos
        while(!openList.isEmpty()){
            Aeropuerto currentNode = openList.poll();
            closedList.add(currentNode);
            if(isFinalNode(currentNode)){
                return getPath(currentNode);
            }
        }
        return null;
    }

    // para obtemer el camino de la solucion
    private ArrayList<Aeropuerto> getPath(Aeropuerto currentNode){
        ArrayList<Aeropuerto> path = new ArrayList<>();
        path.add(currentNode);
        Aeropuerto parent;
        while((parent = currentNode.getPadre()) != null){
            path.add(0, parent);
            currentNode = parent;
        }
        return path;
    }

    private void addAdjacentNodes(Aeropuerto currentNode){
        // agregamos los nodos que estan cerca al objetivo, los nodos adyacentes
    }



    private boolean isFinalNode(Aeropuerto currentNode){
        return currentNode.equals(this.finalNode);
    }


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
