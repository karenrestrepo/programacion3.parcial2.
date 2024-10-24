package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio4.Utils;

import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio4.Model.Docente;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio4.Model.Estudiante;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio4.Model.Materia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Persistencia {
    // Rutas de los archivos de datos
    public static final String RUTA_ARCHIVO_ESTUDIANTES = "src/main/resources/Persistencia/Ejercicio4/estudiantes.txt";
    public static final String RUTA_ARCHIVO_DOCENTES = "src/main/resources/Persistencia/Ejercicio4/docentes.txt";
    public static final String RUTA_ARCHIVO_MATERIAS = "src/main/resources/Persistencia/Ejercicio4/materias.txt";
    public static final String RUTA_ARCHIVO_ASIGNACIONES = "src/main/resources/Persistencia/Ejercicio4/asignacion.txt";
    public static final String RUTA_ARCHIVO_LOG = "src/main/resources/Persistencia/Ejercicio4/log.txt";
    private static final String RUTA_ARCHIVO_LOGIN = "src/main/resources/Persistencia/Ejercicio4/login.properties";

    // Cargar la lista de estudiantes desde el archivo
    public static List<Estudiante> cargarEstudiantes(String ruta) throws IOException {
        List<String> contenido = ArchivoUtil.leerArchivo(ruta);
        List<Estudiante> estudiantes = new ArrayList<>();

        for (String linea : contenido) {
            String[] datos = linea.split(";");
            if (datos.length == 7) {  // Asegúrate de que hay 7 datos
                Estudiante estudiante = new Estudiante(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6]);
                estudiantes.add(estudiante);
            } else {
                System.out.println("Datos inválidos en línea de estudiante: " + linea);
            }
        }

        return estudiantes;
    }

    // Cargar la lista de docentes desde el archivo
    public static List<Docente> cargarDocentes(String ruta) throws IOException {
        List<String> contenido = ArchivoUtil.leerArchivo(ruta);
        List<Docente> docentes = new ArrayList<>();

        for (String linea : contenido) {
            String[] datos = linea.split(";");
            if (datos.length == 9) {  // Asegúrate de que hay 9 datos
                Docente docente = new Docente(datos[0], datos[1], datos[2], datos[3], datos[4], datos[5], datos[6], datos[7], datos[8]);
                docentes.add(docente);
            } else {
                System.out.println("Datos inválidos en línea de docente: " + linea);
            }
        }

        return docentes;
    }

    // Cargar la lista de materias desde el archivo
    public static List<Materia> cargarMaterias(String ruta) throws IOException {
        List<String> contenido = ArchivoUtil.leerArchivo(ruta);
        List<Materia> materias = new ArrayList<>();

        for (String linea : contenido) {
            String[] datos = linea.split("@@");
            Materia materia = new Materia(datos[0], datos[1], Integer.parseInt(datos[2]), datos[3]);
            materias.add(materia);
        }

        return materias;
    }

    // Guardar la asignación en el archivo
    public static void guardarAsignacion(String contenido) throws IOException {
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ASIGNACIONES, contenido, true);
    }

    // Registrar logs de acciones
    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion) {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }

    public static boolean validarCredenciales(String usuario, String contrasenia) throws IOException {
        Properties prop = new Properties();
        prop.load(new java.io.FileInputStream(RUTA_ARCHIVO_LOGIN));
        return usuario.equals(prop.getProperty("usuario")) &&
                contrasenia.equals(prop.getProperty("contraseña"));
    }
}
