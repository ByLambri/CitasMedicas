package metaenlace.citasmedicas.mapper;

import metaenlace.citasmedicas.entity.Paciente;
import metaenlace.citasmedicas.entitydto.PacienteChildDTO;
import metaenlace.citasmedicas.entitydto.PacienteDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PacienteMapper {
	PacienteMapper INSTANCE = Mappers.getMapper(PacienteMapper.class);
	
	PacienteDTO pacienteToPacienteDTO(Paciente paciente);
	
	PacienteChildDTO pacienteToPacienteChildDTO(Paciente paciente);
}
