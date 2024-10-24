package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio2.Utils;

import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio2.Model.Programa;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {
    public static final String RUTA_ARCHIVO_LOG = "src/main/resources/Persistencia/log/ProgramasLog.txt";
    private static final String RUTA_ARCHIVO_PROGRAMAS = "src/main/resources/Persistencia/archivoProgramas.xml";

    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion) {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }

    public static void guardarProgramas(List<Programa> listaProgramas) throws Exception {
        ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_PROGRAMAS, new ArrayList<>(listaProgramas));
    }

    public static ArrayList<Programa> cargarProgramas() throws Exception {
        ArrayList<Programa> programas;
        try {
            programas = (ArrayList<Programa>) ArchivoUtil.cargarRecursoSerializadoXML(RUTA_ARCHIVO_PROGRAMAS);
        } catch (IOException e) {
            programas = new ArrayList<>();
        }
        return programas;
    }
}
