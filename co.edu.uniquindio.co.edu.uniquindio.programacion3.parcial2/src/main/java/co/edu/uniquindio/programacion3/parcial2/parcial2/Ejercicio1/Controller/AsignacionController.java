package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Model.*;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Model.Producto;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Model.Restaurante;
import co.edu.uniquindio.programacion3.parcial2.parcial2.RestauranteApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class AsignacionController {

    private ObservableList<Empleado> empleadoSeleccionados = FXCollections.observableArrayList();
    private ObservableList<Departamento> departamentoSeleccionados = FXCollections.observableArrayList();
    private ObservableList<Proyecto> proyectoSeleccionados = FXCollections.observableArrayList();

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

    }

    @FXML
    void initialize(URL url, ResourceBundle rb) {
            try {
                // Obtener la instancia del restaurante
                EmpresaSoftware empresaSoftware = EmpresaSoftwareApplication.getEmpresaSoftware();

                // Cargar clientes y productos desde el restaurante
                cmbEmpleado.setItems(FXCollections.observableArrayList(empresaSoftware.getListaEmpleados()));
                cmbProductos.setItems(FXCollections.observableArrayList(restaurante.getListaProductos()));

                // El resto del código permanece igual
                colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
                colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

                tblProductosSeleccionados.setItems(productosSeleccionados);
                dpFecha.setValue(LocalDate.now());
                actualizarTotal();

            } catch (Exception e) {
                mostrarError("Error al inicializar", e.getMessage());
            }

    }

}