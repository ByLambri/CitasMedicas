package metaenlace.citasmedicas.service;

import metaenlace.citasmedicas.entity.Paciente;
import metaenlace.citasmedicas.entity.Usuario;
import metaenlace.citasmedicas.entitydto.PacienteDTO;
import metaenlace.citasmedicas.entitydto.UsuarioDTO;
import metaenlace.citasmedicas.mapper.PacienteMapper;
import metaenlace.citasmedicas.mapper.UsuarioMapper;
import metaenlace.citasmedicas.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<UsuarioDTO> findByUsuario(String usuario) {
        Optional<Usuario> usuarioEntity = usuarioRepository.findByUsuario(usuario);
        if (usuarioEntity.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(UsuarioMapper.INSTANCE.usuarioToUsuarioDTO(usuarioEntity.get()));
    }
}
