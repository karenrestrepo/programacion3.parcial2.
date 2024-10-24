package co.edu.uniquindio.programacion3.parcial2.parcial2.Hilos.Ejercicio3;

public class MainHilo3 {

    public static void main(String[] args) {

        String texto = "Hola, soy hilo 3 ";

        Hilo3 hilo3 = new Hilo3(texto);
        hilo3.start();
    }
}
