package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio4.Dto;

import java.util.List;

public record AsignacionMateriasDto(
        String codigoAsignacion,
        String codigoMateria,
        String nombreMateria,
        String codigoDocente,
        String[] codigosEstudiantes
) {
}