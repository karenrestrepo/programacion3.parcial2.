package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Utils;

public class Persistencia {

    public static final String RUTA_ARCHIVO_LOG = "src/main/resources/Persistencia/log/BancoLog.txt";


    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion) {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }
}