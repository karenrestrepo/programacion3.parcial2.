package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Model.*;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Model.Pedido;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Model.Producto;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Utils.Persistencia;
import co.edu.uniquindio.programacion3.parcial2.parcial2.EmpresaSoftwareApplication;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Model.Restaurante;
import co.edu.uniquindio.programacion3.parcial2.parcial2.RestauranteApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class AsignacionController {

    private ObservableList<Asignacion> asignacionSeleccionada = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAsignar;

    @FXML
    private ComboBox<Departamento> cmbDepartamento;

    @FXML
    private ComboBox<Empleado> cmbEmpleado;

    @FXML
    private ComboBox<Proyecto> cmbProyecto;

    @FXML
    private TableColumn<Departamento, String> colDepartamento;

    @FXML
    private TableColumn<Empleado, String> colEmpleado;

    @FXML
    private TableColumn<Proyecto, String> colProyecto;

    @FXML
    private TableView<Asignacion> tbAsignacion;

    @FXML
    void onAsignacion(ActionEvent event) {
        try {
            if (validarDatos()) {
                Asignacion asignacion = new Asignacion();
                asignacion.setEmpleado(cmbEmpleado.getValue());
                asignacion.setDepartamento(cmbDepartamento.getValue());
                asignacion.setProyecto(cmbProyecto.getValue());

                // Agregar el pedido al restaurante
                EmpresaSoftwareApplication.getEmpresaSoftware().agregarAsignacion(asignacion);

                // Guardar en XML
                Persistencia.guardarAsignacion(asignacion);
                Persistencia.guardaRegistroLog("Asignación realizada exitosamente", 1, "realizarAsignacion");

                limpiarFormulario();
                mostrarInformacion("Éxito", "Asignación realizada correctamente");
            }
        } catch (Exception e) {
            mostrarError("Error al realizar asignación", e.getMessage());
            Persistencia.guardaRegistroLog("Error al realizar asignación: " + e.getMessage(), 2, "onAsignacion");
        }
    }

    private void mostrarInformacion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void limpiarFormulario() {
        cmbEmpleado.setValue(null);
        cmbDepartamento.setValue(null);
        cmbProyecto.setValue(null);
    }

    private boolean validarDatos() {
        if (cmbEmpleado.getValue() == null) {
            mostrarError("Error", "Debe seleccionar un empleado");
            return false;
        }
        if (cmbDepartamento.getValue() == null) {
            mostrarError("Error", "Debe seleccionar un departamento");
            return false;}

        if (cmbEmpleado.getValue() == null) {
            mostrarError("Error", "Debe seleccionar un proyecto");
            return false;
        }
        return true;
    }

    @FXML
    void initialize(URL url, ResourceBundle rb) {
            try {
                // Obtener la instancia del restaurante
                EmpresaSoftware empresaSoftware = EmpresaSoftwareApplication.getEmpresaSoftware();

                // Cargar clientes y productos desde el restaurante
                cmbEmpleado.setItems(FXCollections.observableArrayList(empresaSoftware.getListaEmpleados()));
                cmbProyecto.setItems(FXCollections.observableArrayList(empresaSoftware.getListaProyectos()));
                cmbDepartamento.setItems(FXCollections.observableArrayList(empresaSoftware.getListaDepartamentos()));
                // El resto del código permanece igual
                colEmpleado.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                colDepartamento.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                colProyecto.setCellValueFactory(new PropertyValueFactory<>("nombre"));

                tbAsignacion.setItems(asignacionSeleccionada);

            } catch (Exception e) {
                mostrarError("Error al inicializar", e.getMessage());
            }

    }

    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

}