package com.bb.envifastservice.algo;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class LectorEnviosCorto {
    private ArrayList<String> codigos;
    private ArrayList<LocalDateTime> fechasEnvio;
    private ArrayList<Aeropuerto> origenes;
    private ArrayList<Aeropuerto> destinos;
    private ArrayList<Aeropuerto> aeropuertos;
    private ArrayList<Integer> cantPaquetes;
    private LocalDate fechaDesde;
    public LectorEnviosCorto(ArrayList<Aeropuerto> aeropuertos) {
        this.origenes = new ArrayList<>();
        this.destinos = new ArrayList<>();
        this.aeropuertos = aeropuertos;
        this.cantPaquetes = new ArrayList<>();
        this.codigos = new ArrayList<>();
        this.fechasEnvio = new ArrayList<>();
    }

    public LocalDate getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(LocalDate fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public ArrayList<Aeropuerto> getOrigenes() {
        return origenes;
    }

    public void setOrigenes(ArrayList<Aeropuerto> origenes) {
        this.origenes = origenes;
    }

    public ArrayList<Aeropuerto> getDestinos() {
        return destinos;
    }

    public void setDestinos(ArrayList<Aeropuerto> destinos) {
        this.destinos = destinos;
    }

    public ArrayList<Aeropuerto> getAeropuertos() {
        return aeropuertos;
    }

    public void setAeropuertos(ArrayList<Aeropuerto> aeropuertos) {
        this.aeropuertos = aeropuertos;
    }

    public ArrayList<Integer> getCantPaquetes() {
        return cantPaquetes;
    }

    public void setCantPaquetes(ArrayList<Integer> cantPaquetes) {
        this.cantPaquetes = cantPaquetes;
    }

    public ArrayList<String> getCodigos() {
        return codigos;
    }

    public void setCodigos(ArrayList<String> codigos) {
        this.codigos = codigos;
    }

    public ArrayList<LocalDateTime> getFechasEnvio() {
        return fechasEnvio;
    }

    public void setFechasEnvio(ArrayList<LocalDateTime> fechasEnvio) {
        this.fechasEnvio = fechasEnvio;
    }

    public void Leer(String ruta)  throws FileNotFoundException {
        File archivo = new File (ruta);
        BufferedReader br = new BufferedReader(new FileReader(archivo));
        try {
            String data;
            int i = 0; // estableceremos como contador para no tomar en cuanta ciertas lineas
            int cont = 0; // contador para llenar el arreglo de aeropuertos
            int filas=0, sigDia=0;
            while (true) {
                data = br.readLine();
                if(data == null) break;
                String[] parts = data.split("-");
                if(parts.length >= 2){
                    int j_aeropuerto1 = 0, j_aeropuerto2 = 0;

                    if(filas==20){
                        sigDia++;
                        filas=0;
                    }

                    //Codigo
                    codigos.add(parts[0]);

                    //Fecha
                    //Integer fecha = Integer.parseInt(parts[1]);
                    String[] horaMin = parts[1].split(":");
                    fechasEnvio.add(LocalDateTime.of(this.fechaDesde.plusDays(sigDia), LocalTime.of(Integer.parseInt(horaMin[0]),Integer.parseInt(horaMin[1]))));

                    // Tenemos que encontrar el aeropuerto de origen y de destino para que funcione
                    for (int j = 0; j < this.aeropuertos.size(); j++) {
                        if(parts[2].equals(this.aeropuertos.get(j).getCodigo())){
                            j_aeropuerto1 = j;
                        }
                        if(parts[3].equals(this.aeropuertos.get(j).getCodigo())){
                            j_aeropuerto2 = j;
                        }
                    }
                    origenes.add(this.aeropuertos.get(j_aeropuerto1));
                    destinos.add(this.aeropuertos.get(j_aeropuerto2));
                    cantPaquetes.add(Integer.parseInt(parts[4]));
                    filas++;
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

}
