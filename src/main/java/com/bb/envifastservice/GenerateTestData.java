package com.bb.envifastservice;

import com.bb.envifastservice.algo.Aeropuerto;
import com.bb.envifastservice.algo.PlanVuelo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GenerateTestData {

    public GenerateTestData() {
    }

    public void GenerateData(String ruta, ArrayList<Aeropuerto> aeropuertos, int filas, int maxPaq){
        Random rand = new Random();
        int cont=0;
        int cantPaq;
        String linea = null;
        FileWriter fw = null;
        try {
            fw = new FileWriter(ruta);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while(true) {
            if (cont==filas) break;
            int randomIndex1 = rand.nextInt(aeropuertos.size());
            int randomIndex2 = rand.nextInt(aeropuertos.size());
            cantPaq = rand.nextInt(maxPaq-1)+1;
            if(randomIndex1!=randomIndex2){
                //System.out.println("Envio " + cont);
                //System.out.println(aeropuertos.get(randomIndex1).getCodigo() + "-" + aeropuertos.get(randomIndex2).getCodigo() + "-" + cantPaq);
                linea = aeropuertos.get(randomIndex1).getCodigo() + "-" + aeropuertos.get(randomIndex2).getCodigo() + "-" + cantPaq;
                try {
                    fw.write(linea + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                cont++;
            }
        }
        try {
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
