package co.edu.uniquindio.programacion3.parcial2.parcial2.Hilos.Ejercicio1;

import java.util.ArrayList;

public class mainHilo1 {

    public static void main(String[] args) {

        ArrayList<String> listaNombres = new ArrayList<>();
        listaNombres.add("Sofia:");
        listaNombres.add("Vanessa:");
        listaNombres.add("Clara: ");
        listaNombres.add("Victoria: ");
        listaNombres.add("jose: ");
        listaNombres.add("Stiven: ");

        Hilo1 hilo1 = new Hilo1(listaNombres);
        hilo1.start();

    }



}
