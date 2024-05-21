package metaenlace.citasmedicas.mapper;

import metaenlace.citasmedicas.entity.Paciente;
import metaenlace.citasmedicas.entity.Usuario;
import metaenlace.citasmedicas.entitydto.PacienteChildDTO;
import metaenlace.citasmedicas.entitydto.PacienteDTO;
import metaenlace.citasmedicas.entitydto.UsuarioDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);
	
	UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);
}
