package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Utils;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Model.*;
import java.util.ArrayList;
import java.util.Arrays;

public class RestauranteUtil {

    public static void inicializarDatos(Restaurante restaurante) throws Exception {
        // Inicializar clientes
        inicializarClientes(restaurante);

        // Inicializar productos
        inicializarProductos(restaurante);

        // Guardar datos inicializados en archivos
        guardarDatos(restaurante);
    }

    private static void inicializarClientes(Restaurante restaurante) {
        Cliente cliente1 = new Cliente();
        cliente1.setCodigo("C001");
        cliente1.setCedula("1094956324");
        cliente1.setTipoIdentificacion("CC");
        cliente1.setNombre("Juan");
        cliente1.setApellidos("Pérez González");
        cliente1.setTelefono("3156789012");

        Cliente cliente2 = new Cliente();
        cliente2.setCodigo("C002");
        cliente2.setCedula("1097845632");
        cliente2.setTipoIdentificacion("CC");
        cliente2.setNombre("María");
        cliente2.setApellidos("López Rodríguez");
        cliente2.setTelefono("3187894561");

        Cliente cliente3 = new Cliente();
        cliente3.setCodigo("C003");
        cliente3.setCedula("1096321478");
        cliente3.setTipoIdentificacion("CC");
        cliente3.setNombre("Carlos");
        cliente3.setApellidos("Ramírez Muñoz");
        cliente3.setTelefono("3203216547");

        restaurante.setListaClientes(new ArrayList<>(Arrays.asList(cliente1, cliente2, cliente3)));
    }

    private static void inicializarProductos(Restaurante restaurante) {
        Producto producto1 = new Producto();
        producto1.setCodigo("P001");
        producto1.setNombre("Hamburguesa Clásica");
        producto1.setPrecio("15000");

        Producto producto2 = new Producto();
        producto2.setCodigo("P002");
        producto2.setNombre("Pizza Mediana");
        producto2.setPrecio("25000");

        Producto producto3 = new Producto();
        producto3.setCodigo("P003");
        producto3.setNombre("Perro Caliente");
        producto3.setPrecio("12000");

        Producto producto4 = new Producto();
        producto4.setCodigo("P004");
        producto4.setNombre("Lasaña");
        producto4.setPrecio("28000");

        Producto producto5 = new Producto();
        producto5.setCodigo("P005");
        producto5.setNombre("Ensalada César");
        producto5.setPrecio("18000");

        restaurante.setListaProductos(new ArrayList<>(Arrays.asList(
                producto1, producto2, producto3, producto4, producto5
        )));
    }

    private static void guardarDatos(Restaurante restaurante) throws Exception {
        // Guardar clientes en archivo
        Persistencia.guardarClientes(restaurante.getListaClientes());

        // Guardar productos en archivo
        Persistencia.guardarProductos(restaurante.getListaProductos());

        // Registrar en log la inicialización de datos
        Persistencia.guardaRegistroLog(
                "Datos iniciales cargados exitosamente",
                1,
                "inicializarDatos"
        );
    }
}
