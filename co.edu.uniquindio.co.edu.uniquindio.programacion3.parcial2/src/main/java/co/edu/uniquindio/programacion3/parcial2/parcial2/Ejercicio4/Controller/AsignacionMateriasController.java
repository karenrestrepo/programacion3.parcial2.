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

import java.io.File;
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
    public void initialize() {
        verificarArchivosExisten();
    }

    private void verificarArchivosExisten() {
        try {
            File materias = new File(Persistencia.RUTA_ARCHIVO_MATERIAS);
            File docentes = new File(Persistencia.RUTA_ARCHIVO_DOCENTES);
            File estudiantes = new File(Persistencia.RUTA_ARCHIVO_ESTUDIANTES);

            if (!materias.exists()) {
                mostrarAlerta("Error", "No existe el archivo de materias");
            }
            if (!docentes.exists()) {
                mostrarAlerta("Error", "No existe el archivo de docentes");
            }
            if (!estudiantes.exists()) {
                mostrarAlerta("Error", "No existe el archivo de estudiantes");
            }
        } catch (Exception e) {
            mostrarAlerta("Error", "Error al verificar los archivos de datos");
        }
    }

    @FXML
    void onAsignar(ActionEvent event) {
        try {
            // Validar que los campos no estén vacíos
            if (txtCodigoAsignacion.getText().isEmpty() ||
                    txtCodigoMateria.getText().isEmpty() ||
                    txtNombreMateria.getText().isEmpty() ||
                    txtCodigoDocente.getText().isEmpty() ||
                    txtCodigosEstudiantes.getText().isEmpty()) {

                mostrarAlerta("Error", "Todos los campos son obligatorios");
                return;
            }

            AsignacionMateriasDto dto = construirAsignacionMateriasDto();
            if (dto == null) {
                mostrarAlerta("Error", "No se pudo construir la asignación");
                return;
            }

            AsignacionMaterias asignacion = mapearDtoAEntidad(dto);
            if (asignacion == null) {
                // No mostramos alerta aquí porque mapearDtoAEntidad ya lo hace
                return;
            }

            if (asignacion.getMateria() == null) {
                mostrarAlerta("Error", "No se encontró la materia especificada");
                return;
            }

            guardarAsignacion(asignacion);
            mostrarAlerta("Éxito", "La materia fue asignada correctamente.");

        } catch (Exception e) {
            mostrarAlerta("Error", "Error al asignar la materia: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private AsignacionMateriasDto construirAsignacionMateriasDto() {
        String codigoAsignacion = txtCodigoAsignacion.getText();
        String codigoMateria = txtCodigoMateria.getText();
        String nombreMateria = txtNombreMateria.getText();
        String codigoDocente = txtCodigoDocente.getText();
        String codigosEstudiantesTexto = txtCodigosEstudiantes.getText();

        // Validar que ningún campo esté vacío
        if (codigoAsignacion.isEmpty() || codigoMateria.isEmpty() ||
                nombreMateria.isEmpty() || codigoDocente.isEmpty() ||
                codigosEstudiantesTexto.isEmpty()) {
            mostrarAlerta("Error", "Todos los campos son obligatorios");
            return null;
        }

        return new AsignacionMateriasDto(
                codigoAsignacion,
                codigoMateria,
                nombreMateria,
                codigoDocente,
                codigosEstudiantesTexto.split(",")
        );}

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    // Implementar métodos de conversión y guardado como ya se indicó
    private AsignacionMaterias mapearDtoAEntidad(AsignacionMateriasDto dto) throws IOException {
        Materia materia = buscarMateriaPorCodigo(dto.codigoMateria());
        if (materia == null) {
            mostrarAlerta("Error", "No se encontró la materia con código: " + dto.codigoMateria());
            return null;
        }

        // Buscar el docente
        Docente docente = buscarDocentePorCodigo(dto.codigoDocente());
        if (docente == null) {
            mostrarAlerta("Error", "No se encontró el docente con código: " + dto.codigoDocente());
            return null;
        }

        // Buscar los estudiantes
        List<Estudiante> estudiantes = buscarEstudiantesPorCodigos(dto.codigosEstudiantes());
        if (estudiantes.isEmpty()) {
            mostrarAlerta("Error", "No se encontraron los estudiantes con los códigos proporcionados");
            return null;
        }

        // Crear y retornar la asignación
        return new AsignacionMaterias(dto.codigoAsignacion(), materia, docente, estudiantes);
    }

    // Buscar Materia por código
    private Materia buscarMateriaPorCodigo(String codigo) throws IOException {
        List<Materia> materias = Persistencia.cargarMaterias(Persistencia.RUTA_ARCHIVO_MATERIAS);

        if (materias.isEmpty()) {
            mostrarAlerta("Error", "No hay materias registradas en el sistema");
            return null;
        }

        // Limpiar el código de entrada
        String codigoBusqueda = codigo.trim();

        // Imprimir para debug
        System.out.println("Buscando materia con código: " + codigoBusqueda);
        System.out.println("Materias disponibles:");
        materias.forEach(m -> System.out.println("Código: '" + m.getCodigo() + "' Nombre: '" + m.getNombre() + "'"));

        // Buscar la materia comparando solo la parte numérica del código
        Materia materia = materias.stream()
                .filter(m -> {
                    String codigoMateria = m.getCodigo().split("@@")[0].trim();
                    boolean matches = codigoMateria.equals(codigoBusqueda);
                    System.out.println("Comparando: '" + codigoMateria + "' con '" + codigoBusqueda + "' = " + matches);
                    return matches;
                })
                .findFirst()
                .orElse(null);

        if (materia == null) {
            mostrarAlerta("Error", "No se encontró la materia con código: " + codigo);
        } else {
            System.out.println("Materia encontrada: " + materia.getCodigo() + " - " + materia.getNombre());
        }

        return materia;
    }

    private Docente buscarDocentePorCodigo(String codigo) throws IOException {
        List<Docente> docentes = Persistencia.cargarDocentes(Persistencia.RUTA_ARCHIVO_DOCENTES);

        if (docentes.isEmpty()) {
            mostrarAlerta("Error", "No hay docentes registrados en el sistema");
            return null;
        }

        Docente docente = docentes.stream()
                .filter(d -> d.getCodigo().trim().equals(codigo.trim()))
                .findFirst()
                .orElse(null);

        if (docente == null) {
            mostrarAlerta("Error", "No se encontró el docente con código: " + codigo);
        }

        return docente;
    }

    private List<Estudiante> buscarEstudiantesPorCodigos(String[] codigos) throws IOException {
        List<Estudiante> estudiantes = Persistencia.cargarEstudiantes(Persistencia.RUTA_ARCHIVO_ESTUDIANTES);
        List<Estudiante> estudiantesEncontrados = new ArrayList<>();

        if (estudiantes.isEmpty()) {
            mostrarAlerta("Error", "No hay estudiantes registrados en el sistema");
            return estudiantesEncontrados;
        }

        for (String codigo : codigos) {
            String codigoLimpio = codigo.trim();
            estudiantes.stream()
                    .filter(e -> e.getCodigo().trim().equals(codigoLimpio))
                    .findFirst()
                    .ifPresent(estudiantesEncontrados::add);
        }

        if (estudiantesEncontrados.isEmpty()) {
            mostrarAlerta("Error", "No se encontró ningún estudiante con los códigos proporcionados");
        } else if (estudiantesEncontrados.size() < codigos.length) {
            mostrarAlerta("Advertencia", "No se encontraron todos los estudiantes especificados");
        }

        return estudiantesEncontrados;
    }

    // También actualizaremos el formatoAsignacion para asegurar consistencia
    private String formatoAsignacion(AsignacionMaterias asignacion) {
        StringBuilder sb = new StringBuilder();
        sb.append(asignacion.getCodigoAsignacion()).append("@@");
        sb.append(asignacion.getMateria().getCodigo()).append("@@");
        sb.append(asignacion.getMateria().getNombre()).append("@@");
        sb.append(asignacion.getDocente().getCodigo()).append("@@");

        // Concatenar los códigos de estudiantes
        String estudiantesCodigos = asignacion.getEstudiantes().stream()
                .map(Estudiante::getCodigo)
                .reduce((a, b) -> a + "@@" + b)
                .orElse("");

        sb.append(estudiantesCodigos);
        return sb.toString();
    }

    private void guardarAsignacion(AsignacionMaterias asignacion) throws IOException {
        if (asignacion == null || asignacion.getMateria() == null) {
            throw new IllegalArgumentException("La asignación o la materia no pueden ser null");
        }

        String contenido = formatoAsignacion(asignacion);
        Persistencia.guardarAsignacion(contenido);
        Persistencia.guardaRegistroLog("Asignación guardada: " + asignacion.getCodigoAsignacion(), 1, "Asignar");
    }
}