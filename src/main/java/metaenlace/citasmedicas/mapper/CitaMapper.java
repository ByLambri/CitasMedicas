package metaenlace.citasmedicas.mapper;

import metaenlace.citasmedicas.entity.Cita;
import metaenlace.citasmedicas.entitydto.CitaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface CitaMapper {
	CitaMapper INSTANCE = Mappers.getMapper(CitaMapper.class);
	
	CitaDTO citaToCitaDTO(Cita cita);
}