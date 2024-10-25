package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Model;

import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Utils.EmpresaSoftwareUtil;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Utils.Persistencia;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Utils.RestauranteUtil;

import java.util.List;

public class EmpresaSoftware {
    private List<Empleado> listaEmpleados;
    private List<Departamento> listaDepartamentos;
    private List<Proyecto> listaProyectos;

    public EmpresaSoftware(List<Empleado> listaEmpleados, List<Departamento> listaDepartamentos, List<Proyecto> listaProyecctos) {
        this.listaEmpleados = listaEmpleados;
        this.listaDepartamentos = listaDepartamentos;
        this.listaProyectos = listaProyecctos;
    }

    public EmpresaSoftware() {

    }

    public List<Empleado> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<Empleado> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
    }

    public List<Departamento> getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void setListaDepartamentos(List<Departamento> listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public List<Proyecto> getListaProyectos() {
        return listaProyectos;
    }

    public void setListaProyectos(List<Proyecto> listaProyectos) {
        this.listaProyectos = listaProyectos;
    }

    public void cargarDatosIniciales() throws Exception {
        // Cargar datos desde los archivos si existen
        try {
            this.listaEmpleados = Persistencia.cargarEmpleados();
            this.listaDepartamentos = Persistencia.cargarDepartamentos();
            this.listaProyectos = Persistencia.cargarProyectos();
        } catch (Exception e) {
            // Si hay error al cargar, usar datos quemados
            EmpresaSoftwareUtil.inicializarDatos(this);
        }

    }

    public void agregarAsignacion(Asignacion asignacion) {
    }
}
