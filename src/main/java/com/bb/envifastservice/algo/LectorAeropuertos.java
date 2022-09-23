package com.bb.envifastservice.algo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class LectorAeropuertos {
    private ArrayList<Aeropuerto> aeropuertos; // array list

    public LectorAeropuertos(){
        this.aeropuertos = new ArrayList<Aeropuerto>();
//        this.aeropuertos = new Aeropuerto[40]; // en total tenemos 40 aero puertos
    }

    public ArrayList<Aeropuerto> getAeropuertos() {
        return aeropuertos;
    }
    public void Leer(String ruta) throws IOException {

//            this.aeropuertos = new Aeropuerto[40];
        File archivo = new File (ruta);
        BufferedReader br = new BufferedReader(new FileReader(archivo));

        try {
            String data;
            int i = 0; // estableceremos como contador para no tomar en cuanta ciertas lineas
            int cont = 0; // contador para llenar el arreglo de aeropuertos
            while ((data = br.readLine()) != null) {
                if(i != 0 && i != 1 && i != 2 && i != 3 && i != 14 && i != 15 && i != 46){
                    String data2 = data.replaceAll("\\s+", " ");
                    String[] parts = data2.split(" ");
                    if(parts.length >= 2){
                        Integer id = Integer.parseInt(parts[0]);
                        String codigo = parts[1];


                        // ahora ya nos aseguramos que esta leyendo bien los datos
                        String ciudadNombre;
                        String ciudadPais;
                        String ciudadAbreviacion;
                        String continente;
                        Integer capacidad;
                        if(i==9 || i==11){
                            ciudadNombre = parts[2] + " " + parts[3];
                            ciudadPais = parts[4];
                            ciudadAbreviacion = parts[5];
                        } else if(i==10){
                            ciudadNombre =parts[2] + " " + parts[3] + " " + parts[4];
                            ciudadPais =parts[5];
                            ciudadAbreviacion = parts[6];
                        } else if(i==34 || i==43){
                            ciudadNombre = parts[2];
                            ciudadPais = parts[3] + " " + parts[4];
                            ciudadAbreviacion =parts[5];
                        }else {
                            ciudadNombre = parts[2];
                            ciudadPais = parts[3];
                            ciudadAbreviacion =parts[4];
                        }
                        if( i<= 15) {
                            continente = "America del Sur";
                            capacidad = 850;
                        }
                        else {
                            continente = "Europa";
                            capacidad = 900;
                        }
                        Aeropuerto a;

                        a = new Aeropuerto(id, codigo,ciudadNombre, ciudadAbreviacion, ciudadPais
                        , codigo,  TimeZone.getTimeZone(ciudadNombre).toString(), continente);
                        this.aeropuertos.add(a);
                    }
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
