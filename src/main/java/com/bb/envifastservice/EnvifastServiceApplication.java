package com.bb.envifastservice;

import com.bb.envifastservice.algo.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
public class EnvifastServiceApplication {

    public static void main(String[] args) throws IOException {
//        SpringApplication.run(EnvifastServiceApplication.class, args);
        CalculadorDeCostosCapacidades calculadora = new CalculadorDeCostosCapacidades();
        calculadora.leerAeropuertos();
        calculadora.imprimirAeropuertos();
//        calculadora.leerPlanesVuelos();
//        calculadora.calcularCostosMinimos();
//        System.out.println("D");
//        calculadora.imprimirCostosMinimos();
//        System.out.println("E");
    }


}
