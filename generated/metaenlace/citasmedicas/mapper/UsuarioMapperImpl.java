package metaenlace.citasmedicas.mapper;

import javax.annotation.processing.Generated;
import metaenlace.citasmedicas.entity.Usuario;
import metaenlace.citasmedicas.entitydto.UsuarioDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-28T16:40:35+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class UsuarioMapperImpl implements UsuarioMapper {

    @Override
    public UsuarioDTO usuarioToUsuarioDTO(Usuario usuario) {
        if ( usuario == null ) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setId( usuario.getId() );
        usuarioDTO.setUsuario( usuario.getUsuario() );
        usuarioDTO.setNombre( usuario.getNombre() );
        usuarioDTO.setApellidos( usuario.getApellidos() );
        usuarioDTO.setClave( usuario.getClave() );

        return usuarioDTO;
    }
}
