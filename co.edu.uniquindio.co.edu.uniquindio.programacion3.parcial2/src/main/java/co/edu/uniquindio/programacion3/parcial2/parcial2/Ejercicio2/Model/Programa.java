package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio2.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.Serializable;

public class Programa implements Serializable {
    private String codigo;
    private String nombre;
    private String modalidad;
    private transient ObservableList<Programa> listaProgramas = FXCollections.observableArrayList();

    public Programa() {
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

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public ObservableList<Programa> getListaProgramas() {
        return listaProgramas;
    }

    public void setListaProgramas(ObservableList<Programa> listaProgramas) {
        this.listaProgramas = listaProgramas;
    }
}
