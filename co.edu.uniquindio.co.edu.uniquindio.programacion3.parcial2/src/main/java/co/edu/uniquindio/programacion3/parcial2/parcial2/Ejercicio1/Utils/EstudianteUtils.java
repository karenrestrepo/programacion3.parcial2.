package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Utils;

import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Model.Estudiante;

import java.util.ArrayList;
import java.util.List;

public class EstudianteUtils {

    public static List<Estudiante> inicializarDatos() {
        List<Estudiante> estudiantes = new ArrayList<>();

        Estudiante estudiante1 = new Estudiante();
        estudiante1.setCodigo("354685");
        estudiante1.setNombre("Julio Marin");
        estudiante1.setNota1("4");
        estudiante1.setNota2("5");
        estudiante1.setNota3("3.8");
        estudiantes.add(estudiante1);

        // Agregar más estudiantes si es necesario

        return estudiantes;
    }
}