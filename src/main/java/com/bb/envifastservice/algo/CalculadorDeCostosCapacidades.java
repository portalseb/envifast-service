package com.bb.envifastservice.algo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class CalculadorDeCostosCapacidades {

//    private ArrayList<PlanVuelo> planesVuelos;
//    private ArrayList<Aeropuerto> aeropuertos;
//
//    private int costosMinimos[][][];
//
//    public CalculadorDeCostosCapacidades(){
//        this.costosMinimos = new int[40][40][1]; // matriz de costos minimos
//    }
//    public void leerPlanesVuelos() throws FileNotFoundException {
//        LectorPlanVuelos lector = new LectorPlanVuelos(this.aeropuertos);
//        lector.Leer("D:\\Documentos\\Cursos\\Noveno ciclo\\DP1\\Algoritmos\\Datos_entrada\\c.inf226.22-2.planes_vuelo.v01.txt");
//        this.planesVuelos = lector.getVuelos();
//    }
//
//    public void leerAeropuertos() throws IOException {
//        LectorAeropuertos lector = new LectorAeropuertos();
//        lector.Leer("D:\\Documentos\\Cursos\\Noveno ciclo\\DP1\\Algoritmos\\Datos_entrada\\c.inf226.22-2.lista.aeropuertos.v01.txt");
//        this.aeropuertos = lector.getAeropuertos();
//    }
//
//    public void calcularCostosMinimos(){
//
//        // inicializamos el arreglo. Los aeropuertso estan ordenados segun
//        // los archivos de datos
//        for (int i = 0; i < 40; i++) {
//            for (int j = 0; j < 40; j++) {
//                this.costosMinimos[i][j][0] = 900000;// inicialmente son un numero muy grande
//            }
//        }
//
//        for (int i = 0; i < this.planesVuelos.size(); i++) {
//            int costoActual = this.planesVuelos.get(i).calcularCosto();
//            if(costoActual < this.costosMinimos[this.planesVuelos.get(i).getOrigen().getId() - 1][this.planesVuelos.get(i).getDestino().getId() - 1][0]){
//                this.costosMinimos[this.planesVuelos.get(i).getOrigen().getId() - 1][this.planesVuelos.get(i).getDestino().getId() - 1][0] = costoActual;
//            }
//        }
//    }
//
//    public void imprimirCostosMinimos(){
//        for(int i = 0; i < 40; i++){
//            System.out.println("Costos minimos del aeropuerto " + this.aeropuertos.get(i).getCodigo()
//                    + " ubicado en la ciudad "  + this.aeropuertos.get(i).getCiudad());
//            for (int j = 0; j < 40; j++) {
//                System.out.println("Hacia el aeropuerto: " + this.aeropuertos.get(j).getCodigo() +
//                        " = " + this.costosMinimos[i][j][0]);
//            }
//        }
//    }
}
