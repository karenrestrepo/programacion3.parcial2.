package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio4.Controller;

import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio4.Dto.AsignacionMateriasDto;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio4.Mapping.AsignacionMateriasMapper;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio4.Model.*;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio4.Utils.Persistencia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AsignacionMateriasController {
    @FXML
    private TextField txtCodigoAsignacion;

    @FXML
    private TextField txtCodigoMateria;

    @FXML
    private TextField txtNombreMateria;

    @FXML
    private TextField txtCodigoDocente;

    @FXML
    private TextField txtCodigosEstudiantes;

    @FXML
    void onAsignar(ActionEvent event) {
        try {
            AsignacionMateriasDto asignacionMateriasDto = construirAsignacionMateriasDto();

            AsignacionMaterias asignacion = mapearDtoAEntidad(asignacionMateriasDto);
            guardarAsignacion(asignacion);

            mostrarAlerta("Asignación exitosa", "La materia fue asignada correctamente.");
        } catch (Exception e) {
            mostrarAlerta("Error en la asignación", "Ocurrió un error al asignar la materia.");
            e.printStackTrace();
        }
    }

    private AsignacionMateriasDto construirAsignacionMateriasDto() {
        return new AsignacionMateriasDto(
                txtCodigoAsignacion.getText(),
                txtCodigoMateria.getText(),
                txtNombreMateria.getText(),
                txtCodigoDocente.getText(),
                txtCodigosEstudiantes.getText().split(",")
        );
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Implementar métodos de conversión y guardado como ya se indicó
    private AsignacionMaterias mapearDtoAEntidad(AsignacionMateriasDto dto) throws IOException {
        Materia materia = buscarMateriaPorCodigo(dto.codigoMateria());
        Docente docente = buscarDocentePorCodigo(dto.codigoDocente());
        List<Estudiante> estudiantes = buscarEstudiantesPorCodigos(dto.codigosEstudiantes());

        // Validar si se encontraron todos los elementos
        if (materia == null) {
            mostrarAlerta("Error", "No se encontró la materia con el código: " + dto.codigoMateria());
            return null;
        }
        if (docente == null) {
            mostrarAlerta("Error", "No se encontró el docente con el código: " + dto.codigoDocente());
            return null;
        }
        if (estudiantes.isEmpty()) {
            mostrarAlerta("Error", "No se encontraron los estudiantes con los códigos proporcionados.");
            return null;
        }

        // Crear la entidad AsignacionMaterias con los objetos asociados
        return new AsignacionMaterias(dto.codigoAsignacion(), materia, docente, estudiantes);
    }

    // Buscar Materia por código
    private Materia buscarMateriaPorCodigo(String codigo) throws IOException {
        List<Materia> materias = Persistencia.cargarMaterias(Persistencia.RUTA_ARCHIVO_MATERIAS);
        return materias.stream().filter(m -> m.getCodigo().equals(codigo)).findFirst().orElse(null);
    }

    // Buscar Docente por código
    private Docente buscarDocentePorCodigo(String codigo) throws IOException {
        List<Docente> docentes = Persistencia.cargarDocentes(Persistencia.RUTA_ARCHIVO_DOCENTES);
        return docentes.stream().filter(d -> d.getCodigo().equals(codigo)).findFirst().orElse(null);
    }

    // Buscar Estudiantes por sus códigos
    private List<Estudiante> buscarEstudiantesPorCodigos(String[] codigos) throws IOException {
        List<Estudiante> estudiantes = Persistencia.cargarEstudiantes(Persistencia.RUTA_ARCHIVO_ESTUDIANTES);
        List<Estudiante> estudiantesEncontrados = new ArrayList<>();

        for (String codigo : codigos) {
            estudiantes.stream().filter(e -> e.getCodigo().equals(codigo.trim()))
                    .findFirst().ifPresent(estudiantesEncontrados::add);
        }

        return estudiantesEncontrados;
    }

    private void guardarAsignacion(AsignacionMaterias asignacion) throws IOException {
        String contenido = formatoAsignacion(asignacion);
        Persistencia.guardarAsignacion(contenido);
        Persistencia.guardaRegistroLog("Asignación guardada: " + asignacion.getCodigoAsignacion(), 1, "Asignar");
    }

    // Formato para guardar la asignación en el archivo de texto
    private String formatoAsignacion(AsignacionMaterias asignacion) {
        return asignacion.getCodigoAsignacion() + "@@" + asignacion.getMateria().getCodigo() + "@@" +
                asignacion.getMateria().getNombre() + "@@" + asignacion.getDocente().getCodigo() + "@@" +
                String.join("@@", asignacion.getEstudiantes().stream().map(e -> e.getCodigo()).toArray(String[]::new));
    }
}