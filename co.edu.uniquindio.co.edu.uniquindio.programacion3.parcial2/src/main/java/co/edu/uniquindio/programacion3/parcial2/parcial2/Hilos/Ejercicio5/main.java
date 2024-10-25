package co.edu.uniquindio.programacion3.parcial2.parcial2.Hilos.Ejercicio5;

import co.edu.uniquindio.programacion3.parcial2.parcial2.Hilos.Ejercicio3.Hilo3;

public class main {
    public static void main(String[] args) {
        String palabra = "hola";
        long duration = 6000;
        long totalDuration = 20000;

        long tf = System.currentTimeMillis() + totalDuration;

        while (System.currentTimeMillis() < tf) {
            Hilo1 hilo1 = new Hilo1(palabra, duration);
            hilo1.start();
            try {
                hilo1.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            Hilo2 hilo2 = new Hilo2(hilo1.getMensaje());
            hilo2.start();
            try {
                hilo2.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
