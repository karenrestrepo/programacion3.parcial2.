package co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio4.Mapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio4.Dto.AsignacionMateriasDto;
import co.edu.uniquindio.programacion3.parcial2.parcial2.Ejercicio4.Model.AsignacionMaterias;

@Mapper
public interface AsignacionMateriasMapper {
    AsignacionMateriasMapper INSTANCE = Mappers.getMapper(AsignacionMateriasMapper.class);

    @Mapping(target = "codigoAsignacion", source = "codigoAsignacion")
    @Mapping(target = "materia.codigo", source = "codigoMateria")
    @Mapping(target = "materia.nombre", source = "nombreMateria")
    @Mapping(target = "docente.codigo", source = "codigoDocente")
    AsignacionMaterias dtoToEntity(AsignacionMateriasDto dto);

    @Mapping(target = "codigoAsignacion", source = "codigoAsignacion")
    @Mapping(target = "codigoMateria", source = "materia.codigo")
    @Mapping(target = "nombreMateria", source = "materia.nombre")
    @Mapping(target = "codigoDocente", source = "docente.codigo")
    AsignacionMateriasDto entityToDto(AsignacionMaterias entity);
}
