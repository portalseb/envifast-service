package com.bb.envifastservice.algo.antColony;

import com.bb.envifastservice.algo.Aeropuerto;
import com.bb.envifastservice.algo.ArcoAeropuerto;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import static java.time.temporal.ChronoUnit.DAYS;

public class Ant {
    private int cantidadCaminos; //De momento no se usa
    private ArrayList<Boolean> caminoElegidoPorHormiga;//De momento no se usa
    private ArrayList<Double> feromonasEntreVisibilidad; //2da columna de las tablas por cada paso, no se cambia
    private ArrayList<Double> ponderadoEscalaProbabilidades; //recta de probabilidades, no se cambia
    private ArrayList<Double> probabilidadDeCaminoEntreSumatoria; //3era columna de las tablas por cada paso, no se cambia
    private ArrayList<Integer> posiblesCaminosIndices; //Indices (en el arreglo caminos de Antside) de los caminos que puede recorrer la hormiga, no se cambia
    private ArrayList<Integer> caminoIndices; //Indices (en el arreglo caminos de Antside) de los caminos que recorre la hormiga, no se cambia
    private double costoTotal;//Costo del camino que siguio la hormiga, no se cambia
    public double cntQ= 1000;//Aprendizaje
    private AntSide ambienteGlobal=null;
    private ArrayList<Aeropuerto> caminoNodos; //Cambiar tipo de dato -> Aeropuerto
    private ArrayList<Double> caminoCostos; //Cambiar tipo de dato -> ArcoAeropuerto

    private ArrayList<Double> caminoProbabilidades;

    private boolean encontroCamino;

    public Ant(AntSide ambienteHormiga)
    {
        this.ambienteGlobal=ambienteHormiga;
        this.feromonasEntreVisibilidad=new ArrayList<Double>(ambienteGlobal.getCostos().size());
        this.ponderadoEscalaProbabilidades=new ArrayList<Double>(ambienteGlobal.getCostos().size());
        this.probabilidadDeCaminoEntreSumatoria=new ArrayList<Double>(ambienteGlobal.getCostos().size());
        this.caminoElegidoPorHormiga=new ArrayList<Boolean>(ambienteGlobal.getCostos().size());
        this.caminoNodos = new ArrayList<Aeropuerto>(ambienteGlobal.getCostos().size()); //Este tamaño solo sera suficiente si nos aseguramos que no puede recorrer un mismo camino 2 veces
        this.caminoCostos = new ArrayList<Double>(ambienteGlobal.getCostos().size()); //Este tamaño solo sera suficiente si nos aseguramos que no puede recorrer un mismo camino 2 veces
        this.caminoNodos.add(this.ambienteGlobal.getNodoInicial());
        posiblesCaminosIndices = new ArrayList<Integer>();
        caminoIndices = new ArrayList<Integer>();
        costoTotal=0.0;
        this.caminoProbabilidades = new ArrayList<Double>(ambienteGlobal.getCostos().size());
        this.encontroCamino = false;
    }
    public ArrayList<Aeropuerto> getCaminoNodos() {
        return caminoNodos;
    }
    public void setCaminoNodos(ArrayList<Aeropuerto> caminoNodos) {
        this.caminoNodos = caminoNodos;
    }

    public ArrayList<Double> getCaminoCostos() {
        return caminoCostos;
    }

    public void setCaminoCostos(ArrayList<Double> caminoCostos) {
        this.caminoCostos = caminoCostos;
    }

    public ArrayList<Integer> getPosiblesCaminosIndices() {
        return posiblesCaminosIndices;
    }

    public void setPosiblesCaminosIndices(ArrayList<Integer> posiblesCaminosIndices) {
        this.posiblesCaminosIndices = posiblesCaminosIndices;
    }

    public ArrayList<Integer> getCaminoIndices() {
        return caminoIndices;
    }

    public void setCaminoIndices(ArrayList<Integer> caminoIndices) {
        this.caminoIndices = caminoIndices;
    }

    public double getCntQ() {
        return cntQ;
    }

    public double getCostoTotal(){
        return this.costoTotal;
    }


    public ArrayList<Double> getCaminoProbabilidades() {
        return caminoProbabilidades;
    }

    public boolean isEncontroCamino() {
        return encontroCamino;
    }

    public ArrayList<Double> probabilidadElegirUnCamino(AntSide ambiente)//El ambiente que se pasa contiene los caminos posibles desde el nodo de la hormiga
    {
        int i=0;
        double sumatoriaDeProbabilidades=0.0;
        //Se limpian las feromonasEntreVisibilidad, ponderadoEscalaProbabilidades, probabilidadDeCaminoEntreSumatoria
        feromonasEntreVisibilidad = new ArrayList<Double>(ambiente.getCaminos().size());
        ponderadoEscalaProbabilidades = new ArrayList<Double>(ambiente.getCaminos().size());
        probabilidadDeCaminoEntreSumatoria = new ArrayList<Double>(ambiente.getCaminos().size());

        for(i=0;i <= ambiente.getCantidadFeromonasCamino().size()-1;i++)
        {
            // Para calcular la probabilidad de elegir un camino primero se multiplica las feromonas en el camino por la visibilidad.
            this.feromonasEntreVisibilidad.add(ambiente.getCantidadFeromonasCamino().get(i) * ambiente.getVisibilidad().get(i));
            //Luego se realiza una sumatoria de los caminos a elegir.
            sumatoriaDeProbabilidades += this.feromonasEntreVisibilidad.get(i);
        }
        double vTemp=0.0;
        for(i=0;i < ambiente.getCantidadFeromonasCamino().size();i++)
        {
            //Se calcula el probablidad/sumatoriaDeProbabilidades para cada camino
            this.probabilidadDeCaminoEntreSumatoria.add( this.feromonasEntreVisibilidad.get(i)/sumatoriaDeProbabilidades);
            //Se realiza un ponderado para establecer una escala de probabilidades.
            this.ponderadoEscalaProbabilidades.add(this.probabilidadDeCaminoEntreSumatoria.get(i) + vTemp);
            vTemp = this.ponderadoEscalaProbabilidades.get(i);
        }
        return ponderadoEscalaProbabilidades;
    }
    public boolean llegoAlFinal(Aeropuerto nodoActual){
        return ambienteGlobal.getNodoFinal() == nodoActual;
    }


    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /** Revisar si comprende todos los casos*/
    public int nodoSiguiente(ArrayList<Double> rectaProbabilidades){
        Random rand = new Random();
        double pos = rand.nextDouble();
        int i=0;
        for(i=0;i<rectaProbabilidades.size();i++) {
            if (i == 0){
                if (rectaProbabilidades.get(i) >= pos) return i;
            }
            else {
                if (rectaProbabilidades.get(i - 1) <= pos && rectaProbabilidades.get(i) >= pos)
                    break;
            }
        }
        return i;
    }
    /******************************************************************************************************************/
    /******************************************************************************************************************/


    /******************************************************************************************************************/
    /******************************************************************************************************************/
    /** Poner los limites de capacidad ******************************************************/
    public AntSide posiblesCaminos(AntSide ambienteGlob, ArrayList<Aeropuerto> nodos, ArrayList<Double> costos,
                                   Aeropuerto nodoAnt, Aeropuerto nodoAct){
        AntSide caminosHormiga = new AntSide();
        ArcoAeropuerto camino;
        Aeropuerto origen, destino;
        posiblesCaminosIndices = new ArrayList<Integer>(); //0: 1, 1: 3
        int k, capacidadAeropuertoDestino, capacidadVuelo;
        LocalDate dateSalidaSiguienteVuelo, dateLlegadaUltimoVuelo;
        double diaLlegadaUltimoVuelo, diaSalidaSiguienteVuelo;

        double horaLLegadaUltimoVuelo = 0.0,horaSalidaSiguienteVuelo;
        if(nodoAct.getId() != ambienteGlob.getNodoInicial().getId()) {
            horaLLegadaUltimoVuelo = (double) ambienteGlob.getCaminos().get(caminoIndices.get(caminoIndices.size() - 1)).getHoraLlegada().getHour() * 60 + ambienteGlob.getCaminos().get(caminoIndices.get(caminoIndices.size() - 1)).getHoraLlegada().getMinute();
            diaLlegadaUltimoVuelo = (double) ambienteGlob.getCaminos().get(caminoIndices.get(caminoIndices.size() - 1)).getDiaLLegada().getYear()*10000 + (double) ambienteGlob.getCaminos().get(caminoIndices.get(caminoIndices.size() - 1)).getDiaLLegada().getMonth().getValue()*100+(double) ambienteGlob.getCaminos().get(caminoIndices.get(caminoIndices.size() - 1)).getDiaLLegada().getDayOfMonth();
            dateLlegadaUltimoVuelo = ambienteGlob.getCaminos().get(caminoIndices.get(caminoIndices.size() - 1)).getDiaLLegada();
            if(horaLLegadaUltimoVuelo+60>=24*60){
                dateLlegadaUltimoVuelo.plusDays(1);
                diaLlegadaUltimoVuelo = dateLlegadaUltimoVuelo.getYear()*10000+dateLlegadaUltimoVuelo.getMonthValue()*100+dateLlegadaUltimoVuelo.getDayOfMonth();
                horaLLegadaUltimoVuelo = horaLLegadaUltimoVuelo+60-24*60;
            }
            else{
                horaLLegadaUltimoVuelo = horaLLegadaUltimoVuelo+60;
            }


        }
        else {
            dateLlegadaUltimoVuelo = ambienteGlob.getFechaInicial().toLocalDate();
            horaLLegadaUltimoVuelo = (double) ambienteGlob.getFechaInicial().getHour() *60 + ambienteGlob.getFechaInicial().getMinute(); //aqui poner la hora actual
            diaLlegadaUltimoVuelo = (double) dateLlegadaUltimoVuelo.getYear()*10000 + dateLlegadaUltimoVuelo.getMonthValue()*100 + dateLlegadaUltimoVuelo.getDayOfMonth(); //ver si esta bien

        }
        for(int i=0;i<ambienteGlob.getCaminos().size();i++){
            camino = ambienteGlob.getCaminos().get(i);
            origen = new Aeropuerto(camino.getAeropuerto1());
            destino = new Aeropuerto(camino.getAeropuerto2());
            horaSalidaSiguienteVuelo = (double) camino.getHoraPartida().getHour()*60 + camino.getHoraPartida().getMinute();
            diaSalidaSiguienteVuelo = (double) camino.getDiaPartida().getYear()*10000 + camino.getDiaPartida().getMonth().getValue()*100 + camino.getDiaPartida().getDayOfMonth();
            dateSalidaSiguienteVuelo = camino.getDiaPartida();

//            System.out.println(diaLlegadaUltimoVuelo);
//            System.out.println(diaSalidaSiguienteVuelo);
//            System.out.println(DAYS.between(dateLlegadaUltimoVuelo,dateSalidaSiguienteVuelo));
//
//            System.out.println(horaLLegadaUltimoVuelo);
//            System.out.println(horaSalidaSiguienteVuelo);

            k = destino.getCapacidadIndex(camino.getHoraLlegada().getHour(),camino.getHoraLlegada().getMinute(),camino.getDiaLLegada().getDayOfMonth(),camino.getDiaLLegada().getMonthValue(),camino.getDiaLLegada().getYear());
            if(k==-1) {System.out.println("Indice de capacidad no encontrado"); continue;}
            capacidadAeropuertoDestino = destino.getCapacidadDisponible().get(k).getCapacidadDisponible();
            capacidadVuelo = camino.getCapacidadDisponible();

            if(
             (origen.getId() == nodoAct.getId() && nodoAct.getId() == ambienteGlob.getNodoInicial().getId() &&
             this.costoTotal + (double) camino.obtenerDuracionVuelo().toMinutes() <= ambienteGlob.getPlazoMaximoEntrega() &&
             //¿Aqui tambien se pondria lo del transbordo, desde la hora actual hasta cuando salga el vuelo?
             (

             (horaLLegadaUltimoVuelo <= horaSalidaSiguienteVuelo && this.costoTotal +  (DAYS.between(dateLlegadaUltimoVuelo,dateSalidaSiguienteVuelo))*1440 + (horaSalidaSiguienteVuelo - horaLLegadaUltimoVuelo) + (double) camino.obtenerDuracionVuelo().toMinutes() +60<= ambienteGlob.getPlazoMaximoEntrega()
             && diaLlegadaUltimoVuelo <= diaSalidaSiguienteVuelo
             )

                     ||
             (horaLLegadaUltimoVuelo > horaSalidaSiguienteVuelo && this.costoTotal +  (DAYS.between(dateLlegadaUltimoVuelo,dateSalidaSiguienteVuelo)-1)*1440 + (horaSalidaSiguienteVuelo + 24 * 60 - horaLLegadaUltimoVuelo) + (double) camino.obtenerDuracionVuelo().toMinutes() + 60<= ambienteGlob.getPlazoMaximoEntrega()
                     && diaLlegadaUltimoVuelo < diaSalidaSiguienteVuelo
             )
             )
             && !this.caminoNodos.contains(camino.getAeropuerto2())
                     && capacidadVuelo >= this.ambienteGlobal.getPaquetesEnvio().size()
                     && capacidadAeropuertoDestino >= this.ambienteGlobal.getPaquetesEnvio().size()

             ) ||
             (origen.getId() == nodoAct.getId() && destino.getId()!=nodoAnt.getId() &&
             (
             (horaLLegadaUltimoVuelo <= horaSalidaSiguienteVuelo && this.costoTotal +  (DAYS.between(dateLlegadaUltimoVuelo,dateSalidaSiguienteVuelo))*1440 + (horaSalidaSiguienteVuelo - horaLLegadaUltimoVuelo) + (double) camino.obtenerDuracionVuelo().toMinutes() +60<= ambienteGlob.getPlazoMaximoEntrega()
                     && diaLlegadaUltimoVuelo <= diaSalidaSiguienteVuelo
             )
                     ||
             (horaLLegadaUltimoVuelo > horaSalidaSiguienteVuelo && this.costoTotal + (DAYS.between(dateLlegadaUltimoVuelo,dateSalidaSiguienteVuelo)-1)*1440 + (horaSalidaSiguienteVuelo + 24 * 60 - horaLLegadaUltimoVuelo) + (double) camino.obtenerDuracionVuelo().toMinutes() +60<= ambienteGlob.getPlazoMaximoEntrega()
                     && diaLlegadaUltimoVuelo < diaSalidaSiguienteVuelo
             )
             )
             && !this.caminoNodos.contains(camino.getAeropuerto2())
                     && capacidadVuelo >= this.ambienteGlobal.getPaquetesEnvio().size()
                     && capacidadAeropuertoDestino >= this.ambienteGlobal.getPaquetesEnvio().size()
             )
             )
            {
                caminosHormiga.getCaminos().add(camino);
                caminosHormiga.getCantidadFeromonasCamino().add(ambienteGlob.getCantidadFeromonasCamino().get(i));
                if(horaLLegadaUltimoVuelo <= horaSalidaSiguienteVuelo){
                    caminosHormiga.getCostos().add( (DAYS.between(dateLlegadaUltimoVuelo,dateSalidaSiguienteVuelo))*1440 +(horaSalidaSiguienteVuelo - horaLLegadaUltimoVuelo) + (double) camino.obtenerDuracionVuelo().toMinutes()+60);
                }
                else {
                    caminosHormiga.getCostos().add( (DAYS.between(dateLlegadaUltimoVuelo,dateSalidaSiguienteVuelo)-1)*1440 +(horaSalidaSiguienteVuelo + 24 * 60 - horaLLegadaUltimoVuelo) + (double) camino.obtenerDuracionVuelo().toMinutes()+60);
                }
                caminosHormiga.getVisibilidad().add( 1.00/caminosHormiga.getCostos().get(caminosHormiga.getCostos().size() - 1));
                caminosHormiga.getPosiblesCaminosIndices().add(i);
                //posiblesCaminosIndices.add(i);
            }


//            if((origen.getId() == nodoAct.getId() && nodoAct.getId() == ambienteGlob.getNodoInicial().getId()) ||
//                    (origen.getId() == nodoAct.getId() && destino.getId()!=nodoAnt.getId() )){
//                caminosHormiga.getCaminos().add(camino);
//                caminosHormiga.getCantidadFeromonasCamino().add(ambienteGlob.getCantidadFeromonasCamino().get(i));
//                caminosHormiga.getVisibilidad().add(ambienteGlob.getVisibilidad().get(i));
//                caminosHormiga.getCostos().add(ambienteGlob.getCostos().get(i));
//                posiblesCaminosIndices.add(i);
//            }
        }

        return caminosHormiga;
    }
    /******************************************************************************************************************/
    /******************************************************************************************************************/

    public void explorar()
    {
        Aeropuerto nodoActual = ambienteGlobal.getNodoInicial();
        Aeropuerto nuevoNodo, nodoAnt = new Aeropuerto();
        int pos=0,k=0;
        AntSide caminosHormiga = null;
        ArrayList<AntSide> caminosHormigaAnteriores=new ArrayList<AntSide>();
        ArcoAeropuerto camino = null;
        int sinCamino=0;
        while(!llegoAlFinal(nodoActual)) //While hasta que llegue al final (solucion)
        {
            // Hallar los caminos posibles desde el nodo donde esta la hormiga
            // (no puede volver y podriamos poner que no pase por nodos donde ya estuvo revisando el caminoNodos)
            caminosHormiga = posiblesCaminos(this.ambienteGlobal, this.caminoNodos, this.caminoCostos, nodoAnt, nodoActual);

            if(caminosHormiga.getCaminos().size()==0)
            {
                //Retroceder hasta tener un ambiente en el que haya caminos posibles
                while(caminosHormigaAnteriores.size()>0) {
                    k = caminosHormigaAnteriores.get(caminosHormigaAnteriores.size()-1).getCaminos().indexOf(this.ambienteGlobal.getCaminos().get(this.caminoIndices.get(this.caminoIndices.size()-1)));
                    caminosHormigaAnteriores.get(caminosHormigaAnteriores.size() - 1).getCaminos().remove(k);
                    caminosHormigaAnteriores.get(caminosHormigaAnteriores.size() - 1).getVisibilidad().remove(k);
                    caminosHormigaAnteriores.get(caminosHormigaAnteriores.size() - 1).getCostos().remove(k);
                    caminosHormigaAnteriores.get(caminosHormigaAnteriores.size() - 1).getCantidadFeromonasCamino().remove(k);
                    caminosHormigaAnteriores.get(caminosHormigaAnteriores.size() - 1).getPosiblesCaminosIndices().remove(k);


                    this.caminoIndices.remove(this.caminoIndices.size() - 1);
                    this.costoTotal = this.costoTotal - this.caminoCostos.get(this.caminoCostos.size() - 1);
                    this.caminoCostos.remove(this.caminoCostos.size() - 1);
                    this.caminoNodos.remove(this.caminoNodos.size() - 1);
                    if (this.caminoNodos.size() > 1) {
                        nodoActual = this.caminoNodos.get(this.caminoNodos.size() - 1);
                        nodoAnt = this.caminoNodos.get(this.caminoNodos.size() - 2);
                    }
                    if (this.caminoNodos.size() == 1) {
                        nodoActual = this.caminoNodos.get(this.caminoNodos.size() - 1);
                        nodoAnt = new Aeropuerto();
                    }
                    this.caminoProbabilidades.remove(this.caminoProbabilidades.size()-1);

                    if (caminosHormigaAnteriores.get(caminosHormigaAnteriores.size() - 1).getCaminos().size() > 0) {
                        //todavia hay caminos posibles
                        caminosHormiga=caminosHormigaAnteriores.get(caminosHormigaAnteriores.size()-1);
                        caminosHormigaAnteriores.remove(caminosHormigaAnteriores.size() - 1);
                        break;
                    }
                    caminosHormigaAnteriores.remove(caminosHormigaAnteriores.size() - 1);
                    if(caminosHormigaAnteriores.size()==0){  sinCamino=1; System.out.println("No hay camino posible"); break;}
                }
            }

            if(sinCamino==1){this.encontroCamino=false; return;}

            // Se genera y se usa la recta de probabilidades para sacar el nuevo nodo
            pos = nodoSiguiente(probabilidadElegirUnCamino(caminosHormiga));
            camino = caminosHormiga.getCaminos().get(pos);
            nuevoNodo = camino.getAeropuerto2();

            //Actualizar el arreglo caminoNodos y caminoCostos con el nuevo nodo
            this.caminoNodos.add(nuevoNodo);
            this.caminoCostos.add(caminosHormiga.getCostos().get(pos));
            this.costoTotal = this.costoTotal + caminosHormiga.getCostos().get(pos);

            this.caminoProbabilidades.add(probabilidadDeCaminoEntreSumatoria.get(pos));

            //Arreglo que sirve para guardar los indices de los caminos del arreglo del ambiente global
            this.caminoIndices.add(caminosHormiga.getPosiblesCaminosIndices().get(pos));

            //Se actualiza nodoAnterior con el nodo actual
            nodoAnt = nodoActual;

            //Se actualiza nodoActual con el nuevo nodo
            nodoActual = nuevoNodo;

            caminosHormigaAnteriores.add(caminosHormiga);
        }
        this.encontroCamino = true;
    }
}
