package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio2.Controller;

import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio2.Model.Programa;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio2.Utils.Persistencia;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.util.List;
import java.util.ResourceBundle;

public class ProgramaController {
    Programa programa;
    ObservableList<Programa> listaProgramas = FXCollections.observableArrayList();
    ObservableList<String> modalidades = FXCollections.observableArrayList();
    ResourceBundle modalidadesBundle;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnBuscar;

    @FXML
    private TableView<Programa> tableProgramas;

    @FXML
    private TableColumn<Programa, String> tcCodigo;

    @FXML
    private TableColumn<Programa, String> tcNombre;

    @FXML
    private TableColumn<Programa, String> tcModalidad;

    @FXML
    private TextField txtBuscar;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private ComboBox<String> cbModalidad;

    @FXML
    void initialize() {
        initDataBinding();
        cargarModalidades();
        cargarDatosGuardados();
        tableProgramas.setItems(listaProgramas);
        listenerSelection();
    }

    private void cargarModalidades() {
        try {
            modalidadesBundle = ResourceBundle.getBundle("modalidades");
            modalidades.add(modalidadesBundle.getString("modalidad.presencial"));
            modalidades.add(modalidadesBundle.getString("modalidad.distancia"));
            cbModalidad.setItems(modalidades);
        } catch (Exception e) {
            mostrarError("Error al cargar las modalidades");
        }
    }

    private void initDataBinding() {
        tcCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcModalidad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModalidad()));
    }

    private void cargarDatosGuardados() {
        try {
            List<Programa> programasCargados = Persistencia.cargarProgramas();
            listaProgramas.clear();
            listaProgramas.addAll(programasCargados);
        } catch (Exception e) {
            mostrarError("Error al cargar los programas");
        }
    }

    @FXML
    void onAgregar(ActionEvent event) {
        try {
            if(validarCampos()) {
                Programa programa = new Programa();
                programa.setCodigo(txtCodigo.getText());
                programa.setNombre(txtNombre.getText());
                programa.setModalidad(cbModalidad.getValue());

                listaProgramas.add(programa);
                Persistencia.guardarProgramas(listaProgramas);

                limpiarCampos();
                registrarAccion("Programa agregado", 1, "Agregar programa");
                mostrarMensaje("Éxito", "Programa agregado correctamente");
            }
        } catch (Exception e) {
            mostrarError("Error al guardar el programa");
        }
    }

    @FXML
    void onBuscar(ActionEvent event) {
        String criterioBusqueda = txtBuscar.getText().trim().toLowerCase();

        if (criterioBusqueda.isEmpty()) {
            mostrarError("Ingrese un código o nombre para buscar");
            return;
        }

        try {
            List<Programa> programasCargados = Persistencia.cargarProgramas();
            ObservableList<Programa> resultados = FXCollections.observableArrayList();

            for (Programa programa : programasCargados) {
                if (coincideConCriterio(programa, criterioBusqueda)) {
                    resultados.add(programa);
                }
            }

            actualizarResultadosBusqueda(resultados);
            registrarAccion("Búsqueda realizada: " + criterioBusqueda, 1, "Buscar programa");

        } catch (Exception e) {
            mostrarError("Error al buscar programa");
            registrarAccion("Error en búsqueda: " + e.getMessage(), 3, "Error búsqueda");
        }
    }

    private boolean coincideConCriterio(Programa programa, String criterio) {
        return programa.getCodigo().toLowerCase().contains(criterio) ||
                programa.getNombre().toLowerCase().contains(criterio);
    }

    private void actualizarResultadosBusqueda(ObservableList<Programa> resultados) {
        tableProgramas.setItems(resultados);
        if (resultados.isEmpty()) {
            mostrarMensaje("Búsqueda", "No se encontraron programas");
        }
    }

    private boolean validarCampos() {
        if (txtCodigo.getText().trim().isEmpty() ||
                txtNombre.getText().trim().isEmpty() ||
                cbModalidad.getValue() == null) {
            mostrarError("Todos los campos son obligatorios");
            return false;
        }
        return true;
    }

    private void limpiarCampos() {
        txtCodigo.clear();
        txtNombre.clear();
        cbModalidad.setValue(null);
    }

    private void listenerSelection() {
        tableProgramas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if(newSelection != null) {
                mostrarInformacionPrograma(newSelection);
            }
        });
    }

    private void mostrarInformacionPrograma(Programa programa) {
        txtCodigo.setText(programa.getCodigo());
        txtNombre.setText(programa.getNombre());
        cbModalidad.setValue(programa.getModalidad());
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void registrarAccion(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);
    }
}
