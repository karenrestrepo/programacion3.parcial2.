package co.edu.uniquindio.programacion3.parcial2.parcial2.Hilos.Ejercicio5;

public class Hilo1 extends Thread {
    private String palabra;
    private String mensaje = "";
    private long tf;

    public Hilo1(String palabra, long duration) {
        this.palabra = palabra;
        this.tf = System.currentTimeMillis() + duration;
    }

    @Override
    public void run() {
        while (System.currentTimeMillis() < tf) {
            mensaje += palabra;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public String getMensaje() {
        return mensaje;
    }
}
