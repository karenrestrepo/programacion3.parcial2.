package co.edu.uniquindio.programacion3.parcial2.parcial2.Hilos.Ejercicio2;

public class MainHilo2 {

    public static void main(String[] args) {

        int numero = 5;

        Hilo2 hilo2 = new Hilo2(numero);
        hilo2.start();
    }


}
