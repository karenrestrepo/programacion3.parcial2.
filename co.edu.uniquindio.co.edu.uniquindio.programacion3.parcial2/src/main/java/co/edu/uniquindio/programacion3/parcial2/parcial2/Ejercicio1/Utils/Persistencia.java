package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Utils;

import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Model.*;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Model.Cliente;
import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

    private static final String RUTA_ARCHIVO_CONFIG = "src/main/resources/Persistencia/config.properties";

    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion) {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_CONFIG);
    }


    public static void guardarEmpleados(List<Empleado> listaEmpleados) throws IOException {
        String contenido = "";
        for(Empleado empleado : listaEmpleados) {
            contenido += empleado.getId() + "$$" +
                    empleado.getNombre() + "$$" +
                    empleado.getApellido() + "$$" +
                    empleado.getIdDepartamento() + "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_CONFIG, contenido, false);
    }

    public static ArrayList<Empleado> cargarEmpleados() throws IOException {
        ArrayList<Empleado> empleados = new ArrayList<>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_CONFIG);

        for (String linea : contenido) {
            String[] datos = linea.split("$$");
            Empleado empleado = new Empleado();
            empleado.setId(datos[0]);
            empleado.setNombre(datos[1]);
            empleado.setApellido(datos[2]);
            empleado.setIdDepartamento(datos[3]);
        }
        return empleados;
    }

    public static void guardarDepartamentos(List<Departamento> listaDepartamentos) throws IOException {
        String contenido = "";
        for(Departamento departamento : listaDepartamentos) {
            contenido += departamento.getId() + "$$" +
                    departamento.getNombre() + "$$" +
                    departamento.getDescripcion() + "$$" +
                    departamento.getUbicacion() + "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_CONFIG, contenido, false);

    }

    public static void guardarProyectos(List<Proyecto> listaProyectos) throws IOException {
        String contenido = "";
        for(Proyecto proyecto : listaProyectos) {
            contenido += proyecto.getId() + "$$" +
                    proyecto.getNombre() + "$$" +
                    proyecto.getIdDepartamentoResponsable() + "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_CONFIG, contenido, false);

    }

    public static ArrayList<Departamento> cargarDepartamentos() throws IOException {
        ArrayList<Departamento> departamentos = new ArrayList<>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_CONFIG);

        for (String linea : contenido) {
            String[] datos = linea.split("$$");
            Departamento departamento = new Departamento();
            departamento.setId(datos[0]);
            departamento.setNombre(datos[1]);
            departamento.setDescripcion(datos[2]);
            departamento.setUbicacion(datos[3]);
        }
        return departamentos;
    }

    public static ArrayList<Proyecto> cargarProyectos() throws IOException {
        ArrayList<Proyecto> proyectos = new ArrayList<>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_CONFIG);

        for (String linea : contenido) {
            String[] datos = linea.split("$$");
            Proyecto proyecto = new Proyecto();
            proyecto.setId(datos[0]);
            proyecto.setNombre(datos[1]);
            proyecto.setIdDepartamentoResponsable(datos[2]);
        }
        return proyectos;
    }

    public static void guardarAsignacion(Asignacion asignacion) throws IOException {
    ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_CONFIG, asignacion);
    }
}