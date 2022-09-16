package com.bb.envifastservice.algo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LectorAeropuertos {
    private ArrayList<Aeropuerto> aeropuertos; // array list

    public LectorAeropuertos(){
        System.out.println("Entro al constructor");
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
                        Aeropuerto a =  new Aeropuerto();
                        // ahora ya nos aseguramos que esta leyendo bien los datos
                        a.setId(Integer.parseInt(parts[0]));
                        a.setCodigo(parts[1]);
                        if(i==9 || i==11){
                            a.getCiudad().setNombre(parts[2] + " " + parts[3]);
                            a.getCiudad().setPais(parts[4]);
                            a.getCiudad().setAbreviacion(parts[5]);
                        } else if(i==10){
                            a.getCiudad().setNombre(parts[2] + " " + parts[3] + " " + parts[4]);
                            a.getCiudad().setPais(parts[5]);
                            a.getCiudad().setAbreviacion(parts[6]);
                        } else if(i==34 || i==43){
                            a.getCiudad().setNombre(parts[2]);
                            a.getCiudad().setPais(parts[3] + " " + parts[4]);
                            a.getCiudad().setAbreviacion(parts[5]);
                        }else {
                            a.getCiudad().setNombre(parts[2]);
                            a.getCiudad().setPais(parts[3]);
                            a.getCiudad().setAbreviacion(parts[4]);
                        }
                        if( i<= 15) {
                            a.getCiudad().setContinente("America del Sur");
                            a.setCapacidad(850);
                        }
                        else {
                            a.getCiudad().setContinente("Europa");
                            a.setCapacidad(900);
                        }
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
