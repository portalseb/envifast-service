package com.bb.envifastservice;

import com.bb.envifastservice.algo.Aeropuerto;
import com.bb.envifastservice.algo.LectorAeropuertos;
import com.bb.envifastservice.algo.LectorPlanVuelos;
import com.bb.envifastservice.algo.PlanVuelo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
public class EnvifastServiceApplication {

    public static void main(String[] args) throws IOException {
//        SpringApplication.run(EnvifastServiceApplication.class, args);
        // leemos los archivos que nos piden

        String ruta = "D:\\Documentos\\Cursos\\Noveno ciclo\\DP1\\Algoritmos\\Datos_entrada\\c.inf226.22-2.lista.aeropuertos.v01.txt";
        LectorAeropuertos lector = new LectorAeropuertos();
        lector.Leer(ruta);
        ArrayList<Aeropuerto> aeropuertos = lector.getAeropuertos();
        System.out.println(aeropuertos.size());

        String ruta2 = "D:\\Documentos\\Cursos\\Noveno ciclo\\DP1\\Algoritmos\\Datos_entrada\\c.inf226.22-2.planes_vuelo.v01.txt";
        LectorPlanVuelos vuelos = new LectorPlanVuelos();
        vuelos.Leer(ruta2);
        ArrayList<PlanVuelo> planVuelos = vuelos.getVuelos();
        System.out.println(planVuelos.size());

    }


}
