package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Model;

public class Asignacion {
    private Empleado empleado;
    private Proyecto proyecto;
    private Departamento departamento;

    public Asignacion(){}

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
}
