package metaenlace.citasmedicas.service;

import metaenlace.citasmedicas.entity.Cita;
import metaenlace.citasmedicas.entity.Medico;
import metaenlace.citasmedicas.entity.Paciente;
import metaenlace.citasmedicas.entitydto.CitaDTO;
import metaenlace.citasmedicas.entitydto.MedicoDTO;
import metaenlace.citasmedicas.entitydto.PacienteChildDTO;
import metaenlace.citasmedicas.exception.BadRequestException;
import metaenlace.citasmedicas.exception.NotFoundException;
import metaenlace.citasmedicas.mapper.MedicoMapper;
import metaenlace.citasmedicas.repository.CitaRepository;
import metaenlace.citasmedicas.repository.MedicoRepository;
import metaenlace.citasmedicas.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private CitaRepository citaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<MedicoDTO> getAll() {
        List<MedicoDTO> medicosDTO = new ArrayList<>();
        for (Medico medico : medicoRepository.findAll()) {
            medicosDTO.add(MedicoMapper.INSTANCE.medicoToMedicoDTO(medico));
        }

        return medicosDTO;
    }

    public MedicoDTO getById(Long id) {
        Optional<Medico> medico = medicoRepository.findById(id);
        if (medico.isEmpty()) {
            throw new NotFoundException("Medico not found : " + id);
        }
        return MedicoMapper.INSTANCE.medicoToMedicoDTO(medico.get());

    }

    public Optional<MedicoDTO> findByUsuario(String usuario) {
        Optional<Medico> medico = medicoRepository.findByUsuario(usuario);
        if (medico.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(MedicoMapper.INSTANCE.medicoToMedicoDTO(medico.get()));
    }

    public void add(MedicoDTO newMedico) {
        // encriptar pass
        newMedico.setClave(passwordEncoder.encode(newMedico.getClave()));

        Medico medico = new Medico(null,
                newMedico.getNombre(),
                newMedico.getApellidos(),
                newMedico.getUsuario(),
                newMedico.getClave(),
                newMedico.getNumColegiado(),
                new ArrayList<>(),
                new ArrayList<>());

        // append every patient if exists iterating every ID
        if (newMedico.getPacientes() != null) {
            List<Paciente> pacientes = new ArrayList<>();
            for (PacienteChildDTO pacienteDTO : newMedico.getPacientes()) {
                if (pacienteDTO.getId() != null) {
                    Optional<Paciente> paciente = pacienteRepository.findById(pacienteDTO.getId());
                    if (paciente.isPresent()) {
                        pacientes.add(paciente.get());
                    }
                }
            }

            medico.setPacientes(pacientes);
        }

        // add the new medic and update the ID for the return data
        newMedico.setId(medicoRepository.save(medico).getId());
    }

    public void delete(Long id) {
        if (!medicoRepository.existsById(id)) {
            throw new NotFoundException("Medico not found : " + id);
        }

        medicoRepository.deleteById(id);
    }

    public void update(MedicoDTO newMedico) {
        // check id
        if (newMedico.getId() == null) {
            throw new BadRequestException("id must not be empty");
        }

        Optional<Medico> medico = medicoRepository.findById(newMedico.getId());

        // check medico exists
        if (medico.isEmpty()) {
            throw new NotFoundException("Medico not found : " + newMedico.getId());
        }

        Medico medicoReplace = new Medico(newMedico.getId(),
                newMedico.getNombre(),
                newMedico.getApellidos(),
                newMedico.getUsuario(),
                newMedico.getClave(),
                newMedico.getNumColegiado(),
                new ArrayList<>(),
                new ArrayList<>());

        // append every patient if exists iterating every ID
        if (newMedico.getPacientes() != null) {
            List<Paciente> pacientes = new ArrayList<>();
            for (PacienteChildDTO pacienteDTO : newMedico.getPacientes()) {
                if (pacienteDTO.getId() != null) {
                    Optional<Paciente> paciente = pacienteRepository.findById(pacienteDTO.getId());
                    paciente.ifPresent(pacientes::add);
                }
            }

            medicoReplace.setPacientes(pacientes);
        }

        // append every cita if exists
        if (newMedico.getCitas() != null) {
            List<Cita> citas = new ArrayList<>();
            for (CitaDTO citaDTO : newMedico.getCitas()) {
                if (citaDTO.getId() != null) {
                    Optional<Cita> cita = citaRepository.findById(citaDTO.getId());
                    cita.ifPresent(citas::add);
                }
            }

            medicoReplace.setCitas(citas);
        }

        // add the new medic and update the ID for the return data
        newMedico.setId(medicoRepository.save(medicoReplace).getId());
    }
}
