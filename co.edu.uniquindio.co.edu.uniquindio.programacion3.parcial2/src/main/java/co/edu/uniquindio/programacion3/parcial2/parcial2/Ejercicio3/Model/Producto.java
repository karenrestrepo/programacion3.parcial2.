package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Model;

public class Producto {
    private String codigo;
    private String nombre;
    private String precio;

    public Producto() {
    }

    // Getters y Setters
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
