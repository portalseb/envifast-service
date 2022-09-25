package com.bb.envifastservice.algo.antColony;

import com.bb.envifastservice.algo.Aeropuerto;
import com.bb.envifastservice.algo.ArcoAeropuerto;
import com.bb.envifastservice.algo.LectorAeropuertos;
import com.bb.envifastservice.algo.LectorArcoAeropuerto;

import java.io.IOException;
import java.util.ArrayList;

public class Aco {
    private double solucionCosto;//No se deberia cambiar
    private ArrayList<ArcoAeropuerto> solucionCamino; //Cambiar tipo de dato
    public Aco() {
        solucionCosto=999999;
        solucionCamino=new ArrayList<ArcoAeropuerto>(); //Cambiar tipo de dato
    }
    public double getSolucionCosto() {
        return solucionCosto;
    }
    public ArrayList<ArcoAeropuerto> getSolucionCamino() {
        return solucionCamino;
    }
    public void activarHormigas(AntSide ambiente) {
        /*****************************************************************************************************************/
        /**Definir cual va a ser el limite y la cantidad de hormigas*/
        for(int i=0; i<5;i++){
            // Inicializar hormiga 1
            Ant hormiga1 = new Ant(ambiente);
            hormiga1.explorar();

            // Inicializar hormiga 2
            Ant hormiga2 = new Ant(ambiente);
            hormiga2.explorar();

            // Actualizar rastro de feromonas
            ambiente.actualizarFeromonasEnElCamino(hormiga1, hormiga2);

            // Impresion para verificar el camino de las hormigas, se deberia guardar el mejor camino en el ambiente tambien
            System.out.println("Camino de hormiga 1");
            System.out.println(hormiga1.getCaminoNodos().toString());
            System.out.println(hormiga1.getCostoTotal());
            System.out.println("Camino de hormiga 2");
            System.out.println(hormiga2.getCaminoNodos().toString());
            System.out.println(hormiga2.getCostoTotal());

            if(solucionCosto> hormiga1.getCostoTotal() || solucionCosto>hormiga2.getCostoTotal()){
                if(hormiga1.getCostoTotal()<hormiga2.getCostoTotal()){
                    solucionCamino=new ArrayList<ArcoAeropuerto>();
                    for(int j=0;j<hormiga1.getCaminoIndices().size();j++){
                        //System.out.println(ambiente.getCaminos().get(hormiga1.getCaminoIndices().get(j)));
                        solucionCamino.add(ambiente.getCaminos().get(hormiga1.getCaminoIndices().get(j)));
                    }
                    solucionCosto=hormiga1.getCostoTotal();
                }
                else {
                    solucionCamino=new ArrayList<ArcoAeropuerto>();
                    for (int j = 0; j < hormiga2.getCaminoIndices().size(); j++){
                        //System.out.println(ambiente.getCaminos().get(hormiga2.getCaminoIndices().get(j)));
                        solucionCamino.add(ambiente.getCaminos().get(hormiga2.getCaminoIndices().get(j)));
                    }
                    solucionCosto= hormiga2.getCostoTotal();
                }
            }
        /*****************************************************************************************************************/
        /*****************************************************************************************************************/


        }
    }

    //Main para llamar al algoritmo
    public static void main(String args[]) throws IOException {
//        // Inicializamos los arreglos
//        ArrayList<Aeropuerto> aeropuertos = new ArrayList<>();
//        ArrayList<ArcoAeropuerto> vuelos = new ArrayList<>();

        ArrayList<ArcoAeropuerto> arcos;
        ArrayList<Aeropuerto> aeropuertos;

        /* Leemos los aeropuertos y los planes de vuelo */
        LectorAeropuertos lectorAeropuertos = new LectorAeropuertos();
        lectorAeropuertos.Leer("C:\\Users\\Fernando\\Desktop\\fernando\\Archivos Pucp\\ciclo 9\\dp1\\algoritmos\\c.inf226.22-2.lista.aeropuertos.v01.txt");
        aeropuertos = lectorAeropuertos.getAeropuertos();

        LectorArcoAeropuerto lectorArcos = new LectorArcoAeropuerto(aeropuertos);
        lectorArcos.Leer("C:\\Users\\Fernando\\Desktop\\fernando\\Archivos Pucp\\ciclo 9\\dp1\\algoritmos\\c.inf226.22-2.planes_vuelo.v01.txt");
        arcos = lectorArcos.getArcos();

        // Creamos los aeropuertos
//        Aeropuerto a1 = new Aeropuerto(1, "001", "Lima", "lima", "Peru",
//                "Jorge Chavez", "PACIFICO", "America del sur");
//        Aeropuerto a2 = new Aeropuerto(2, "002", "Santiago de Chile", "sant", "Chile",
//                "Arturo Merino", "PACIFICO", "America del sur");
//        Aeropuerto a3 = new Aeropuerto(3, "003", "Caracas", "cara", "Venezuela",
//                "Simon Bolivar", "PACIFICO", "America del sur");
//        Aeropuerto a4 = new Aeropuerto(4, "004", "Bogota", "bogo", "Colombia",
//                "El Dorado", "PACIFICO", "America del sur");
//        Aeropuerto a5 = new Aeropuerto(5, "005", "Quito", "quit", "Ecuador",
//                "Mariscal Sucre", "PACIFICO", "America del sur");
//        Aeropuerto a6 = new Aeropuerto(6, "006", "La Paz", "lapa", "Bolivia",
//                "El Alto", "PACIFICO", "America del sur");
//        Aeropuerto a7 = new Aeropuerto(7, "007", "Brasilia", "bras", "Brasil",
//                "Juscelino Kubitschek", "PACIFICO", "America del sur");

        // Agregamos los aeropuertos
//        aeropuertos.add(a1);
//        aeropuertos.add(a2);
//        aeropuertos.add(a3);
//        aeropuertos.add(a4);
//        aeropuertos.add(a5);
//        aeropuertos.add(a6);
//        aeropuertos.add(a7);

        // Creamos los vuelos
//        ArcoAeropuerto arc1 = new ArcoAeropuerto("ABCD", a1, a2, "12:00", "16:00");
//        ArcoAeropuerto arc2 = new ArcoAeropuerto("DEFG", a1, a3, "12:00", "16:00");
//        ArcoAeropuerto arc3 = new ArcoAeropuerto("GHIJ", a1, a6, "12:00", "16:00");
//        ArcoAeropuerto arc4 = new ArcoAeropuerto("JKLM", a2, a7, "12:00", "16:00");
//        ArcoAeropuerto arc5 = new ArcoAeropuerto("MNOP", a2, a3, "12:00", "16:00");
//        ArcoAeropuerto arc6 = new ArcoAeropuerto("OPQR", a6, a3, "12:00", "16:00");
//        ArcoAeropuerto arc7 = new ArcoAeropuerto("RSTU", a6, a5, "12:00", "16:00");
//        ArcoAeropuerto arc8 = new ArcoAeropuerto("UVWX", a3, a7, "12:00", "16:00");
//        ArcoAeropuerto arc9 = new ArcoAeropuerto("XYZA", a3, a5, "12:00", "16:00");
//        ArcoAeropuerto arc10 = new ArcoAeropuerto("ACET", a5, a4, "12:00", "16:00");
//        ArcoAeropuerto arc11 = new ArcoAeropuerto("GERS", a7, a4, "12:00", "16:00");

//        vuelos.add(arc1);
//        vuelos.add(arc2);
//        vuelos.add(arc3);
//        vuelos.add(arc4);
//        vuelos.add(arc5);
//        vuelos.add(arc6);
//        vuelos.add(arc7);
//        vuelos.add(arc8);
//        vuelos.add(arc9);
//        vuelos.add(arc10);
//        vuelos.add(arc11);

        // Creamos el ambiente
        /**Este constructor se cambiará, debe aceptar el arreglo de vuelos y aeropuertos, aeropuerto de inicio y fin, (mas adelante la cantidad de paquetes)*/
        AntSide ambiente= new AntSide(aeropuertos,arcos);

        // Agregamos los aeropuertos de origen y destino al ambiente (nodo inicial y nodo final)
        //ambiente.setNodoInicial(a1);
        //ambiente.setNodoFinal(a4);
//        ambiente.setNodoInicialFinal(a1,a4); //nuevo metodo que agrega los 2 y tambien setea el tipo de envio (mismo continente o diferente)

        // Fijamos los aeropuertos de origen y destino (Ej: 0: Bogotá - 6: Lima
        ambiente.setNodoInicialFinal(aeropuertos.get(2),aeropuertos.get(11));

        /**************************************************************************************************************/
        /**************************************************************************************************************/
        /**IGNORAR ESTO PORQUE LOS COSTOS AHORA VAN A SER VARIABLES*/
        /** Se añaden los costos y visibilidad usando la funcion de obtenerDuracion de cada camino*/
        //for(int j=0;j<ambiente.getCaminos().size();j++) {
        //    ambiente.getCostos().add((double) ambiente.getCaminos().get(j).obtenerDuracionVuelo().toMinutes());
        //    ambiente.getVisibilidad().add(1.00 / ambiente.getCostos().get(j));
        //}
        /**************************************************************************************************************/
        /**************************************************************************************************************/

//        //Estos costos solo de prueba, los costos seran variables en cada salto porque tmbn se debe considerar el tiempo
//        //en el aeropuerto hasta el siguiente vuelo
//        ambiente.getCostos().add(5.00);
//        ambiente.getCostos().add(3.10);
//        ambiente.getCostos().add(5.20);
//        ambiente.getCostos().add(5.20);
//        ambiente.getCostos().add(4.90);
//        ambiente.getCostos().add(3.20);
//        ambiente.getCostos().add(4.70);
//        ambiente.getCostos().add(3.00);
//        ambiente.getCostos().add(6.00);
//        ambiente.getCostos().add(5.50);
//        ambiente.getCostos().add(4.80);
//
//        // Agregamos la visibilidad en cada vuelo
//        ambiente.getVisibilidad().add(0.2);
//        ambiente.getVisibilidad().add(0.32);
//        ambiente.getVisibilidad().add(0.19);
//        ambiente.getVisibilidad().add(0.19);
//        ambiente.getVisibilidad().add(0.2);
//        ambiente.getVisibilidad().add(0.31);
//        ambiente.getVisibilidad().add(0.21);
//        ambiente.getVisibilidad().add(0.33);
//        ambiente.getVisibilidad().add(0.16);
//        ambiente.getVisibilidad().add(0.18);
//        ambiente.getVisibilidad().add(0.21);

        // Creamos la solución
        Aco algoritmoHormigas = new Aco();
        algoritmoHormigas.activarHormigas(ambiente);
        System.out.println("Camino: " + algoritmoHormigas.getSolucionCamino().toString());
    }
}



