package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio4.Utils;

import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio4.Model.Docente;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio4.Model.Estudiante;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio4.Model.Materia;

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
            try {
                String[] datos = linea.split(";");
                if (datos.length == 7) {
                    // Limpiar espacios en blanco de cada campo
                    for (int i = 0; i < datos.length; i++) {
                        datos[i] = datos[i].trim();
                    }

                    Estudiante estudiante = new Estudiante(
                            datos[0], // código
                            datos[1], // nombre
                            datos[2], // apellido
                            datos[3], // email
                            datos[4], // teléfono
                            datos[5], // dirección
                            datos[6]  // ciudad
                    );
                    estudiantes.add(estudiante);
                } else {
                    System.out.println("Error: Formato inválido en línea de estudiante: " + linea);
                    System.out.println("Se esperaban 7 campos, se encontraron: " + datos.length);
                }
            } catch (Exception e) {
                System.out.println("Error al procesar línea de estudiante: " + linea);
                e.printStackTrace();
            }
        }
        return estudiantes;
    }

    // Cargar la lista de docentes desde el archivo
    public static List<Docente> cargarDocentes(String ruta) throws IOException {
        List<String> contenido = ArchivoUtil.leerArchivo(ruta);
        List<Docente> docentes = new ArrayList<>();

        for (String linea : contenido) {
            try {
                String[] datos = linea.split(";");
                if (datos.length == 9) {
                    // Limpiar espacios en blanco de cada campo
                    for (int i = 0; i < datos.length; i++) {
                        datos[i] = datos[i].trim();
                    }

                    Docente docente = new Docente(
                            datos[0], // código
                            datos[1], // nombre
                            datos[2], // apellido
                            datos[3], // email
                            datos[4], // teléfono
                            datos[5], // dirección
                            datos[6], // ciudad
                            datos[7], // profesión
                            datos[8]  // especialidad
                    );
                    docentes.add(docente);
                } else {
                    System.out.println("Error: Formato inválido en línea de docente: " + linea);
                    System.out.println("Se esperaban 9 campos, se encontraron: " + datos.length);
                }
            } catch (Exception e) {
                System.out.println("Error al procesar línea de docente: " + linea);
                e.printStackTrace();
            }
        }
        return docentes;
    }

    // Cargar la lista de materias desde el archivo
    public static List<Materia> cargarMaterias(String ruta) throws IOException {
        List<String> contenido = ArchivoUtil.leerArchivo(ruta);
        List<Materia> materias = new ArrayList<>();

        for (String linea : contenido) {
            try {
                String[] datos = linea.split("@@");
                if (datos.length >= 2) {  // Cambiado para ser más flexible con el formato
                    // Limpiar espacios en blanco de cada campo
                    for (int i = 0; i < datos.length; i++) {
                        datos[i] = datos[i].trim();
                    }

                    // Extraer el código numérico (primera parte antes de @@)
                    String codigo = datos[0];
                    String nombre = datos[1];
                    int creditos = 0;
                    String tipo = "";

                    if (datos.length >= 3) {
                        try {
                            creditos = Integer.parseInt(datos[2]);
                        } catch (NumberFormatException e) {
                            System.out.println("Error al convertir créditos: " + datos[2]);
                        }
                    }

                    if (datos.length >= 4) {
                        tipo = datos[3];
                    }

                    Materia materia = new Materia(codigo, nombre, creditos, tipo);
                    materias.add(materia);
                } else {
                    System.out.println("Error: Formato inválido en línea de materia: " + linea);
                    System.out.println("Se esperaban al menos 2 campos, se encontraron: " + datos.length);
                }
            } catch (Exception e) {
                System.out.println("Error al procesar línea de materia: " + linea);
                e.printStackTrace();
            }
        }
        return materias;
    }

    // Guardar la asignación en el archivo
    public static void guardarAsignacion(String contenido) throws IOException {
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_ASIGNACIONES, contenido + "\n", true);
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