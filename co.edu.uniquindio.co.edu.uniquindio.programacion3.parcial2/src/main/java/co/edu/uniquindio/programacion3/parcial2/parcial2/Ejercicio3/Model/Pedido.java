package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Model;

import java.util.List;

public class Pedido {
    private String fecha;
    private String total;
    private Cliente cliente;
    private List<Producto> productos;

    public Pedido() {
    }

    // Getters y Setters
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }
}
