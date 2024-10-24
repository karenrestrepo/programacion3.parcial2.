package co.edu.uniquindio.programacion3.parcial2.parcial2.Hilos.Ejercicio4;

public class T1 extends Thread{

    private int numero;
    private  int resultado;

    public T1(int numero) {
        this.numero = numero;

    }

    @Override
    public void run(){
        resultado = sumarRecursivo(numero);
    }

    public static int sumarRecursivo(int numero) {

        if (numero == 0){
            return 0;
        }else{
            return numero + sumarRecursivo(numero-1);

        }


    }
    public int getNumero() {
        return numero;
    }

    public int getResultado() {
        return resultado;
    }
}
