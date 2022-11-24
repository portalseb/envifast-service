package com.bb.envifastservice.algo;

import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;

public class LectorEnviosCorto {
    private LinkedList<String> codigos;
    private LinkedList<LocalDateTime> fechasEnvio;
    private LinkedList<Aeropuerto> origenes;
    private LinkedList<Aeropuerto> destinos;
    private LinkedList<Aeropuerto> aeropuertos;
    private LinkedList<Integer> cantPaquetes;
    private LocalDate fechaDesde;
    public LectorEnviosCorto(LinkedList<Aeropuerto> aeropuertos) {
        this.origenes = new LinkedList<>();
        this.destinos = new LinkedList<>();
        this.aeropuertos = aeropuertos;
        this.cantPaquetes = new LinkedList<>();
        this.codigos = new LinkedList<>();
        this.fechasEnvio = new LinkedList<>();
    }

    public LocalDate getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(LocalDate fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public LinkedList<Aeropuerto> getOrigenes() {
        return origenes;
    }

    public void setOrigenes(LinkedList<Aeropuerto> origenes) {
        this.origenes = origenes;
    }

    public LinkedList<Aeropuerto> getDestinos() {
        return destinos;
    }

    public void setDestinos(LinkedList<Aeropuerto> destinos) {
        this.destinos = destinos;
    }

    public LinkedList<Aeropuerto> getAeropuertos() {
        return aeropuertos;
    }

    public void setAeropuertos(LinkedList<Aeropuerto> aeropuertos) {
        this.aeropuertos = aeropuertos;
    }

    public LinkedList<Integer> getCantPaquetes() {
        return cantPaquetes;
    }

    public void setCantPaquetes(LinkedList<Integer> cantPaquetes) {
        this.cantPaquetes = cantPaquetes;
    }

    public LinkedList<String> getCodigos() {
        return codigos;
    }

    public void setCodigos(LinkedList<String> codigos) {
        this.codigos = codigos;
    }

    public LinkedList<LocalDateTime> getFechasEnvio() {
        return fechasEnvio;
    }

    public void setFechasEnvio(LinkedList<LocalDateTime> fechasEnvio) {
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

    public void LeerActualizado(String ruta, String fecha, String timeInf, String timeSup) throws IOException {
        String[] codes  = {"SKBO",
                "SEQM",
                "SVMI",
                "SBBR",
                "SPIM",
                "SLLP",
                "SCEL",
                "SABE",
                "SGAS",
                "SUAA",
                "LATI",
                "EDDI",
                "LOWW",
                "EBCI",
                "UMMS",
                "LBSF",
                "LKPR",
                "LDZA",
                "EKCH",
                "LZIB",
                "LJLJ",
                "LEMD",
                "EETN",
                "EFHK",
                "LFPG",
                "LGAV",
                "EHAM",
                "LHBP",
                "EIDW",
                "BIKF",
                "LIRA",
                "EVRA",
                "ELLX",
                "LMML",
                "ENGM",
                "EPMO",
                "LPPT",
                "EGLL",
                "ESKN",
                "LSZB"};
        for(int i=0;i<40;i++) {
            ClassPathResource classPathResource = new ClassPathResource("pack_enviado_"+ codes[i] +".txt");
            //File archivo = new File(ruta + "pack_enviado_"+ codes[i] +".txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(classPathResource.getInputStream(), StandardCharsets.UTF_8));
            try {
                String data;
                while (true) {
                    data = br.readLine();
                    if (data == null) break;
                    String[] parts = data.split("-");
                    if (parts.length >= 2) {
                        Integer fechaIni = Integer.parseInt(parts[1]);
                        String[] horaIni = parts[2].split(":");
                        LocalDateTime fechaHoraIni = LocalDateTime.of(LocalDate.of(fechaIni / 10000, (fechaIni % 10000) / 100, (fechaIni % 10000) % 100), LocalTime.of(Integer.parseInt(horaIni[0]), Integer.parseInt(horaIni[1])));
                        if(LocalTime.parse(timeSup).isAfter(LocalTime.parse(timeInf))) {
                            if ((LocalDateTime.of(LocalDate.parse(fecha), LocalTime.parse(timeInf)).isBefore(fechaHoraIni) && LocalDateTime.of(LocalDate.parse(fecha), LocalTime.parse(timeSup)).isAfter(fechaHoraIni))
                                    || LocalDateTime.of(LocalDate.parse(fecha), LocalTime.parse(timeInf)).isEqual(fechaHoraIni)) {
                                int j_aeropuerto1 = 0, j_aeropuerto2 = 0;
                                String origen = parts[0].substring(0, 4);
                                String[] destPaq = parts[3].split(":");
                                for (int j = 0; j < this.aeropuertos.size(); j++) {
                                    if (origen.equals(this.aeropuertos.get(j).getCodigo())) j_aeropuerto1 = j;
                                    if (destPaq[0].equals(this.aeropuertos.get(j).getCodigo())) j_aeropuerto2 = j;
                                }
                                if (!(this.aeropuertos.get(j_aeropuerto1).getCiudad().getContinente().equals("EUROPA")
                                        && this.aeropuertos.get(j_aeropuerto2).getCiudad().getContinente().equals("AMERICA DEL SUR"))) {
                                    codigos.add(parts[0]);
                                    fechasEnvio.add(fechaHoraIni);
                                    origenes.add(this.aeropuertos.get(j_aeropuerto1));
                                    destinos.add(this.aeropuertos.get(j_aeropuerto2));
                                    cantPaquetes.add(Integer.parseInt(destPaq[1]));
                                }
                            }
                            if (fechaHoraIni.toLocalDate().isAfter(LocalDate.parse(fecha)))
                                break;
                        }
                        else{
                            if ((LocalDateTime.of(LocalDate.parse(fecha), LocalTime.parse(timeInf)).isBefore(fechaHoraIni) && LocalDateTime.of(LocalDate.parse(fecha).plusDays(1), LocalTime.parse(timeSup)).isAfter(fechaHoraIni))
                                    || LocalDateTime.of(LocalDate.parse(fecha), LocalTime.parse(timeInf)).isEqual(fechaHoraIni)) {
                                int j_aeropuerto1 = 0, j_aeropuerto2 = 0;
                                String origen = parts[0].substring(0, 4);
                                String[] destPaq = parts[3].split(":");
                                for (int j = 0; j < this.aeropuertos.size(); j++) {
                                    if (origen.equals(this.aeropuertos.get(j).getCodigo())) j_aeropuerto1 = j;
                                    if (destPaq[0].equals(this.aeropuertos.get(j).getCodigo())) j_aeropuerto2 = j;
                                }
                                if (!(this.aeropuertos.get(j_aeropuerto1).getCiudad().getContinente().equals("EUROPA")
                                        && this.aeropuertos.get(j_aeropuerto2).getCiudad().getContinente().equals("AMERICA DEL SUR"))) {
                                    codigos.add(parts[0]);
                                    fechasEnvio.add(fechaHoraIni);
                                    origenes.add(this.aeropuertos.get(j_aeropuerto1));
                                    destinos.add(this.aeropuertos.get(j_aeropuerto2));
                                    cantPaquetes.add(Integer.parseInt(destPaq[1]));
                                }
                            }
                            if (fechaHoraIni.toLocalDate().isAfter(LocalDate.parse(fecha).plusDays(1)))
                                break;
                        }
                    }
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

//        archivo = new File (ruta + "pack_enviado_EBCI.txt");
//        archivo = new File (ruta + "pack_enviado_EDDI.txt");
//        archivo = new File (ruta + "pack_enviado_EETN.txt");
//        archivo = new File (ruta + "pack_enviado_EFHK.txt");
//        archivo = new File (ruta + "pack_enviado_EGLL.txt");
//        archivo = new File (ruta + "pack_enviado_EHAM.txt");
//        archivo = new File (ruta + "pack_enviado_EIDW.txt");
//        archivo = new File (ruta + "pack_enviado_EKCH.txt");
//        archivo = new File (ruta + "pack_enviado_ELLX.txt");
//        archivo = new File (ruta + "pack_enviado_ENGM.txt");
//        archivo = new File (ruta + "pack_enviado_EPMO.txt");
//        archivo = new File (ruta + "pack_enviado_ESKN.txt");
//        archivo = new File (ruta + "pack_enviado_EVRA.txt");
//        archivo = new File (ruta + "pack_enviado_LATI.txt");
//        archivo = new File (ruta + "pack_enviado_LBSF.txt");
//        archivo = new File (ruta + "pack_enviado_LDZA.txt");
//        archivo = new File (ruta + "pack_enviado_LEMD.txt");
//        archivo = new File (ruta + "pack_enviado_LFPG.txt");
//        archivo = new File (ruta + "pack_enviado_LGAV.txt");
//        archivo = new File (ruta + "pack_enviado_LHBP.txt");
//        archivo = new File (ruta + "pack_enviado_LIRA.txt");
//        archivo = new File (ruta + "pack_enviado_LJLJ.txt");
//        archivo = new File (ruta + "pack_enviado_LKPR.txt");
//        archivo = new File (ruta + "pack_enviado_LMML.txt");
//        archivo = new File (ruta + "pack_enviado_LOWW.txt");
//        archivo = new File (ruta + "pack_enviado_LPPT.txt");
//        archivo = new File (ruta + "pack_enviado_LSZB.txt");
//        archivo = new File (ruta + "pack_enviado_LZIB.txt");
//
//        archivo = new File (ruta + "pack_enviado_SABE.txt");
//        archivo = new File (ruta + "pack_enviado_SBBR.txt");
//        archivo = new File (ruta + "pack_enviado_SCEL.txt");
//        archivo = new File (ruta + "pack_enviado_SQEM.txt");
//        archivo = new File (ruta + "pack_enviado_SGAS.txt");
//        archivo = new File (ruta + "pack_enviado_SKBO.txt");
//        archivo = new File (ruta + "pack_enviado_SLLP.txt");
//        archivo = new File (ruta + "pack_enviado_SPIM.txt");
//        archivo = new File (ruta + "pack_enviado_SUAA.txt");
//        archivo = new File (ruta + "pack_enviado_SVMI.txt");
//
//        archivo = new File (ruta + "pack_enviado_UMMS.txt");


    }

}
