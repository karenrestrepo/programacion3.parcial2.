package co.edu.uniquindio.programacion3.parcial2.parcial2.Hilos.Ejercicio4;

public class Main {

    public static void main(String[] args) {

        int numero = 10;
        String palabra = "otorrinolaringologia";

        T1 t1 = new T1(numero);
        T2 t2 = new T2(palabra);

        t1.start();
        t2.start();

        try{
            t1.join();
            t2.join();


        }catch (InterruptedException e){
            throw  new RuntimeException(e);

        }
        int resultado = t1.getResultado() * t2.getResultado();

        System.out.println("El resultado de S2 * S4 es = " + resultado);

    }

}
