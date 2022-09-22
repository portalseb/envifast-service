package com.bb.envifastservice.algo.astar;

import com.bb.envifastservice.algo.Aeropuerto;
import com.bb.envifastservice.algo.ArcoAeropuerto;
import com.bb.envifastservice.algo.TablaTiempos;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class AStarSearch {
    private PriorityQueue<Aeropuerto> openList;
    private Set<Aeropuerto> closedList;
    private ArrayList<Aeropuerto> visitedNodes ; // lista de nodos visitados
    private Aeropuerto initialNode;
    private Aeropuerto finalNode;
    private TablaTiempos graph;
    // tendremos que usar si o si este constructor
    public AStarSearch(TablaTiempos graph){
        this.graph = graph;
        this.initialNode = new Aeropuerto();
        this.finalNode = new Aeropuerto();
        this.closedList = new HashSet<>();
        this.openList = new PriorityQueue<>();

    }

    // efectivamente, el calculo de la heuristica se tiene que buscar en el grafo

    public void calculateHeuristic(){
//        for (Aeropuerto a: this.graph.
//             ) {
//
//        }
    }

    public ArrayList<Aeropuerto> findPath(){
        openList.add(this.initialNode);// inicializamos la lista de nodos abiertos
        while(!openList.isEmpty()){
            Aeropuerto currentNode = openList.poll();
            closedList.add(currentNode);
            if(isFinalNode(currentNode)){
                return getPath(currentNode);
            }else{
                addAdjacentNodes(currentNode);
            }
        }
        return null;
    }

    // para obtemer el camino de la solucion
    private ArrayList<Aeropuerto> getPath(Aeropuerto currentNode){
        ArrayList<Aeropuerto> path = new ArrayList<>();
        path.add(currentNode);
        Aeropuerto parent;
        while((parent = currentNode.getParent()) != null){
            path.add(0, parent);
            currentNode = parent;
        }
        return path;
    }

    // para agregar todos los nodos que estan cerca al objetivo. Para el calculo de nodos adyacentes
    // se toman los que esten en los planes de vuelo disponibles a esa hora y que tengan como
    // nodo de partida al nodo actual
    private void addAdjacentNodes(Aeropuerto currentNode){
        for (ArcoAeropuerto
                arco:
             this.graph.getArcos()) {
            if(arco.getHoraPartida().compareTo(LocalTime.from(LocalDate.now())) > 0){
                if(arco.getAeropuerto1().getCodigo() == currentNode.getCodigo()){
                    checkNode(arco.getAeropuerto1(), currentNode, arco.obtenerDuracionVuelo().toMinutesPart());
                }
            }
        }

    }

    // este metodo nos permite saber si el nodo esta en la lista abierta o cerrada
    // si no esta en la lista abierta lo agrega y si ya esta entonces actualizamos f = g(n) + h(n).
    private void checkNode(Aeropuerto adjacentNode, Aeropuerto currentNode, int costo){
        if(!this.closedList.contains(adjacentNode)){
            if(!this.openList.contains(adjacentNode)){
                adjacentNode.setNodeData(currentNode, costo);
                this.openList.add(adjacentNode);
            }else{
                boolean changed = adjacentNode.checkBetterPath(currentNode, costo);
                if(changed){
                    // removemos y agregamos el nodo adyacente, para que la cola de prioridad
                    // para que pueda ordenar de nuevo sus contenidos con el valor modificado de finalCost.
                    this.openList.remove(adjacentNode);
                    this.openList.add(adjacentNode);
                }
            }
        }
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
