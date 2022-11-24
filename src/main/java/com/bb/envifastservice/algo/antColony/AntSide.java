package com.bb.envifastservice.algo.antColony;

import com.bb.envifastservice.algo.Aeropuerto;
import com.bb.envifastservice.algo.ArcoAeropuerto;
import com.bb.envifastservice.algo.Envio;
import com.bb.envifastservice.algo.Paquete;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;

public class AntSide {
    public LinkedList<ArcoAeropuerto> caminos;//0: 1-2, 1:1-3, 2: 3-4, 3: 3-5, se debe cambiar
    public LinkedList<Integer> posiblesCaminosIndices;
    public LinkedList<Aeropuerto> nodos;//aeropuertos, se debe cambiar
    public LinkedList<Double> costos;//costos (duracion de vuelos), no se cambia
    public LinkedList<Double> visibilidad; //inversa de los costos, no se cambia
    public LinkedList<Double> cantidadFeromonasCamino; //feromonas, no se cambia
    public LinkedList<Double> probabilidadDeSerEscogido; //recta de probabilidad, no se cambia
    public LinkedList<Integer> numeroVecesDeSerEscogigo; //no se esta usando
    public static final double coeficienteEvaporacion = 0.9; //para actualizar feromonas, no se cambia
    public Aeropuerto nodoInicial; //cambiar tipo de dato
    public Aeropuerto nodoFinal; //cambiar tipo de dato
    public int tipoEnvio; //1: mismo continente, 2: diferentes contientes
    public double plazoMaximoEntrega;

    public LinkedList<Paquete> paquetesEnvio;

    public LocalDateTime fechaInicial;
    public int maximoDeIteraciones;

    public AntSide(){
        caminos = new LinkedList<ArcoAeropuerto>();
        //caminos = new ArrayList<ArcoAeropuerto>();
        nodos = new LinkedList<Aeropuerto>();
        //nodos = new ArrayList <Aeropuerto>();
        costos = new LinkedList<Double>();
        visibilidad = new LinkedList<Double>();
        cantidadFeromonasCamino = new LinkedList<Double>();
        probabilidadDeSerEscogido = new LinkedList<Double>();
        numeroVecesDeSerEscogigo = new LinkedList<Integer>();
        posiblesCaminosIndices = new LinkedList<Integer>();
        paquetesEnvio = new LinkedList<Paquete>();
    }

    public AntSide(ArrayList<Aeropuerto> aeropuertos, ArrayList<ArcoAeropuerto> vuelos){
        caminos = new LinkedList<ArcoAeropuerto>(vuelos);
        nodos = new LinkedList<Aeropuerto>(aeropuertos);
        costos = new LinkedList<Double>();
        visibilidad = new LinkedList<Double>();
        cantidadFeromonasCamino = new LinkedList<Double>();
        for(int i=0;i<vuelos.size();i++)
            cantidadFeromonasCamino.add(0.1);
        probabilidadDeSerEscogido = new LinkedList<Double>();
        numeroVecesDeSerEscogigo = new LinkedList<Integer>();
        posiblesCaminosIndices = new LinkedList<Integer>();
        paquetesEnvio = new LinkedList<Paquete>();
    }

    public AntSide(int numeroAristas, int numeroNodos) {
        caminos = new LinkedList<ArcoAeropuerto>();
        //caminos = new ArrayList<ArcoAeropuerto>(numeroAristas);
        nodos = new LinkedList<Aeropuerto>();
        //nodos = new ArrayList <Aeropuerto>(numeroNodos);
        costos = new LinkedList<Double>();
        visibilidad = new LinkedList<Double>();
        cantidadFeromonasCamino = new LinkedList<Double>();
        for(int i=0;i<numeroAristas;i++){
            cantidadFeromonasCamino.add(0.1);
        }
        probabilidadDeSerEscogido = new LinkedList<Double>();
        numeroVecesDeSerEscogigo = new LinkedList<Integer>();
        posiblesCaminosIndices = new LinkedList<Integer>();
        paquetesEnvio = new LinkedList<Paquete>();
    }

    public AntSide(int numeroAristas, int numeroNodos, Aeropuerto nodoIni, Aeropuerto nodoFin) {
        caminos = new LinkedList<ArcoAeropuerto>();
        //caminos = new ArrayList<ArcoAeropuerto>(numeroAristas);
        nodos = new LinkedList<Aeropuerto>();
        //nodos = new ArrayList <Aeropuerto>(numeroNodos);
        costos = new LinkedList<Double>();
        visibilidad = new LinkedList<Double>();
        cantidadFeromonasCamino = new LinkedList<Double>();
        for(int i=0;i<numeroAristas;i++){
            cantidadFeromonasCamino.add(0.1);
        }
        probabilidadDeSerEscogido = new LinkedList<Double>();
        numeroVecesDeSerEscogigo = new LinkedList<Integer>();

        this.nodoInicial = nodoIni;
        this.nodoFinal = nodoFin;
        if(nodoInicial.getCiudad().getContinente().equals(nodoFinal.getCiudad().getContinente())) {
            this.tipoEnvio = 1;
            this.plazoMaximoEntrega= 1440.00; //1 dia en minutos
        }
        else {
            this.tipoEnvio = 2;
            this.plazoMaximoEntrega=2880.00; //2 dias en minutos
        }
        posiblesCaminosIndices = new LinkedList<Integer>();
        paquetesEnvio = new LinkedList<Paquete>();
    }

    public AntSide(int numeroAristas, int numeroNodos, Aeropuerto nodoIni, Aeropuerto nodoFin, LinkedList<Paquete> paquetes) {
        caminos = new LinkedList<ArcoAeropuerto>();
        //caminos = new ArrayList<ArcoAeropuerto>(numeroAristas);
        nodos = new LinkedList<Aeropuerto>();
        //nodos = new ArrayList <Aeropuerto>(numeroNodos);
        costos = new LinkedList<Double>();
        visibilidad = new LinkedList<Double>();
        cantidadFeromonasCamino = new LinkedList<Double>();
        for(int i=0;i<numeroAristas;i++){
            cantidadFeromonasCamino.add(0.1);
        }
        probabilidadDeSerEscogido = new LinkedList<Double>();
        numeroVecesDeSerEscogigo = new LinkedList<Integer>();

        this.nodoInicial = nodoIni;
        this.nodoFinal = nodoFin;
        if(nodoInicial.getCiudad().getContinente().equals(nodoFinal.getCiudad().getContinente())) {
            this.tipoEnvio = 1;
            this.plazoMaximoEntrega= 1440.00; //1 dia en minutos
        }
        else {
            this.tipoEnvio = 2;
            this.plazoMaximoEntrega=2880.00; //2 dias en minutos
        }
        posiblesCaminosIndices = new LinkedList<Integer>();
        paquetesEnvio = paquetes;
    }

    public AntSide(int numeroAristas, int numeroNodos, Aeropuerto nodoIni, Aeropuerto nodoFin, LinkedList<Paquete> paquetes,LocalDateTime fechaIni) {
        caminos = new LinkedList<ArcoAeropuerto>();
        //caminos = new ArrayList<ArcoAeropuerto>(numeroAristas);
        nodos = new LinkedList<Aeropuerto>();
        //nodos = new ArrayList <Aeropuerto>(numeroNodos);
        costos = new LinkedList<Double>();
        visibilidad = new LinkedList<Double>();
        cantidadFeromonasCamino = new LinkedList<Double>();
        for(int i=0;i<numeroAristas;i++){
            cantidadFeromonasCamino.add(0.1);
        }
        probabilidadDeSerEscogido = new LinkedList<Double>();
        numeroVecesDeSerEscogigo = new LinkedList<Integer>();

        this.nodoInicial = nodoIni;
        this.nodoFinal = nodoFin;
        if(nodoInicial.getCiudad().getContinente().equals(nodoFinal.getCiudad().getContinente())) {
            this.tipoEnvio = 1;
            this.plazoMaximoEntrega= 1440.00; //1 dia en minutos
        }
        else {
            this.tipoEnvio = 2;
            this.plazoMaximoEntrega=2880.00; //2 dias en minutos
        }
        posiblesCaminosIndices = new LinkedList<Integer>();
        paquetesEnvio = paquetes;
        fechaInicial = fechaIni;
    }

    public LocalDateTime getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(LocalDateTime fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Aeropuerto getNodoInicial() {
        return nodoInicial;
    }

    public void setNodoInicial(Aeropuerto nodoInicial) {
        this.nodoInicial = nodoInicial;
    }

    public Aeropuerto getNodoFinal() {
        return nodoFinal;
    }

    public void setNodoFinal(Aeropuerto nodoFinal) {
        this.nodoFinal = nodoFinal;
    }


    public void setNodoInicialFinal(Aeropuerto nodoInicial, Aeropuerto nodoFinal) {
        this.nodoInicial = nodoInicial;
        this.nodoFinal = nodoFinal;
        if(nodoInicial.getCiudad().getContinente().equals(nodoFinal.getCiudad().getContinente())) {
            this.tipoEnvio = 1;
            this.plazoMaximoEntrega= 1440.00; //1 dia en minutos
        }
        else {
            this.tipoEnvio = 2;
            this.plazoMaximoEntrega=2880.00; //2 dias en minutos
        }
        paquetesEnvio = new LinkedList<Paquete>();
    }

    public void setNodoInicialFinalPaquetes(Aeropuerto nodoInicial, Aeropuerto nodoFinal, LinkedList<Paquete> paquetes) {
        this.nodoInicial = nodoInicial;
        this.nodoFinal = nodoFinal;
        if(nodoInicial.getCiudad().getContinente().equals(nodoFinal.getCiudad().getContinente())) {
            this.tipoEnvio = 1;
            this.plazoMaximoEntrega= 1440.00; //1 dia en minutos
        }
        else {
            this.tipoEnvio = 2;
            this.plazoMaximoEntrega=2880.00; //2 dias en minutos
        }
        this.paquetesEnvio = paquetes;
    }

    public void setTipoEnvio(int tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }

    public void setPlazoMaximoEntrega(double plazoMaximoEntrega) {
        this.plazoMaximoEntrega = plazoMaximoEntrega;
    }

    public LinkedList<Paquete> getPaquetesEnvio() {
        return paquetesEnvio;
    }

    public void setPaquetesEnvio(LinkedList<Paquete> paquetesEnvio) {
        this.paquetesEnvio = paquetesEnvio;
    }

    public int getTipoEnvio() {
        return tipoEnvio;
    }
    public double getPlazoMaximoEntrega() {
        return plazoMaximoEntrega;
    }
    public LinkedList<Integer> getPosiblesCaminosIndices() {
        return posiblesCaminosIndices;
    }

    public void setPosiblesCaminosIndices(LinkedList<Integer> posiblesCaminosIndices) {
        this.posiblesCaminosIndices = posiblesCaminosIndices;
    }

    public LinkedList<ArcoAeropuerto> getCaminos() {
        return caminos;
    }

    public void setCaminos(LinkedList<ArcoAeropuerto> caminos) {
        this.caminos = caminos;
        cantidadFeromonasCamino = new LinkedList<Double>();
        for(int i=0;i<caminos.size();i++) {
            cantidadFeromonasCamino.add(0.1);
        }
    }

    public LinkedList<Aeropuerto> getNodos() {
        return nodos;
    }

    public void setNodos(LinkedList<Aeropuerto> nodos) {
        this.nodos = nodos;
    }

    public LinkedList<Double> getCostos() {
        return costos;
    }

    public void setCostos(LinkedList<Double> costos) {
        this.costos = costos;
    }

    public LinkedList<Double> getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(LinkedList<Double> visibilidad) {
        this.visibilidad = visibilidad;
    }

    public LinkedList<Double> getCantidadFeromonasCamino() {
        return cantidadFeromonasCamino;
    }

    public void setCantidadFeromonasCamino(LinkedList<Double> cantidadFeromonasCamino) {
        this.cantidadFeromonasCamino = cantidadFeromonasCamino;
    }

    public LinkedList<Double> getProbabilidadDeSerEscogido() {
        return probabilidadDeSerEscogido;
    }

    public void setProbabilidadDeSerEscogido(LinkedList<Double> probabilidadDeSerEscogido) {
        this.probabilidadDeSerEscogido = probabilidadDeSerEscogido;
    }

    public LinkedList<Integer> getNumeroVecesDeSerEscogigo() {
        return numeroVecesDeSerEscogigo;
    }

    public void setNumeroVecesDeSerEscogigo(LinkedList<Integer> numeroVecesDeSerEscogigo) {
        this.numeroVecesDeSerEscogigo = numeroVecesDeSerEscogigo;
    }

    public int getMaximoDeIteraciones() {
        return maximoDeIteraciones;
    }

    public void setMaximoDeIteraciones(int maximoDeIteraciones) {
        this.maximoDeIteraciones = maximoDeIteraciones;
    }

    public void actualizarFeromonasEnElCamino(Ant hormiga1, Ant hormiga2) {
        int i=0;
        if(hormiga1.isEncontroCamino() && hormiga2.isEncontroCamino()) {
            while (i < getCantidadFeromonasCamino().size()) {
                if (hormiga1.getCaminoIndices().contains(i) && hormiga2.getCaminoIndices().contains(i))
                    this.cantidadFeromonasCamino.set(i, (1 - coeficienteEvaporacion) * getCantidadFeromonasCamino().get(i) +
                            hormiga1.getCntQ() / hormiga1.getCostoTotal() +
                            hormiga2.getCntQ() / hormiga2.getCostoTotal());
                else if (hormiga1.getCaminoIndices().contains(i)) {
                    this.cantidadFeromonasCamino.set(i, (1 - coeficienteEvaporacion) * getCantidadFeromonasCamino().get(i) +
                            hormiga1.getCntQ() / hormiga1.getCostoTotal());
                } else if (hormiga2.getCaminoIndices().contains(i)) {
                    this.cantidadFeromonasCamino.set(i, (1 - coeficienteEvaporacion) * getCantidadFeromonasCamino().get(i) +
                            hormiga2.getCntQ() / hormiga2.getCostoTotal());
                } else {
                    this.cantidadFeromonasCamino.set(i, (1 - coeficienteEvaporacion) * getCantidadFeromonasCamino().get(i));
                }
                i++;
            }
        }
        else{
            if(hormiga1.isEncontroCamino()){
                while (i < getCantidadFeromonasCamino().size()) {
                    if (hormiga1.getCaminoIndices().contains(i))
                        this.cantidadFeromonasCamino.set(i, (1 - coeficienteEvaporacion) * getCantidadFeromonasCamino().get(i) +
                                hormiga1.getCntQ() / hormiga1.getCostoTotal());
                    else
                        this.cantidadFeromonasCamino.set(i, (1 - coeficienteEvaporacion) * getCantidadFeromonasCamino().get(i));
                    i++;
                }
            }
            else{
                if(hormiga2.isEncontroCamino()){
                    while (i < getCantidadFeromonasCamino().size()) {
                        if (hormiga2.getCaminoIndices().contains(i)) {
                            this.cantidadFeromonasCamino.set(i, (1 - coeficienteEvaporacion) * getCantidadFeromonasCamino().get(i) +
                                    hormiga2.getCntQ() / hormiga2.getCostoTotal());
                        } else {
                            this.cantidadFeromonasCamino.set(i, (1 - coeficienteEvaporacion) * getCantidadFeromonasCamino().get(i));
                        }
                        i++;
                    }
                }
            }


        }
    }

    public void actualizarCapacidades(ArrayList<ArcoAeropuerto> caminoSolucion){
        int minutosHastaElVuelo=0;
        LocalDateTime horaAct = fechaInicial;
        LocalDateTime horaAnt = LocalDateTime.of(horaAct.getYear(),horaAct.getMonthValue(),horaAct.getDayOfMonth(),horaAct.getHour(),horaAct.getMinute());

        for(int i=0;i<caminoSolucion.size();i++){
            ArcoAeropuerto camino = caminoSolucion.get(i);
            Aeropuerto origen = camino.getAeropuerto1();
            Aeropuerto destino = camino.getAeropuerto2();

            //Calcular minutos entre horaAnt y camino.horaSalida
            minutosHastaElVuelo = (int) ChronoUnit.MINUTES.between(horaAnt,LocalDateTime.of(camino.getDiaPartida().getYear(),camino.getDiaPartida().getMonthValue(),camino.getDiaPartida().getDayOfMonth(),camino.getHoraPartida().getHour(),camino.getHoraPartida().getMinute()));


            //Se llena la capacidad de aeropuerto origen desde la hora de llegada anterior hasta la hora de partida del vuelo
            for(int k=0;k<minutosHastaElVuelo;k++){
                int hora=horaAnt.getHour();
                int minuto = horaAnt.getMinute();
                int dia = horaAnt.getDayOfMonth();
                int mes = horaAnt.getMonthValue();
                int anio = horaAnt.getYear();
                int ind = nodos.get(nodos.indexOf(origen)).getCapacidadIndex(hora, minuto, dia, mes, anio);
                for(int j=0;j<paquetesEnvio.size();j++) {
                    nodos.get(nodos.indexOf(origen)).getCapacidadDisponible().get(ind).agregarPaquete(paquetesEnvio.get(j));
                }
                horaAnt = horaAnt.plusMinutes(1);
            }

            for(int j=0;j<paquetesEnvio.size();j++) {
                caminos.get(caminos.indexOf(camino)).agregarPaquete(paquetesEnvio.get(j));
            }

            //Se llena la capacidad de aeropuerto destino desde la hora de llegada hasta 1 hora despues
            horaAnt = LocalDateTime.of(camino.getDiaLLegada().getYear(),camino.getDiaLLegada().getMonthValue(),camino.getDiaLLegada().getDayOfMonth(),camino.getHoraLlegada().getHour(),camino.getHoraLlegada().getMinute());

            //System.out.println("a esta hora llega el ultimo avion "+horaAnt);

            for(int k=0;k<60;k++){
                int hora=horaAnt.getHour();
                int minuto = horaAnt.getMinute();
                int dia = horaAnt.getDayOfMonth();
                int mes = horaAnt.getMonthValue();
                int anio = horaAnt.getYear();
                int ind = nodos.get(nodos.indexOf(destino)).getCapacidadIndex(hora, minuto, dia, mes, anio);
                if(ind==-1) {System.out.println("No se encontro el indice para" + hora +":"+ minuto +" " + dia + "-"+mes +"-" +anio); return;}
                for(int j=0;j<paquetesEnvio.size();j++) {
                    nodos.get(nodos.indexOf(destino)).getCapacidadDisponible().get(ind).agregarPaquete(paquetesEnvio.get(j));
                }
                horaAnt = horaAnt.plusMinutes(1);
            }
        }
    }

    public LinkedList<ArcoAeropuerto> sacarArcosPosibles(LinkedList<ArcoAeropuerto> arcosGeneral, Envio envio){
        LinkedList<ArcoAeropuerto> arcos = new LinkedList<>();
        if(envio.getOrigen().getCiudad().getContinente().equals(envio.getDestino().getCiudad().getContinente())) {
            for (int i = 0; i < arcosGeneral.size(); i++) {
                ArcoAeropuerto arco = arcosGeneral.get(i);
                double horaPartida = (double) arco.getHoraPartida().getHour() * 60 + arco.getHoraPartida().getMinute();
                double horaLlegada = (double) arco.getHoraLlegada().getHour() * 60 + arco.getHoraLlegada().getMinute();
                LocalDate fechaActual = LocalDate.of(envio.getFechaEnvio().getYear(),envio.getFechaEnvio().getMonthValue(),envio.getFechaEnvio().getDayOfMonth());
                LocalDate diaSig = fechaActual.plusDays(1);
                LocalDate diaSigSig = fechaActual.plusDays(2);
                LocalTime horaActual = LocalTime.of(envio.getFechaEnvio().getHour(),envio.getFechaEnvio().getMinute());
                double horaActualValor = (double) horaActual.getHour() * 60 + horaActual.getMinute();
                if (

                        (
                                (fechaActual.isEqual(arco.getDiaPartida()) && fechaActual.isEqual(arco.getDiaLLegada()) && horaActualValor <= horaPartida)
                                        ||
                                        (fechaActual.isEqual(arco.getDiaPartida()) && diaSig.isEqual(arco.getDiaLLegada()) && horaActualValor <= horaPartida && horaActualValor - 60 >= horaLlegada)
                                        ||
                                        (diaSig.isEqual(arco.getDiaPartida()) && diaSig.isEqual(arco.getDiaLLegada()) && horaActualValor - 60 >= horaLlegada)
                        )
                                && arco.getCapacidadDisponible() > envio.getCantidadPaquetes()
                )
                    arcos.add(arco);
            }
        }
        else{
            for (int i = 0; i < arcosGeneral.size(); i++) {
                ArcoAeropuerto arco = arcosGeneral.get(i);
                double horaPartida = (double) arco.getHoraPartida().getHour() * 60 + arco.getHoraPartida().getMinute();
                double horaLlegada = (double) arco.getHoraLlegada().getHour() * 60 + arco.getHoraLlegada().getMinute();
                LocalDate fechaActual = LocalDate.of(envio.getFechaEnvio().getYear(),envio.getFechaEnvio().getMonthValue(),envio.getFechaEnvio().getDayOfMonth());
                LocalDate diaSig = fechaActual.plusDays(1);
                LocalDate diaSigSig = fechaActual.plusDays(2);
                LocalTime horaActual = LocalTime.of(envio.getFechaEnvio().getHour(),envio.getFechaEnvio().getMinute());
                double horaActualValor = (double) horaActual.getHour() * 60 + horaActual.getMinute();
                if(
                        (
                                (fechaActual.isEqual(arco.getDiaPartida()) && fechaActual.isEqual(arco.getDiaLLegada()) && horaActualValor<=horaPartida)
                                        ||
                                        (fechaActual.isEqual(arco.getDiaPartida()) && diaSig.isEqual(arco.getDiaLLegada()) && horaActualValor<=horaPartida)
                                        ||
                                        (diaSig.isEqual(arco.getDiaPartida())  && diaSig.isEqual(arco.getDiaLLegada()))
                                        ||
                                        (diaSig.isEqual(arco.getDiaPartida())  && diaSigSig.isEqual(arco.getDiaLLegada()) && horaActualValor-60>=horaLlegada)
                                        ||
                                        (diaSigSig.isEqual(arco.getDiaPartida())  && diaSigSig.isEqual(arco.getDiaLLegada()) && horaActualValor-60>=horaLlegada)
                                        ||
                                        (fechaActual.isEqual(arco.getDiaPartida())  && diaSigSig.isEqual(arco.getDiaLLegada()) && horaActualValor<=horaPartida && horaActualValor-60>=horaLlegada)
                        )
                                && arco.getCapacidadDisponible()>envio.getCantidadPaquetes()
                )
                    arcos.add(arco);
            }
        }
        return  arcos;
    }
    public ArrayList<ArcoAeropuerto> sacarArcosPosiblesEUSA(ArrayList<ArcoAeropuerto> arcos){
        ArrayList<ArcoAeropuerto> subArcos = new ArrayList<>();
        for(int i=0;i<arcos.size();i++){
            if(
                    (arcos.get(i).getAeropuerto1().getCiudad().getContinente().equals("AMERICA DEL SUR") && arcos.get(i).getAeropuerto2().getCiudad().getContinente().equals("AMERICA DEL SUR"))
                || (arcos.get(i).getAeropuerto1().getCiudad().getContinente().equals("EUROPA") && arcos.get(i).getAeropuerto2().getCiudad().getContinente().equals("AMERICA DEL SUR"))

            )
                subArcos.add(arcos.get(i));
        }
        int flag;
        for(int i=0;i<arcos.size();i++) {
            flag=0;
            if(arcos.get(i).getAeropuerto1().getCiudad().getContinente().equals("EUROPA") && arcos.get(i).getAeropuerto2().getCiudad().getContinente().equals("EUROPA")){
                for(int j=0;j<subArcos.size();j++){
                    if(arcos.get(i).getAeropuerto2().getId()==subArcos.get(j).getAeropuerto1().getId() && subArcos.get(j).getAeropuerto1().getCiudad().getContinente().equals("EUROPA") && subArcos.get(j).getAeropuerto2().getCiudad().getContinente().equals("AMERICA DEL SUR")){
                        flag = 1;break;}
                }
            }
            if(flag==1){
                subArcos.add(arcos.get(i));
            }
        }
        return subArcos;
    }


}
