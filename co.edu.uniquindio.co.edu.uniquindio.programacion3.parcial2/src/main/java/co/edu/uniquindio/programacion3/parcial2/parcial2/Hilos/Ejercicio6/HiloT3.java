package co.edu.uniquindio.programacion3.parcial2.parcial2.Hilos.Ejercicio6;

public class HiloT3 extends Thread {

    private int resultado;
    private String palabra;

    public HiloT3( String palabra) {
        this.palabra = palabra;
    }

    @Override
    public void run(){
        resultado = contarConsonantesRecursivo(palabra);
    }

    public static int contarConsonantesRecursivo(String palabra){

        if (palabra.isEmpty()) {
            return 0;
        }

        String consonantes = "bcdfghjklmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ";


        if (consonantes.indexOf(palabra.charAt(0)) != -1) {
            return 1 + contarConsonantesRecursivo(palabra.substring(1));
        } else {
            return contarConsonantesRecursivo(palabra.substring(1));
        }

    }

    public int getResultado() {
        return resultado;
    }

    public String getPalabra() {
        return palabra;
    }
}
