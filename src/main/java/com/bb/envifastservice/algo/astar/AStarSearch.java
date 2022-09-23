package com.bb.envifastservice.algo.astar;

import com.bb.envifastservice.algo.Aeropuerto;
import com.bb.envifastservice.algo.ArcoAeropuerto;
import com.bb.envifastservice.algo.TablaTiempos;

import java.sql.Time;
import java.time.Instant;
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
        // esta funcion esta fallando
        for (ArcoAeropuerto
                arco:
             this.graph.getArcos()) {
            int horaLlegada = arco.getHoraPartida().getHour() * 60 +  arco.getHoraPartida().getMinute();

//            System.out.println("Duracion vuelo: " + duracionVuelo);
//            int horaActual = LocalDate.now().atStartOfDay().getHour() * 60 + LocalDate.now().atStartOfDay().getMinute();
            Calendar now = Calendar.getInstance();
            int hour = now.get(Calendar.HOUR_OF_DAY);
            int minute = now.get(Calendar.MINUTE);
            int horaActual = ((hour * 60) + minute);;
//            if(arco.getHoraPartida().compareTo(LocalTime.from(LocalDate.now())) > 0){
//                if(arco.getAeropuerto1().getCodigo() == currentNode.getCodigo()){
//                    checkNode(arco.getAeropuerto1(), currentNode, arco.obtenerDuracionVuelo().toMinutesPart());
//                }
//            }
            // tuve que comentar esta funcion, porque los nodos adyacentes
            // no son solo los que despegan despues de ahora, sino que pueden despegar manhiana
//            if(horaPartida > horaActual){
            // Si tiene que ser el aeropuerto 1, porque el nodo adyacente es el aeropuerto a donde se dirige
            // SI ESTA CALCULANDO LAS HORAS CORRECTAMENTE, ENTONCES QUE ESTA PASANDO
            // Y PORQUE SIGUE ESCOGIENDO A SKBO COMO SEGUNDO AEROPUERTO
            if(arco.getAeropuerto1().getCodigo() == currentNode.getCodigo()){
                // y aqui el nodo adyacente si tendria que ser aeropuerto de destino

                // por ahora esta funcion esta buena, pero me parece que el costo esta mal calculado
//                checkNode(arco.getAeropuerto2(), currentNode, arco.obtenerDuracionVuelo().toMinutesPart());
                int duracionVuelo = (int) arco.obtenerDuracionVuelo().toMinutes(); // duracion del vuelo en minutos
                int costo;
                if(horaLlegada > horaActual){
                    costo = horaLlegada  - horaActual + duracionVuelo;
                }else{
                    costo = horaActual + 24 * 60 - horaLlegada + duracionVuelo;
                }
                checkNode(arco.getAeropuerto2(), currentNode, costo);
            }
//            }
        }

    }

    // este metodo nos permite saber si el nodo esta en la lista abierta o cerrada
    // si no esta en la lista abierta lo agrega y si ya esta entonces actualizamos f = g(n) + h(n).
    private void checkNode(Aeropuerto adjacentNode, Aeropuerto currentNode, int costo){
        System.out.println("El costo es: " + costo);
        System.out.println("El aeropuerto adyacente es: " + adjacentNode.getCodigo());
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
