package com.bb.envifastservice;

import com.bb.envifastservice.algo.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

@SpringBootApplication
public class EnvifastServiceApplication {

    public static void main(String[] args) throws IOException {
//        SpringApplication.run(EnvifastServiceApplication.class, args);
//        CalculadorDeCostosCapacidades calculadora = new CalculadorDeCostosCapacidades();
//        calculadora.leerAeropuertos();
//        calculadora.imprimirAeropuertos();
//        calculadora.leerPlanesVuelos();
//        calculadora.imprimirPlanesVuelo();
//        calculadora.calcularCostosMinimos();
//        System.out.println("D");
//        calculadora.imprimirCostosMinimos();
//        System.out.println("E");
        ArrayList<PlanVuelo> planesVuelos;
        ArrayList<Aeropuerto> aeropuertos;
        ArrayList<ArcoAeropuerto> arcoAeropuertos = new ArrayList<ArcoAeropuerto>();
        LectorAeropuertos lectorAeropuertos = new LectorAeropuertos();
        LectorPlanVuelos lectorPlanVuelos = new LectorPlanVuelos();

        lectorAeropuertos.Leer("D:\\\\DOCUMENTOS\\\\FACI\\2022-2\\DP1\\c.inf226.22-2.lista.aeropuertos.v01.txt");
        aeropuertos = lectorAeropuertos.getAeropuertos();
        lectorPlanVuelos.setAeropuertos(aeropuertos);
        lectorPlanVuelos.Leer("D:\\\\DOCUMENTOS\\\\FACI\\2022-2\\DP1\\c.inf226.22-2.planes_vuelo.v01.txt");
        planesVuelos = lectorPlanVuelos.getVuelos();
        for (Aeropuerto a: aeropuertos) {
            System.out.println(a.toString());
        }
        for (PlanVuelo pv: planesVuelos){
            System.out.println(pv.toString());
        }

        ////Generar data de envios:
        //GenerateTestData test = new GenerateTestData();
        //test.GenerateData("C:\\Users\\Fernando\\Desktop\\fernando\\Archivos Pucp\\ciclo 9\\dp1\\algoritmos\\data.prueba.01.txt",aeropuertos,100,6);


    }
}
