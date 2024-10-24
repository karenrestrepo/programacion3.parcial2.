package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Model;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Utils.*;

import java.util.ArrayList;
import java.util.List;

public class Restaurante {
    private List<Cliente> listaClientes;
    private List<Producto> listaProductos;
    private List<Pedido> listaPedidos;

    public Restaurante() {
        this.listaClientes = new ArrayList<>();
        this.listaProductos = new ArrayList<>();
        this.listaPedidos = new ArrayList<>();
    }

    // Getters y setters
    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public List<Pedido> getListaPedidos() {
        return listaPedidos;
    }

    public void setListaPedidos(List<Pedido> listaPedidos) {
        this.listaPedidos = listaPedidos;
    }

    // Métodos para gestionar clientes
    public void agregarCliente(Cliente cliente) {
        listaClientes.add(cliente);
    }

    public void eliminarCliente(Cliente cliente) {
        listaClientes.remove(cliente);
    }

    public Cliente buscarCliente(String cedula) {
        return listaClientes.stream()
                .filter(c -> c.getCedula().equals(cedula))
                .findFirst()
                .orElse(null);
    }

    // Métodos para gestionar productos
    public void agregarProducto(Producto producto) {
        listaProductos.add(producto);
    }

    public void eliminarProducto(Producto producto) {
        listaProductos.remove(producto);
    }

    public Producto buscarProducto(String codigo) {
        return listaProductos.stream()
                .filter(p -> p.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    // Métodos para gestionar pedidos
    public void agregarPedido(Pedido pedido) {
        listaPedidos.add(pedido);
    }

    public void eliminarPedido(Pedido pedido) {
        listaPedidos.remove(pedido);
    }

    // Método para inicializar datos de prueba
    public void cargarDatosIniciales() throws Exception {
        // Cargar datos desde los archivos si existen
        try {
            this.listaClientes = Persistencia.cargarClientes();
            this.listaProductos = Persistencia.cargarProductos();
        } catch (Exception e) {
            // Si hay error al cargar, usar datos quemados
            RestauranteUtil.inicializarDatos(this);
        }
    }
}
