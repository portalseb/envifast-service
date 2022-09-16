package com.bb.envifastservice.algo.astar;

import com.bb.envifastservice.algo.Aeropuerto;

import java.util.PriorityQueue;
import java.util.Queue;

public class BestFirstGraph {
    private PriorityQueue<Aeropuerto> colaPrioridad = new PriorityQueue<Aeropuerto>();
    private Queue<Aeropuerto> nodosVisitados ;
}
