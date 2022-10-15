package com.bb.envifastservice.algo;

import java.io.*;
import java.util.ArrayList;

public class LectorArcoAeropuerto {

    private ArrayList<ArcoAeropuerto> arcos;
    private ArrayList<Aeropuerto> aeropuertos;

    public LectorArcoAeropuerto(){
        this.arcos = new ArrayList<>();
    }

    public LectorArcoAeropuerto(ArrayList<Aeropuerto> aeropuertos) {
        this.arcos = new ArrayList<>();
        this.aeropuertos = aeropuertos;
    }

    public void Leer(String ruta)  throws FileNotFoundException {
        File archivo = new File (ruta);
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        Integer idArco=1;
        try {
            String data;
            int i = 0; // estableceremos como contador para no tomar en cuanta ciertas lineas
            int cont = 0; // contador para llenar el arreglo de aeropuertos
            while (true) {
                data = br.readLine();
                if(data == null) break;
                String[] parts = data.split("-");
                if(parts.length >= 2){
                    String nombreVuelo = "Vuelo_Rutina";
                    int j_aeropuerto1 = 0, j_aeropuerto2 = 0;
                    // Tenemos que encontrar el aeropuerto de origen y de destino para que funcione
                    for (int j = 0; j < this.aeropuertos.size(); j++) {
                        if(parts[0].equals(this.aeropuertos.get(j).getCodigo())){
                            j_aeropuerto1 = j;
//                            pv.setOrigen(this.aeropuertos.get(j));
                        }
                        if(parts[1].equals(this.aeropuertos.get(j).getCodigo())){
                            j_aeropuerto2 = j;
//                            pv.setDestino(this.aeropuertos.get(j));
                        }
                    }
                    // ahora ya nos aseguramos que esta leyendo bien los datos
                    ArcoAeropuerto pv = new ArcoAeropuerto(idArco,nombreVuelo, this.aeropuertos.get(j_aeropuerto1),
                            this.aeropuertos.get(j_aeropuerto2), parts[2], parts[3]);
//                    pv.setHoraInicio(parts[2]);
//                    pv.setHoraFin(parts[3]);
//                    this.vuelos.add(pv);
                    this.arcos.add(pv);
                    idArco++;
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
    public ArrayList<ArcoAeropuerto> getArcos() {
        return arcos;
    }

    public ArrayList<Aeropuerto> getAeropuertos() {
        return aeropuertos;
    }

    public void setArcos(ArrayList<ArcoAeropuerto> arcos) {
        this.arcos = arcos;
    }

    public void setAeropuertos(ArrayList<Aeropuerto> aeropuertos) {
        this.aeropuertos = aeropuertos;
    }

    @Override
    public String toString() {
        return "LectorPlanVuelos{" +
                "vuelos=" + arcos +
                ", aeropuertos=" + aeropuertos +
                '}';
    }
}
