package co.edu.uniquindio.programacion3.parcial2.parcial2.Hilos.Ejercicio5;

import co.edu.uniquindio.programacion3.parcial2.parcial2.Hilos.Ejercicio3.Hilo3;

public class main {

    private static long tac = System.currentTimeMillis();
    private static long tf = tac + 20000;
    public static void main(String[] args) {
        String palabra = "hola";
        ejecucionhilos(palabra);


    }

    private static void ejecucionhilos(String palabra){
        if(tac>tf){
            return;
        }
        Hilo1 hilo1 = new Hilo1(palabra);
        hilo1.start();
        try {
            hilo1.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(hilo1.getVariable());

        Hilo2 hilo2 = new Hilo2(hilo1.getVariable());
        hilo2.start();
        try {
            hilo2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println(hilo2.getMensaje());
        tac = System.currentTimeMillis();
        ejecucionhilos(palabra);

    }
}
