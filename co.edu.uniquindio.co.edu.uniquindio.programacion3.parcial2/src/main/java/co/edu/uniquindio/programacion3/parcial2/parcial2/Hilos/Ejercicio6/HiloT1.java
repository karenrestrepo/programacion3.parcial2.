package co.edu.uniquindio.programacion3.parcial2.parcial2.Hilos.Ejercicio6;

public class HiloT1 extends Thread {

    private int resultado;
    private int numero;

    public HiloT1( int numero) {
        this.resultado = resultado;
        this.numero = numero;
    }

    @Override
    public void run(){
        resultado = sumaRecursiva(numero);
    }

    public static int sumaRecursiva(int n ){
        if (n == 0) {
            return 0;
        } else {

            return n + sumaRecursiva(n - 1);
        }


    }



    public int getResultado() {
        return resultado;
    }

    public int getNumero() {
        return numero;
    }
}
