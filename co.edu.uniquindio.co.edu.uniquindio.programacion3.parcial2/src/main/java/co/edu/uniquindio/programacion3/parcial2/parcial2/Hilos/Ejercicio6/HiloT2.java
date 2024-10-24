package co.edu.uniquindio.programacion3.parcial2.parcial2.Hilos.Ejercicio6;

public class HiloT2 extends Thread{

    private int resultado;
    private int numero;

    public HiloT2( int numero) {
        this.numero = numero;
    }

    @Override
    public void run(){
        resultado = calcularFactorial(numero);
    }
    public static int calcularFactorial(int numero){

        if (numero == 0 || numero == 1) {
            return 1;
        } else {
            return numero * calcularFactorial(numero - 1);
        }


    }

    public int getResultado() {
        return resultado;
    }

    public int getNumero() {
        return numero;
    }
}
