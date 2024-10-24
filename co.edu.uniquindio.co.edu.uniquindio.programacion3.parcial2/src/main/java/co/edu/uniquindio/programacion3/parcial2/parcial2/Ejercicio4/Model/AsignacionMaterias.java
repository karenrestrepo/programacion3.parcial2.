package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio4.Model;

import java.util.List;

public class AsignacionMaterias {
    private String codigoAsignacion;
    private Materia materia;
    private Docente docente;
    private List<Estudiante> estudiantes;

    public AsignacionMaterias() {
    }

    public AsignacionMaterias(String s, Materia materia, Docente docente, List<Estudiante> estudiantes) {
    }

    public String getCodigoAsignacion() {
        return codigoAsignacion;
    }

    public void setCodigoAsignacion(String codigoAsignacion) {
        this.codigoAsignacion = codigoAsignacion;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public List<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    public void setEstudiantes(List<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
    }
}
