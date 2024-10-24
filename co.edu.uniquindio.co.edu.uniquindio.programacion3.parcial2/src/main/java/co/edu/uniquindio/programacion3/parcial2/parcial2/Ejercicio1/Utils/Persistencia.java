package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Utils;

import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Model.Estudiante;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    public static final String RUTA_ARCHIVO_LOG = "src/main/resources/Persistencia/log/BancoLog.txt";
    private static final String RUTA_ARCHIVO_ESTUDIANTES = "src/main/resources/Persistencia/archivoEstudiantes.txt";


    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion) {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }

    public static void guardarEstudiantes(List<Estudiante> listaEstudiantes) throws IOException {
        String contenido = "";
        for(Estudiante estudiante:listaEstudiantes)
        {
            contenido+= estudiante.getCodigo()+
                    ","+estudiante.getNombre()+
                    ","+estudiante.getNota1()+
                    ","+estudiante.getNota2()+
                    ","+estudiante.getNota3()+"\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ESTUDIANTES, contenido, false);
    }

    public static void cargarDatosArchivos(Estudiante estudiante) throws IOException {
        ArrayList<Estudiante> estudiantesCargados = cargarEstudiantes();
        if(estudiantesCargados.size() > 0)
            estudiante.getListaEstudiantes().addAll(estudiantesCargados);
    }

    public static ArrayList<Estudiante> cargarEstudiantes() throws FileNotFoundException, IOException{
        ArrayList<Estudiante> estudiantes =new ArrayList<Estudiante>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_ESTUDIANTES);
        String linea="";
        for (int i = 0; i < contenido.size(); i++)
        {
            linea = contenido.get(i);
            Estudiante estudiante = new Estudiante();
            estudiante.setCodigo(linea.split(",")[0]);
            estudiante.setNombre(linea.split(",")[1]);
            estudiante.setNota1(linea.split(",")[2]);
            estudiante.setNota2(linea.split(",")[3]);
            estudiante.setNota3(linea.split(",")[4]);
            estudiantes.add(estudiante);
        }
        return estudiantes;
    }
}