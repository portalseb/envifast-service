package com.bb.envifastservice.algo.astar;

import com.bb.envifastservice.algo.Aeropuerto;
import com.bb.envifastservice.algo.ArcoAeropuerto;
import com.bb.envifastservice.algo.TablaTiempos;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
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
        while((parent = currentNode.getParent()) != null){
            path.add(0, parent);
            currentNode = parent;
        }
        return path;
    }

    private void addAdjacentNodes(Aeropuerto currentNode){
        // agregamos los nodos que estan cerca al objetivo, los nodos adyacentes
        // para el calculo de los nodos adyacentes tenemos que tener en cuenta
        // que se tiene que tomar la hora y leer los arcos aeropuertos que tengan
        // como nodo de partida al nodo actual.

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

    private void checkNode(Aeropuerto adjacentNode, Aeropuerto currentNode, int costo){
        if(!this.closedList.contains(adjacentNode)){
            if(!this.openList.contains(adjacentNode)){
                adjacentNode.setNodeData(currentNode, costo); // actualiza el costo de f = g(n) + h(n)
                this.openList.add(adjacentNode);
            }else{
                boolean changed = adjacentNode.checkBetterPath(currentNode, costo);
                if(changed){
                    // removemos y agregamos el nodo adyacente, para que la cola de prioridad
                    // pueda ordenar de nuevo sus contenidos con el valor modificado de finalCost.
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
