package com.bb.envifastservice.algo.antColony;

public class Aco {
    public Aco() {
    }

    public void activarHormigas() {
        //Inicializar ambiente
        AntSide ambiente= new AntSide(11,7,1,4); //Este constructor se cambiará, debe aceptar el arreglo de vuelos y aeropuertos, aeropuerto de inicio y fin, (mas adelante la cantidad de paquetes)

        for(int i=0; i<10;i++){
            // //Inicializar hormiga 1
            Ant hormiga1 = new Ant(ambiente);
            //    hormiga1.explorar();

            // //Inicializar hormiga 2
            Ant hormiga2 = new Ant(ambiente);
            //    hormiga2.explorar();

            //// Actualizar rastro de feromonas (falta cambiar el metodo para que acepte las 2 hormigas)
            // ambiente.actualizarFeromonasEnElCamino(hormiga1.getCaminoCostos(), hormiga2.getCaminoCostos());

        }
    }
}

    /** Main para llamar al algoritmo
    public static void main(String args[])
        {
            Se debe leer los vuelos y aeropuertos

            Aco algoritmoHormigas = new Aco();

            algoritmoHormigas.activarHormigas();
        }
     */

