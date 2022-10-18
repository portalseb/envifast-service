package com.bb.envifastservice.algo.antColony;

import com.bb.envifastservice.algo.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Aco {
    private double solucionCosto;
    private ArrayList<ArcoAeropuerto> solucionCamino;
    public Aco() {
        solucionCosto=999999;
        solucionCamino=new ArrayList<ArcoAeropuerto>();
    }
    public double getSolucionCosto() {
        return solucionCosto;
    }
    public ArrayList<ArcoAeropuerto> getSolucionCamino() {
        return solucionCamino;
    }
    public void activarHormigas(AntSide ambiente) {
        int contProb1=0, contProb2=0;
        while(true) {
            // Inicializar hormiga 1
            Ant hormiga1 = new Ant(ambiente);
            hormiga1.explorar();

            // Inicializar hormiga 2
            Ant hormiga2 = new Ant(ambiente);
            hormiga2.explorar();

            // Actualizar rastro de feromonas
            if(hormiga1.isEncontroCamino() && hormiga2.isEncontroCamino())
            {
                ambiente.actualizarFeromonasEnElCamino(hormiga1, hormiga2);
                if (solucionCosto > hormiga1.getCostoTotal() || solucionCosto > hormiga2.getCostoTotal()) {
                    if (hormiga1.getCostoTotal() < hormiga2.getCostoTotal()) {
                        solucionCamino = new ArrayList<ArcoAeropuerto>();
                        for (int j = 0; j < hormiga1.getCaminoIndices().size(); j++) {
                            solucionCamino.add(ambiente.getCaminos().get(hormiga1.getCaminoIndices().get(j)));
                        }
                        solucionCosto = hormiga1.getCostoTotal();
                    } else {
                        solucionCamino = new ArrayList<ArcoAeropuerto>();
                        for (int j = 0; j < hormiga2.getCaminoIndices().size(); j++) {
                            solucionCamino.add(ambiente.getCaminos().get(hormiga2.getCaminoIndices().get(j)));
                        }
                        solucionCosto = hormiga2.getCostoTotal();
                    }
                }
                contProb1 = 0;
                for (int p = 0; p < hormiga1.getCaminoProbabilidades().size(); p++)
                    if (hormiga1.getCaminoProbabilidades().get(p) >= 0.9)
                        contProb1++;
                contProb2 = 0;
                for (int p = 0; p < hormiga2.getCaminoProbabilidades().size(); p++)
                    if (hormiga2.getCaminoProbabilidades().get(p) >= 0.9)
                        contProb2++;
                if (contProb1 == hormiga1.getCaminoProbabilidades().size() || contProb2 == hormiga2.getCaminoProbabilidades().size()) {
                    System.out.println("Se llego al limite");
                    break;
                }
            }
        }
    }

    //Main para llamar al algoritmo
    public static void main(String args[]) throws IOException {
        ArrayList<ArcoAeropuerto> arcos;
        ArrayList<Aeropuerto> aeropuertos;

        /* Leemos los aeropuertos y los planes de vuelo */
        LectorAeropuertos lectorAeropuertos = new LectorAeropuertos();
        lectorAeropuertos.Leer("C:\\Users\\Fernando\\Desktop\\fernando\\Archivos Pucp\\ciclo 9\\dp1\\algoritmos\\c.inf226.22-2.lista.aeropuertos.v01.txt");
//        lectorAeropuertos.Leer("D:\\Documentos\\Cursos\\Noveno ciclo\\DP1\\Algoritmos\\Datos_entrada\\c.inf226.22-2.lista.aeropuertos.v01.txt");
        aeropuertos = lectorAeropuertos.getAeropuertos();

        LectorArcoAeropuerto lectorArcos = new LectorArcoAeropuerto(aeropuertos);
        lectorArcos.Leer("C:\\Users\\Fernando\\Desktop\\fernando\\Archivos Pucp\\ciclo 9\\dp1\\algoritmos\\c.inf226.22-2.planes_vuelo.v02.txt");
//        lectorArcos.Leer("D:\\Documentos\\Cursos\\Noveno ciclo\\DP1\\Algoritmos\\Datos_entrada\\c.inf226.22-2.planes_vuelo.v01.txt");
        arcos = lectorArcos.getArcos();


        double horaPartida, horaLlegada;
        int total=arcos.size();

        //Se crea los arcos para los siguientes 3 dias:
        //Vuelos del dia 1 y dia1-dia2
        for(int i =0;i<arcos.size();i++){
            ArcoAeropuerto arco = arcos.get(i);
            horaPartida = (double) arco.getHoraPartida().getHour()*60 + arco.getHoraPartida().getMinute();
            horaLlegada = (double) arco.getHoraLlegada().getHour()*60 + arco.getHoraLlegada().getMinute();

            if(horaPartida < horaLlegada){
                arco.setDiaPartida(LocalDate.parse("2022-10-17"));
                arco.setDiaLLegada(LocalDate.parse("2022-10-17"));
            }
            else{
                arco.setDiaPartida(LocalDate.parse("2022-10-17"));
                arco.setDiaLLegada(LocalDate.parse("2022-10-18"));
            }
            arcos.set(i,arco);
        }

        //Vuelos del dia 2 y dia2-dia3
        for(int i =0;i<total;i++){
            ArcoAeropuerto arco = new ArcoAeropuerto();
            arco.setId(total+i);
            arco.setAeropuerto1(arcos.get(i).getAeropuerto1());
            arco.setAeropuerto2(arcos.get(i).getAeropuerto2());
            arco.setHoraPartida(arcos.get(i).getHoraPartida());
            arco.setHoraLlegada(arcos.get(i).getHoraLlegada());
            arco.setCapacidadMaxima(arcos.get(i).getCapacidadMaxima());
            arco.setCapacidadDisponible(arcos.get(i).getCapacidadDisponible());
            horaPartida = (double) arcos.get(i).getHoraPartida().getHour()*60 + arcos.get(i).getHoraPartida().getMinute();
            horaLlegada = (double) arcos.get(i).getHoraLlegada().getHour()*60 + arcos.get(i).getHoraLlegada().getMinute();

            if(horaPartida < horaLlegada){
                arco.setDiaPartida(LocalDate.parse("2022-10-18"));
                arco.setDiaLLegada(LocalDate.parse("2022-10-18"));
            }
            else{
                arco.setDiaPartida(LocalDate.parse("2022-10-18"));
                arco.setDiaLLegada(LocalDate.parse("2022-10-19"));
            }
            arcos.add(arco);
        }

        //Vuelos del dia 3 y dia3-dia4
        for(int i =0;i<total;i++){
            ArcoAeropuerto arco = new ArcoAeropuerto();
            arco.setId(total+total+i);
            arco.setAeropuerto1(arcos.get(i).getAeropuerto1());
            arco.setAeropuerto2(arcos.get(i).getAeropuerto2());
            arco.setHoraPartida(arcos.get(i).getHoraPartida());
            arco.setHoraLlegada(arcos.get(i).getHoraLlegada());
            arco.setCapacidadMaxima(arcos.get(i).getCapacidadMaxima());
            arco.setCapacidadDisponible(arcos.get(i).getCapacidadDisponible());

            horaPartida = (double) arcos.get(i).getHoraPartida().getHour()*60 + arcos.get(i).getHoraPartida().getMinute();
            horaLlegada = (double) arcos.get(i).getHoraLlegada().getHour()*60 + arcos.get(i).getHoraLlegada().getMinute();

            if(horaPartida < horaLlegada){
                arco.setDiaPartida(LocalDate.parse("2022-10-19"));
                arco.setDiaLLegada(LocalDate.parse("2022-10-19"));
            }
            else{
                arco.setDiaPartida(LocalDate.parse("2022-10-19"));
                arco.setDiaLLegada(LocalDate.parse("2022-10-20"));
            }
            arcos.add(arco);
        }


        Iterator<ArcoAeropuerto> itr = arcos.iterator();
        while (itr.hasNext()) {
            ArcoAeropuerto arcosig = itr.next();
            ArcoAeropuerto arco = new ArcoAeropuerto();
            arco.setHoraPartida(arcosig.getHoraPartida());
            arco.setHoraLlegada(arcosig.getHoraLlegada());
            arco.setDiaLLegada(arcosig.getDiaLLegada());
            arco.setDiaPartida(arcosig.getDiaPartida());


            horaPartida = (double) arco.getHoraPartida().getHour()*60 + arco.getHoraPartida().getMinute();
            horaLlegada = (double) arco.getHoraLlegada().getHour()*60 + arco.getHoraLlegada().getMinute();
            LocalDate fechaActual = LocalDate.now();
            LocalDate diaSig = fechaActual.plusDays(1);
            LocalDate diaSigSig = fechaActual.plusDays(2);
            LocalTime horaActual = LocalTime.now();
            double horaActualValor = (double)horaActual.getHour()*60 + horaActual.getMinute();

            //Condicion para formar los arcos de envios entre mismo continente, es decir, 1 dia como maximo
            if(
                    !(
                            (
                            (fechaActual.isEqual(arco.getDiaPartida()) && fechaActual.isEqual(arco.getDiaLLegada()) && horaActualValor<=horaPartida)
                        ||
                            (fechaActual.isEqual(arco.getDiaPartida()) && diaSig.isEqual(arco.getDiaLLegada()) && horaActualValor<=horaPartida && horaActualValor-60>=horaLlegada)
                        ||
                        (diaSig.isEqual(arco.getDiaPartida())  && diaSig.isEqual(arco.getDiaLLegada()) && horaActualValor-60>=horaLlegada)
                            )
                            //&& arco.getCapacidadDisponible()>5 //cantidad de paquetes

                    )
            )
            {
                itr.remove();
            }

//            //Condicion para formar arcos de envios entre 2 continentes, es decir, 2 dias como maximo
//                        if(
//                                !(
//                                  (
//                                        (fechaActual.isEqual(arco.getDiaPartida()) && fechaActual.isEqual(arco.getDiaLLegada()) && horaActualValor<=horaPartida)
//                                    ||
//                                        (fechaActual.isEqual(arco.getDiaPartida()) && diaSig.isEqual(arco.getDiaLLegada()) && horaActualValor<=horaPartida)
//                                    ||
//                                        (diaSig.isEqual(arco.getDiaPartida())  && diaSig.isEqual(arco.getDiaLLegada()))
//                                     ||
//                                        (diaSig.isEqual(arco.getDiaPartida())  && diaSigSig.isEqual(arco.getDiaLLegada()) && horaActualValor-60>=horaLlegada)
//                                     ||
//                                        (diaSigSig.isEqual(arco.getDiaPartida())  && diaSigSig.isEqual(arco.getDiaLLegada()) && horaActualValor-60>=horaLlegada)
//                                     ||
//                                        (fechaActual.isEqual(arco.getDiaPartida())  && diaSigSig.isEqual(arco.getDiaLLegada()) && horaActualValor<=horaPartida && horaActualValor-60>=horaLlegada)
//                                  )
//                                  //&& arco.getCapacidadDisponible()>5 //cantidad de paquetes
//                                )
//                        )
//                        {
//                            itr.remove();
//                        }


        }




        //Se crean las capacidades de aeropuertos para los siguiente 3 dias
        for(int i=0;i<aeropuertos.size();i++){
            //dia1:
            for(int j=0;j<24;j++){
                for(int k=0;k<60;k++){
                    FechaHora fechaHora = new FechaHora();
                    fechaHora.setDia(LocalDate.parse("2022-10-17"));
                    fechaHora.setHora(LocalTime.of(j,k));

                    CapacidadAeropuerto capacidad = new CapacidadAeropuerto();
                    capacidad.setFechaHora(fechaHora);
                    capacidad.setCapacidadDisponible(aeropuertos.get(i).getCapacidad());
                    aeropuertos.get(i).getCapacidadDisponible().add(capacidad);
                }
            }
            //dia2:
            for(int j=0;j<24;j++){
                for(int k=0;k<60;k++){
                    FechaHora fechaHora = new FechaHora();
                    fechaHora.setDia(LocalDate.parse("2022-10-18"));
                    fechaHora.setHora(LocalTime.of(j,k));

                    CapacidadAeropuerto capacidad = new CapacidadAeropuerto();
                    capacidad.setFechaHora(fechaHora);
                    capacidad.setCapacidadDisponible(aeropuertos.get(i).getCapacidad());
                    aeropuertos.get(i).getCapacidadDisponible().add(capacidad);
                }
            }
            //dia3:
            for(int j=0;j<24;j++){
                for(int k=0;k<60;k++){
                    FechaHora fechaHora = new FechaHora();
                    fechaHora.setDia(LocalDate.parse("2022-10-19"));
                    fechaHora.setHora(LocalTime.of(j,k));

                    CapacidadAeropuerto capacidad = new CapacidadAeropuerto();
                    capacidad.setFechaHora(fechaHora);
                    capacidad.setCapacidadDisponible(aeropuertos.get(i).getCapacidad());
                    aeropuertos.get(i).getCapacidadDisponible().add(capacidad);
                }
            }
            //dia4:
            for(int j=0;j<24;j++){
                for(int k=0;k<60;k++){
                    FechaHora fechaHora = new FechaHora();
                    fechaHora.setDia(LocalDate.parse("2022-10-20"));
                    fechaHora.setHora(LocalTime.of(j,k));

                    CapacidadAeropuerto capacidad = new CapacidadAeropuerto();
                    capacidad.setFechaHora(fechaHora);
                    capacidad.setCapacidadDisponible(aeropuertos.get(i).getCapacidad());
                    aeropuertos.get(i).getCapacidadDisponible().add(capacidad);
                }
            }
        }

        // Creamos el ambiente
        AntSide ambiente= new AntSide(aeropuertos,arcos);
        // Hora actual
        LocalDateTime horaActual = LocalDateTime.now();


        //Se debe tener un arreglo de arcos general (ya se tiene) y feromonas general.
        //ArrayList<Double> feromonas = new ArrayList<Double>();
        //feromonas.addAll(Collections.singleton((double) 0.1));


        for(int j=0;j<6;j++) {
            // Fijamos los aeropuertos de origen y destino (Ej: 0: Bogotá - 6: Lima
            ambiente.setNodoInicialFinal(aeropuertos.get(0), aeropuertos.get(j+1));


            //Aqui se eligen el subarreglo de arcosEnvio y feromonasEnvio:
            //ArrayList<ArcoAeropuerto> arcosEnvio = modificarArcos(arcos,aeropuertos.get(0),aeropuertos.get(j+1)); //en este metodo se hace las funciones de mas arriba
            //ArrayList<Double> feromonasEnvio = sacarFeromonas(arcosEnvio);
            //ambiente.setCaminos(arcosEnvio);
            //ambiente.setCantidadFeromonasCamino(feromonasEnvio);


            //Se crean los paquetes (Esto se debe leer en la clase principal)
            ArrayList<Paquete> paquetes = new ArrayList<Paquete>();
            for (int i = 0; i < 20; i++) {
                Paquete paquete = new Paquete();//Ponerle el detalle
                paquetes.add(paquete);
            }

            //Fijamos los paquetes
            ambiente.setPaquetesEnvio(paquetes);
            ambiente.setFechaInicial(horaActual);

            // Creamos la solución
            Aco algoritmoHormigas = new Aco();
            long start1 = System.currentTimeMillis();
            algoritmoHormigas.activarHormigas(ambiente);
            long end1 = System.currentTimeMillis();
            System.out.println("Elapsed Time in milli seconds: " + (end1 - start1));

            //Se imprime la solucion
            System.out.println("Envio "+ j +" - Camino: " + algoritmoHormigas.getSolucionCamino().toString() + algoritmoHormigas.getSolucionCosto());

            //Actualizar capacidades
            ambiente.actualizarCapacidades(algoritmoHormigas.getSolucionCamino());

            //Actualizar arreglo global de arcos y feromonas, con el arcosEnvio y feromonasEnvio
            //actualizarArcosFeromonas(arcosEnvio,feromonasEnvio);

        }
    }
}



