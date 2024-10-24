package co.edu.uniquindio.programacion3.parcial2.parcial2.Hilos.Ejercicio1;

import java.util.ArrayList;

public class Hilo1 extends Thread{

    ///  Hilo 1 que cree un arreglo e imprima sus valores. Tiempo de espera en cada iteración : 1
    // segundo

    ArrayList<String> listanombre;

    public Hilo1(ArrayList<String> listanombre) {
        this.listanombre = listanombre;
    }

    @Override
    public void run(){

        try {
            for(String nombre: listanombre){
                System.out.println(nombre);
                Thread.sleep(1000);
            }

        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
