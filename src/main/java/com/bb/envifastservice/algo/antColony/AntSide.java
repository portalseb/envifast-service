package com.bb.envifastservice.algo.antColony;

import com.bb.envifastservice.algo.Aeropuerto;
import com.bb.envifastservice.algo.ArcoAeropuerto;
import com.bb.envifastservice.algo.Envio;
import com.bb.envifastservice.algo.Paquete;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class AntSide {
    public ArrayList<ArcoAeropuerto> caminos;//0: 1-2, 1:1-3, 2: 3-4, 3: 3-5, se debe cambiar
    public ArrayList<Integer> posiblesCaminosIndices;
    public ArrayList<Aeropuerto> nodos;//aeropuertos, se debe cambiar
    public ArrayList<Double> costos;//costos (duracion de vuelos), no se cambia
    public ArrayList<Double> visibilidad; //inversa de los costos, no se cambia
    public ArrayList<Double> cantidadFeromonasCamino; //feromonas, no se cambia
    public ArrayList<Double> probabilidadDeSerEscogido; //recta de probabilidad, no se cambia
    public ArrayList<Integer> numeroVecesDeSerEscogigo; //no se esta usando
    public static final double coeficienteEvaporacion = 0.9; //para actualizar feromonas, no se cambia
    public Aeropuerto nodoInicial; //cambiar tipo de dato
    public Aeropuerto nodoFinal; //cambiar tipo de dato
    public int tipoEnvio; //1: mismo continente, 2: diferentes contientes
    public double plazoMaximoEntrega;

    public ArrayList<Paquete> paquetesEnvio;

    public LocalDateTime fechaInicial;

    public AntSide(){
        caminos = new ArrayList<ArcoAeropuerto>();
        //caminos = new ArrayList<ArcoAeropuerto>();
        nodos = new ArrayList<Aeropuerto>();
        //nodos = new ArrayList <Aeropuerto>();
        costos = new ArrayList<Double>();
        visibilidad = new ArrayList<Double>();
        cantidadFeromonasCamino = new ArrayList<Double>();
        probabilidadDeSerEscogido = new ArrayList<Double>();
        numeroVecesDeSerEscogigo = new ArrayList<Integer>();
        posiblesCaminosIndices = new ArrayList<Integer>();
        paquetesEnvio = new ArrayList<Paquete>();
    }

    public AntSide(ArrayList<Aeropuerto> aeropuertos, ArrayList<ArcoAeropuerto> vuelos){
        caminos = new ArrayList<ArcoAeropuerto>(vuelos);
        nodos = new ArrayList<Aeropuerto>(aeropuertos);
        costos = new ArrayList<Double>(vuelos.size());
        visibilidad = new ArrayList<Double>(vuelos.size());
        cantidadFeromonasCamino = new ArrayList<Double>();
        for(int i=0;i<vuelos.size();i++)
            cantidadFeromonasCamino.add(0.1);
        probabilidadDeSerEscogido = new ArrayList<Double>(vuelos.size());
        numeroVecesDeSerEscogigo = new ArrayList<Integer>(vuelos.size());
        posiblesCaminosIndices = new ArrayList<Integer>();
        paquetesEnvio = new ArrayList<Paquete>();
    }

    public AntSide(int numeroAristas, int numeroNodos) {
        caminos = new ArrayList<ArcoAeropuerto>(numeroAristas);
        //caminos = new ArrayList<ArcoAeropuerto>(numeroAristas);
        nodos = new ArrayList<Aeropuerto>(numeroNodos);
        //nodos = new ArrayList <Aeropuerto>(numeroNodos);
        costos = new ArrayList<Double>(numeroAristas);
        visibilidad = new ArrayList<Double>(numeroAristas);
        cantidadFeromonasCamino = new ArrayList<Double>(numeroAristas);
        for(int i=0;i<numeroAristas;i++){
            cantidadFeromonasCamino.add(0.1);
        }
        probabilidadDeSerEscogido = new ArrayList<Double>(numeroAristas);
        numeroVecesDeSerEscogigo = new ArrayList<Integer>(numeroAristas);
        posiblesCaminosIndices = new ArrayList<Integer>();
        paquetesEnvio = new ArrayList<Paquete>();
    }

    public AntSide(int numeroAristas, int numeroNodos, Aeropuerto nodoIni, Aeropuerto nodoFin) {
        caminos = new ArrayList<ArcoAeropuerto>(numeroAristas);
        //caminos = new ArrayList<ArcoAeropuerto>(numeroAristas);
        nodos = new ArrayList<Aeropuerto>(numeroNodos);
        //nodos = new ArrayList <Aeropuerto>(numeroNodos);
        costos = new ArrayList<Double>(numeroAristas);
        visibilidad = new ArrayList<Double>(numeroAristas);
        cantidadFeromonasCamino = new ArrayList<Double>(numeroAristas);
        for(int i=0;i<numeroAristas;i++){
            cantidadFeromonasCamino.add(0.1);
        }
        probabilidadDeSerEscogido = new ArrayList<Double>(numeroAristas);
        numeroVecesDeSerEscogigo = new ArrayList<Integer>(numeroAristas);

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
        posiblesCaminosIndices = new ArrayList<Integer>();
        paquetesEnvio = new ArrayList<Paquete>();
    }

    public AntSide(int numeroAristas, int numeroNodos, Aeropuerto nodoIni, Aeropuerto nodoFin, ArrayList<Paquete> paquetes) {
        caminos = new ArrayList<ArcoAeropuerto>(numeroAristas);
        //caminos = new ArrayList<ArcoAeropuerto>(numeroAristas);
        nodos = new ArrayList<Aeropuerto>(numeroNodos);
        //nodos = new ArrayList <Aeropuerto>(numeroNodos);
        costos = new ArrayList<Double>(numeroAristas);
        visibilidad = new ArrayList<Double>(numeroAristas);
        cantidadFeromonasCamino = new ArrayList<Double>(numeroAristas);
        for(int i=0;i<numeroAristas;i++){
            cantidadFeromonasCamino.add(0.1);
        }
        probabilidadDeSerEscogido = new ArrayList<Double>(numeroAristas);
        numeroVecesDeSerEscogigo = new ArrayList<Integer>(numeroAristas);

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
        posiblesCaminosIndices = new ArrayList<Integer>();
        paquetesEnvio = paquetes;
    }

    public AntSide(int numeroAristas, int numeroNodos, Aeropuerto nodoIni, Aeropuerto nodoFin, ArrayList<Paquete> paquetes,LocalDateTime fechaIni) {
        caminos = new ArrayList<ArcoAeropuerto>(numeroAristas);
        //caminos = new ArrayList<ArcoAeropuerto>(numeroAristas);
        nodos = new ArrayList<Aeropuerto>(numeroNodos);
        //nodos = new ArrayList <Aeropuerto>(numeroNodos);
        costos = new ArrayList<Double>(numeroAristas);
        visibilidad = new ArrayList<Double>(numeroAristas);
        cantidadFeromonasCamino = new ArrayList<Double>(numeroAristas);
        for(int i=0;i<numeroAristas;i++){
            cantidadFeromonasCamino.add(0.1);
        }
        probabilidadDeSerEscogido = new ArrayList<Double>(numeroAristas);
        numeroVecesDeSerEscogigo = new ArrayList<Integer>(numeroAristas);

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
        posiblesCaminosIndices = new ArrayList<Integer>();
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
        paquetesEnvio = new ArrayList<Paquete>();
    }

    public void setNodoInicialFinalPaquetes(Aeropuerto nodoInicial, Aeropuerto nodoFinal, ArrayList<Paquete> paquetes) {
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

    public ArrayList<Paquete> getPaquetesEnvio() {
        return paquetesEnvio;
    }

    public void setPaquetesEnvio(ArrayList<Paquete> paquetesEnvio) {
        this.paquetesEnvio = paquetesEnvio;
    }

    public int getTipoEnvio() {
        return tipoEnvio;
    }
    public double getPlazoMaximoEntrega() {
        return plazoMaximoEntrega;
    }
    public ArrayList<Integer> getPosiblesCaminosIndices() {
        return posiblesCaminosIndices;
    }

    public void setPosiblesCaminosIndices(ArrayList<Integer> posiblesCaminosIndices) {
        this.posiblesCaminosIndices = posiblesCaminosIndices;
    }

    public ArrayList<ArcoAeropuerto> getCaminos() {
        return caminos;
    }

    public void setCaminos(ArrayList<ArcoAeropuerto> caminos) {
        this.caminos = caminos;
        cantidadFeromonasCamino = new ArrayList<Double>(caminos.size());
        for(int i=0;i<caminos.size();i++) {
            cantidadFeromonasCamino.add(0.1);
        }
    }

    public ArrayList<Aeropuerto> getNodos() {
        return nodos;
    }

    public void setNodos(ArrayList<Aeropuerto> nodos) {
        this.nodos = nodos;
    }

    public ArrayList<Double> getCostos() {
        return costos;
    }

    public void setCostos(ArrayList<Double> costos) {
        this.costos = costos;
    }

    public ArrayList<Double> getVisibilidad() {
        return visibilidad;
    }

    public void setVisibilidad(ArrayList<Double> visibilidad) {
        this.visibilidad = visibilidad;
    }

    public ArrayList<Double> getCantidadFeromonasCamino() {
        return cantidadFeromonasCamino;
    }

    public void setCantidadFeromonasCamino(ArrayList<Double> cantidadFeromonasCamino) {
        this.cantidadFeromonasCamino = cantidadFeromonasCamino;
    }

    public ArrayList<Double> getProbabilidadDeSerEscogido() {
        return probabilidadDeSerEscogido;
    }

    public void setProbabilidadDeSerEscogido(ArrayList<Double> probabilidadDeSerEscogido) {
        this.probabilidadDeSerEscogido = probabilidadDeSerEscogido;
    }

    public ArrayList<Integer> getNumeroVecesDeSerEscogigo() {
        return numeroVecesDeSerEscogigo;
    }

    public void setNumeroVecesDeSerEscogigo(ArrayList<Integer> numeroVecesDeSerEscogigo) {
        this.numeroVecesDeSerEscogigo = numeroVecesDeSerEscogigo;
    }

    public void actualizarFeromonasEnElCamino(Ant hormiga1, Ant hormiga2) {
        int i=0;
        while (i < getCantidadFeromonasCamino().size()) {
            if(hormiga1.getCaminoIndices().contains(i) && hormiga2.getCaminoIndices().contains(i))
                this.cantidadFeromonasCamino.set(i,(1 - coeficienteEvaporacion) * getCantidadFeromonasCamino().get(i) +
                        hormiga1.getCntQ()/hormiga1.getCostoTotal() +
                        hormiga2.getCntQ()/hormiga2.getCostoTotal());
            else if (hormiga1.getCaminoIndices().contains(i)) {
                this.cantidadFeromonasCamino.set(i,(1 - coeficienteEvaporacion) * getCantidadFeromonasCamino().get(i) +
                        hormiga1.getCntQ()/hormiga1.getCostoTotal());
            }
            else if (hormiga2.getCaminoIndices().contains(i)) {
                this.cantidadFeromonasCamino.set(i,(1 - coeficienteEvaporacion) * getCantidadFeromonasCamino().get(i) +
                        hormiga2.getCntQ()/hormiga2.getCostoTotal());
            }
            else{
                this.cantidadFeromonasCamino.set(i,(1 - coeficienteEvaporacion) * getCantidadFeromonasCamino().get(i));
            }
            i++;
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


            for(int k=0;k<60;k++){
                int hora=horaAnt.getHour();
                int minuto = horaAnt.getMinute();
                int dia = horaAnt.getDayOfMonth();
                int mes = horaAnt.getMonthValue();
                int anio = horaAnt.getYear();
                int ind = nodos.get(nodos.indexOf(destino)).getCapacidadIndex(hora, minuto, dia, mes, anio);
                for(int j=0;j<paquetesEnvio.size();j++) {
                    nodos.get(nodos.indexOf(destino)).getCapacidadDisponible().get(ind).agregarPaquete(paquetesEnvio.get(j));
                }
                horaAnt = horaAnt.plusMinutes(1);
            }
        }
    }

    public ArrayList<ArcoAeropuerto> sacarArcosPosibles(ArrayList<ArcoAeropuerto> arcosGeneral, Envio envio){
        ArrayList<ArcoAeropuerto> arcos = new ArrayList<>();
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


}
