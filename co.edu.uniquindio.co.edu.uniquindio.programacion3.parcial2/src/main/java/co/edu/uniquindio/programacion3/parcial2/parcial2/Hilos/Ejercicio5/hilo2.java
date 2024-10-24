package co.edu.uniquindio.programacion3.parcial2.parcial2.Hilos.Ejercicio5;

public class Hilo2 extends Thread{

   private String mensaje;

    public Hilo2(String mensaje) {
        this.mensaje = mensaje;
    }

    @Override
    public void run(){
        System.out.println(mensaje);
        mensaje=" ";
    }


    public String getMensaje() {
        return mensaje;
    }
}
