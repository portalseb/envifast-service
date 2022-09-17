package com.bb.envifastservice.algo;

import java.io.*;
import java.util.ArrayList;

public class LectorPlanVuelos {
    private ArrayList<PlanVuelo> vuelos;
    private ArrayList<Aeropuerto> aeropuertos;

    public LectorPlanVuelos(){
        this.vuelos = new ArrayList<PlanVuelo>();
    }

    public void setVuelos(ArrayList<PlanVuelo> vuelos) {
        this.vuelos = vuelos;
    }

    public ArrayList<Aeropuerto> getAeropuertos() {
        return aeropuertos;
    }

    public void setAeropuertos(ArrayList<Aeropuerto> aeropuertos) {
        this.aeropuertos = aeropuertos;
    }

    public ArrayList<PlanVuelo> getVuelos() {
        return vuelos;
    }

    public LectorPlanVuelos(ArrayList<Aeropuerto> airlines){
        this.vuelos = new ArrayList<PlanVuelo>();
        this.aeropuertos = airlines;
    }
    public void Leer(String ruta) throws FileNotFoundException {
        File archivo = new File (ruta);
        BufferedReader br = new BufferedReader(new FileReader(archivo));

        try {
            String data;
            int i = 0; // estableceremos como contador para no tomar en cuanta ciertas lineas
            int cont = 0; // contador para llenar el arreglo de aeropuertos
            while (true) {
                data = br.readLine();
                if(data == null) break;
                String[] parts = data.split("-");
                if(parts.length >= 2){
                    PlanVuelo pv = new PlanVuelo();
                    // Tenemos que encontrar el aeropuerto de origen y de destino para que funcione
                    for (int j = 0; j < this.aeropuertos.size(); j++) {
                        if(parts[0].equals(this.aeropuertos.get(j).getCodigo())){
                            pv.setOrigen(this.aeropuertos.get(j));
                        }
                        if(parts[1].equals(this.aeropuertos.get(j).getCodigo())){
                            pv.setDestino(this.aeropuertos.get(j));
                        }
                    }
                    // ahora ya nos aseguramos que esta leyendo bien los datos
                    pv.setHoraInicio(parts[2]);
                    pv.setHoraFin(parts[3]);
                    this.vuelos.add(pv);
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

    @Override
    public String toString() {
        return "LectorPlanVuelos{" +
                "vuelos=" + vuelos +
                ", aeropuertos=" + aeropuertos +
                '}';
    }
}
