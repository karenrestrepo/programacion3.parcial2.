package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Utils;

import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Model.Empleado;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Model.EmpresaSoftware;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Model.Proyecto;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Model.Cliente;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Model.Producto;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Model.Restaurante;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Utils.Persistencia;

import java.util.ArrayList;
import java.util.Arrays;

public class EmpresaSoftwareUtil {
    public static void inicializarDatos(EmpresaSoftware empresaSoftware) throws Exception {
        // Inicializar empleados
        inicializarEmpleados(empresaSoftware);

        // Inicializar proyectos
        inicializarProyectos(empresaSoftware);

        // Inicializar departamentos
        inicializarDepartamentos(empresaSoftware);

        // Guardar datos inicializados en archivos
        guardarDatos(empresaSoftware);
    }

    private static void inicializarDepartamentos(EmpresaSoftware empresaSoftware) {
    }

    private static void inicializarEmpleados(EmpresaSoftware empresaSoftware) {
        Empleado empleado1 = new Empleado();
        empleado1.setId("1094956324");
        empleado1.setNombre("Juan");
        empleado1.setApellido("Mora");
        empleado1.setIdDepartamento("01D");

        Empleado empleado2 = new Empleado();
        empleado2.setId("1094956324");
        empleado2.setNombre("María");
        empleado2.setApellido("López");
        empleado2.setIdDepartamento("02D");

        Empleado empleado3 = new Empleado();
        empleado3.setId("1096321478");
        empleado3.setNombre("Carlos");
        empleado3.setApellido("Ramírez");
        empleado3.setIdDepartamento("03D");

        empresaSoftware.setListaEmpleados(new ArrayList<>(Arrays.asList(empleado1, empleado2, empleado3)));
    }

    private static void inicializarProyectos(EmpresaSoftware empresaSoftware) {
        Proyecto proyecto1 = new Proyecto();
        proyecto1.setId("P001");
        proyecto1.setNombre("App mensajería");
        proyecto1.setIdDepartamentoResponsable("01D");

        Proyecto proyecto2 = new Proyecto();
        proyecto2.setId("P002");
        proyecto2.setNombre("App citas");
        proyecto2.setIdDepartamentoResponsable("02D");

        Proyecto proyecto3 = new Proyecto();
        proyecto3.setId("P003");
        proyecto3.setNombre("App domicilios");
        proyecto3.setIdDepartamentoResponsable("03D");

        empresaSoftware.setListaProyectos(new ArrayList<>(Arrays.asList(
                proyecto1, proyecto2, proyecto3
        )));
    }

    private static void guardarDatos(EmpresaSoftware empresaSoftware) throws Exception {
        // Guardar clientes en archivo
        Persistencia.guardarEmpleados(empresaSoftware.getListaEmpleados());

        // Guardar productos en archivo
        Persistencia.guardarDepartamentos(empresaSoftware.getListaDepartamentos());

        Persistencia.guardarProyectos(empresaSoftware.getListaProyectos());

        // Registrar en log la inicialización de datos
        Persistencia.guardaRegistroLog(
                "Datos iniciales cargados exitosamente",
                1,
                "inicializarDatos"
        );
    }
}

