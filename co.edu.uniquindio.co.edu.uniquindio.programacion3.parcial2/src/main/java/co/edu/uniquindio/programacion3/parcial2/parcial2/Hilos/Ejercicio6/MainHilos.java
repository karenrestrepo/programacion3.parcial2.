package co.edu.uniquindio.programacion3.parcial2.parcial2.Hilos.Ejercicio6;

public class MainHilos {
    public static void main(String[] args) {

        int n = 10;
        int factorial = 6;
        String palabra = "Electroencefalografista";

        HiloT1 hiloT1 = new HiloT1(10);
        HiloT2 hiloT2 = new HiloT2(6);
        HiloT3 hiloT3 = new HiloT3(palabra);

        hiloT1.start();
        hiloT2.start();
        hiloT3.start();

        try {
            hiloT1.join();
            hiloT2.join();
            hiloT3.join();

        }catch (InterruptedException e){
            throw  new RuntimeException(e);

        }
        int d = hiloT1.getResultado() * hiloT2.getResultado();
        int resultado = hiloT3.getResultado();

        System.out.println("El valor de d = " + d +"\n"+ " La cantidad de consonantes es = " + resultado);
    }
}
