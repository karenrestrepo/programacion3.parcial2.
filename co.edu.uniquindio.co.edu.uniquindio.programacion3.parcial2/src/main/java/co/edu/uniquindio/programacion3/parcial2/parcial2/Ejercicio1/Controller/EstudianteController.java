package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Model.Estudiante;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio1.Utils.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class EstudianteController {
    Estudiante estudiante;
    ObservableList<Estudiante> listaEstudiantes = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnBuscar;

    @FXML
    private TableView<Estudiante> tableEstudiantes;

    @FXML
    private TableColumn<Estudiante, String> tcCodigo;

    @FXML
    private TableColumn<Estudiante, String> tcNombre;

    @FXML
    private TableColumn<Estudiante, String> tcNota1;

    @FXML
    private TableColumn<Estudiante, String> tcNota2;

    @FXML
    private TableColumn<Estudiante, String> tcNota3;

    @FXML
    private TextField txtBuscar;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtNota1;

    @FXML
    private TextField txtNota2;

    @FXML
    private TextField txtNota3;

    @FXML
    void onAgregar(ActionEvent event) {
        Estudiante estudiante1 = new Estudiante();
        estudiante1.setCodigo(txtCodigo.getText());
        estudiante1.setNombre(txtNombre.getText());
        estudiante1.setNota1(txtNota1.getText());
        estudiante1.setNota2(txtNota2.getText());
        estudiante1.setNota3(txtNota3.getText());

        listaEstudiantes.add(estudiante1);
        limpiarCamposEstudiante();
        registrarAcciones("Estudiante agregado", 1, "Agregar estudiante");
    }

    private void registrarAcciones(String mensaje, int nivel, String accion) {
        Persistencia.guardaRegistroLog(mensaje, nivel, accion);

    }

    private void limpiarCamposEstudiante() {
        txtCodigo.setText("");
        txtNombre.setText("");
        txtNota1.setText("");
        txtNota2.setText("");
        txtNota3.setText("");
    }

    @FXML
    void onBuscar(ActionEvent event) {

    }

    @FXML
    void initialize() {
        initDataBinding();
        obtenerEstudiates();
        tableEstudiantes.getItems().clear();
        tableEstudiantes.setItems(listaEstudiantes);
        listenerSelection();
    }

    private void initDataBinding() {
        tcCodigo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigo()));
        tcNombre.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombre()));
        tcNota1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNota1()));
        tcNota2.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNota2()));
        tcNota3.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNota3()));
    }

    private void obtenerEstudiates() {
        listaEstudiantes.addAll(EstudianteUtils.inicializarDatos());
    }

    private void listenerSelection() {
        tableEstudiantes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            estudiante = newSelection;
            mostrarInformacionEstudiante(estudiante);
        });
    }

    private void mostrarInformacionEstudiante(Estudiante estudianteSeleccionado) {
        if(estudianteSeleccionado != null){
            txtCodigo.setText(estudianteSeleccionado.getCodigo());
            txtNombre.setText(estudianteSeleccionado.getNombre());
            txtNota1.setText(estudianteSeleccionado.getNota1());
            txtNota2.setText(estudianteSeleccionado.getNota2());
            txtNota3.setText(estudianteSeleccionado.getNota3());
        }
    }

}


