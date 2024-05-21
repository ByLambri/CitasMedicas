package metaenlace.citasmedicas.mapper;

import metaenlace.citasmedicas.entity.Diagnostico;
import metaenlace.citasmedicas.entitydto.DiagnosticoChildDTO;
import metaenlace.citasmedicas.entitydto.DiagnosticoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DiagnosticoMapper {

	DiagnosticoMapper INSTANCE = Mappers.getMapper(DiagnosticoMapper.class);

	DiagnosticoDTO diagnosticoToDiagnosticoDTO(Diagnostico diagnostico);

	DiagnosticoChildDTO diagnosticoToDiagnosticoChildDTO(Diagnostico diagnostico);
}