package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Controller;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Model.*;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio3.Utils.Persistencia;
import co.edu.uniquindio.programacion3.parcial2.parcial2.RestauranteApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PedidosController implements Initializable {
    @FXML
    private ComboBox<Cliente> cmbClientes;

    @FXML
    private ComboBox<Producto> cmbProductos;

    @FXML
    private TableView<Producto> tblProductosSeleccionados;

    @FXML
    private TableColumn<Producto, String> colCodigo;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, String> colPrecio;

    @FXML
    private Label lblTotal;

    @FXML
    private DatePicker dpFecha;

    private ObservableList<Producto> productosSeleccionados = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // Obtener la instancia del restaurante
            Restaurante restaurante = RestauranteApplication.getRestaurante();

            // Cargar clientes y productos desde el restaurante
            cmbClientes.setItems(FXCollections.observableArrayList(restaurante.getListaClientes()));
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

    @FXML
    void realizarPedido(ActionEvent event) {
        try {
            if (validarDatos()) {
                Pedido pedido = new Pedido();
                pedido.setCliente(cmbClientes.getValue());
                pedido.setFecha(dpFecha.getValue().toString());
                pedido.setProductos(new ArrayList<>(productosSeleccionados));
                pedido.setTotal(lblTotal.getText());

                // Agregar el pedido al restaurante
                RestauranteApplication.getRestaurante().agregarPedido(pedido);

                // Guardar en XML
                Persistencia.guardarPedido(pedido);
                Persistencia.guardaRegistroLog("Pedido realizado exitosamente", 1, "realizarPedido");

                limpiarFormulario();
                mostrarInformacion("Éxito", "Pedido realizado correctamente");
            }
        } catch (Exception e) {
            mostrarError("Error al realizar pedido", e.getMessage());
            Persistencia.guardaRegistroLog("Error al realizar pedido: " + e.getMessage(), 2, "realizarPedido");
        }
    }

    @FXML
    void agregarProducto(ActionEvent event) {
        Producto productoSeleccionado = cmbProductos.getValue();
        if (productoSeleccionado != null) {
            productosSeleccionados.add(productoSeleccionado);
            actualizarTotal();
        } else {
            mostrarError("Error", "Debe seleccionar un producto");
        }
    }

    @FXML
    void quitarProducto(ActionEvent event) {
        Producto productoSeleccionado = tblProductosSeleccionados.getSelectionModel().getSelectedItem();
        if (productoSeleccionado != null) {
            productosSeleccionados.remove(productoSeleccionado);
            actualizarTotal();
        } else {
            mostrarError("Error", "Debe seleccionar un producto de la tabla");
        }
    }

    private boolean validarDatos() {
        if (cmbClientes.getValue() == null) {
            mostrarError("Error", "Debe seleccionar un cliente");
            return false;
        }
        if (productosSeleccionados.isEmpty()) {
            mostrarError("Error", "Debe agregar al menos un producto");
            return false;
        }
        if (dpFecha.getValue() == null) {
            mostrarError("Error", "Debe seleccionar una fecha");
            return false;
        }
        return true;
    }

    private void actualizarTotal() {
        double total = 0;
        for (Producto producto : productosSeleccionados) {
            total += Double.parseDouble(producto.getPrecio());
        }
        lblTotal.setText(String.format("%.2f", total));
    }

    private void limpiarFormulario() {
        cmbClientes.setValue(null);
        cmbProductos.setValue(null);
        productosSeleccionados.clear();
        dpFecha.setValue(LocalDate.now());
        actualizarTotal();
    }

    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarInformacion(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
