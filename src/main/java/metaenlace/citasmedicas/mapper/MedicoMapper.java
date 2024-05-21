package metaenlace.citasmedicas.mapper;

import metaenlace.citasmedicas.entity.Medico;
import metaenlace.citasmedicas.entitydto.MedicoChildDTO;
import metaenlace.citasmedicas.entitydto.MedicoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MedicoMapper {
	MedicoMapper INSTANCE = Mappers.getMapper(MedicoMapper.class);
	
	MedicoDTO medicoToMedicoDTO(Medico medico);
	
	MedicoChildDTO medicoToMedicoChildDTO(Medico medico);
}
