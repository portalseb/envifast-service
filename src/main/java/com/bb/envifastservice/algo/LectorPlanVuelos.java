package com.bb.envifastservice.algo;

import java.io.*;
import java.util.ArrayList;

public class LectorPlanVuelos {
    private ArrayList<PlanVuelo> vuelos;

    public LectorPlanVuelos(){
        this.vuelos = new ArrayList<PlanVuelo>();
    }

    public void Leer(String ruta) throws FileNotFoundException {
        File archivo = new File (ruta);
        BufferedReader br = new BufferedReader(new FileReader(archivo));

        try {
            String data;
            int i = 0; // estableceremos como contador para no tomar en cuanta ciertas lineas
            int cont = 0; // contador para llenar el arreglo de aeropuertos
            while (i < 60) {
                data = br.readLine();
//                    String data2 = data.replaceAll("\\s+", " ");
                String[] parts = data.split("-");
                if(parts.length >= 2){
                    PlanVuelo a = new PlanVuelo();
                    Aeropuerto aeroOrigen = new Aeropuerto();
                    Aeropuerto aeroDestino = new Aeropuerto();

                    aeroOrigen.setCodigo(parts[0]);
                    aeroDestino.setCodigo(parts[1]);
                    // ahora ya nos aseguramos que esta leyendo bien los datos
                    a.setHoraInicio(parts[2]);
                    a.setHoraFin(parts[3]);
                    a.setOrigen(aeroOrigen);
                    a.setDestino(aeroDestino);
                    this.vuelos.add(a);
                }
                i++;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    public ArrayList<PlanVuelo> getVuelos() {
        return vuelos;
    }
}
