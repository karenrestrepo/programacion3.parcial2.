package co.edu.uniquindio.programacion3.parcial2.parcial2.Hilos.Ejercicio5;

public class Hilo1 extends Thread{

    private String variable;
    long tac = System.currentTimeMillis();
    long tf =tac + 6000;



    public Hilo1(String variable) {
        this.variable = variable;
    }

    @Override
    public void run(){
        unirCadena();
    }


    private void unirCadena(){
        while (tac < tf){
            variable +=variable;
        }
        try{
            Thread.sleep(1000);

        }catch(InterruptedException e){
            throw new RuntimeException(e);
        }

    }





    public String getVariable() {
        return variable;
    }
}
