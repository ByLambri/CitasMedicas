package metaenlace.citasmedicas.repository;

import metaenlace.citasmedicas.entity.Paciente;
import metaenlace.citasmedicas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long>{
    public Optional<Paciente> findByUsuario(String usuario);
}
