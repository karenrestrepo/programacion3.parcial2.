package co.edu.uniquindio.programacion3.parcial2.parcial2.Hilos.Ejercicio2;

public class Hilo2 extends Thread {
    //Hilo 2 que cree un entero y lo multiplique por el mismo 10 veces. Tiempo de espera en
    // cada iteración : 1.5 segundo

    int numeroEntero;

    public Hilo2(int numeroEntero) {
        this.numeroEntero = numeroEntero;
    }

    @Override
    public void run(){
        try {
            if (numeroEntero > 0)
                for (int i = 0; i < 10; i++) {

                    int resultado = numeroEntero * numeroEntero;
                    System.out.println(resultado);
                    Thread.sleep(1500);

                }

        }catch (InterruptedException e){
            throw  new RuntimeException( e);

        }
    }
}
