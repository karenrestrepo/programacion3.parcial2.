package co.edu.uniquindio.programacion3.parcial2.parcial2.Hilos.Ejercicio3;

public class Hilo3  extends Thread{

    //Hilo 3 que imprima el nombre del mismo hilo 15 veces. Tiempo de espera en cada
    // iteración : 500 ms

    private String nombre;

    public Hilo3(String nombre) {
        this.nombre = nombre;
    }

    @Override

    public void run(){
        try{
            for (int i = 0; i < 15; i++) {

                System.out.println(nombre);
                Thread.sleep(500);

            }

        }catch (InterruptedException e){
            throw new RuntimeException(e);

        }
    }

}
