package co.edu.uniquindio.programacion3.parcial2.parcial2.Hilos.Ejercicio4;

public class T2 extends Thread{

    //contarVocales(palabra)- T2

    private String palabra;
    private int resultado;

    public T2(String palabra) {
        this.palabra = palabra;
    }

    @Override
    public void run(){

        resultado = contarVocales(palabra,0);

    }
    public int contarVocales(String palabra,int i) {

        if (i >= palabra.length()){
            return 0;
        }

        char letra = palabra.charAt(i);
        int contar = (esVocal(letra) ? 1:0);
        return contar +contarVocales(palabra,i +1);




    }
    public static boolean esVocal(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }




    public String getPalabra() {
        return palabra;
    }

    public int getResultado() {
        return resultado;
    }
}
