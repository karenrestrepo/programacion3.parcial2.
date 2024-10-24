package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Utils;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Model.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
public class Persistencia {
    public static final String RUTA_ARCHIVO_LOG = "src/main/resources/Persistencia/log/RestauranteLog.txt";
    private static final String RUTA_ARCHIVO_CLIENTES = "src/main/resources/Persistencia/clientes.txt";
    private static final String RUTA_ARCHIVO_PRODUCTOS = "src/main/resources/Persistencia/productos.txt";
    private static final String RUTA_ARCHIVO_PEDIDOS = "src/main/resources/Persistencia/pedidos.xml";
    private static final String RUTA_ARCHIVO_CONFIG = "src/main/resources/config.properties";

    public static void guardaRegistroLog(String mensajeLog, int nivel, String accion) {
        ArchivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, RUTA_ARCHIVO_LOG);
    }

    public static void guardarClientes(List<Cliente> listaClientes) throws IOException {
        String contenido = "";
        for(Cliente cliente : listaClientes) {
            contenido += cliente.getCodigo() + "@" +
                    cliente.getCedula() + "@" +
                    cliente.getTipoIdentificacion() + "@" +
                    cliente.getNombre() + "@" +
                    cliente.getApellidos() + "@" +
                    cliente.getTelefono() + "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_CLIENTES, contenido, false);
    }

    public static ArrayList<Cliente> cargarClientes() throws IOException {
        ArrayList<Cliente> clientes = new ArrayList<>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_CLIENTES);

        for (String linea : contenido) {
            String[] datos = linea.split("@");
            Cliente cliente = new Cliente();
            cliente.setCodigo(datos[0]);
            cliente.setCedula(datos[1]);
            cliente.setTipoIdentificacion(datos[2]);
            cliente.setNombre(datos[3]);
            cliente.setApellidos(datos[4]);
            cliente.setTelefono(datos[5]);
            clientes.add(cliente);
        }
        return clientes;
    }

    public static void guardarProductos(List<Producto> listaProductos) throws IOException {
        String contenido = "";
        for(Producto producto : listaProductos) {
            contenido += producto.getCodigo() + "#" +
                    producto.getNombre() + "#" +
                    producto.getPrecio() + "\n";
        }
        ArchivoUtil.guardarArchivo(RUTA_ARCHIVO_PRODUCTOS, contenido, false);
    }

    public static ArrayList<Producto> cargarProductos() throws IOException {
        ArrayList<Producto> productos = new ArrayList<>();
        ArrayList<String> contenido = ArchivoUtil.leerArchivo(RUTA_ARCHIVO_PRODUCTOS);

        for (String linea : contenido) {
            String[] datos = linea.split("#");
            Producto producto = new Producto();
            producto.setCodigo(datos[0]);
            producto.setNombre(datos[1]);
            producto.setPrecio(datos[2]);
            productos.add(producto);
        }
        return productos;
    }

    public static void guardarPedido(Pedido pedido) throws Exception {
        // Usar XMLEncoder para guardar el pedido
        ArchivoUtil.salvarRecursoSerializadoXML(RUTA_ARCHIVO_PEDIDOS, pedido);
    }

    public static boolean validarCredenciales(String usuario, String contrasenia) throws IOException {
        Properties prop = new Properties();
        prop.load(new java.io.FileInputStream(RUTA_ARCHIVO_CONFIG));
        return usuario.equals(prop.getProperty("usuario")) &&
                contrasenia.equals(prop.getProperty("contrasenia"));
    }
}
