package metaenlace.citasmedicas.service;

import metaenlace.citasmedicas.entity.Cita;
import metaenlace.citasmedicas.entity.Medico;
import metaenlace.citasmedicas.entity.Paciente;
import metaenlace.citasmedicas.entitydto.CitaDTO;
import metaenlace.citasmedicas.entitydto.MedicoChildDTO;
import metaenlace.citasmedicas.entitydto.MedicoDTO;
import metaenlace.citasmedicas.entitydto.PacienteDTO;
import metaenlace.citasmedicas.exception.BadRequestException;
import metaenlace.citasmedicas.exception.NotFoundException;
import metaenlace.citasmedicas.mapper.MedicoMapper;
import metaenlace.citasmedicas.mapper.PacienteMapper;
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
public class PacienteService {
	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private CitaRepository citaRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public List<PacienteDTO> getAll() {
		List<PacienteDTO> pacientesDTO = new ArrayList<PacienteDTO>();
		for (Paciente paciente : pacienteRepository.findAll()) {
			pacientesDTO.add(PacienteMapper.INSTANCE.pacienteToPacienteDTO(paciente));
		}

		return pacientesDTO;
	}

	public PacienteDTO getById(Long id) {
		Optional<Paciente> paciente = pacienteRepository.findById(id);
		if (paciente.isEmpty()) {
			throw new NotFoundException("Paciente not found : " + id);
		}

		return PacienteMapper.INSTANCE.pacienteToPacienteDTO(paciente.get());
	}

	public Optional<PacienteDTO> findByUsuario(String usuario) {
		Optional<Paciente> paciente = pacienteRepository.findByUsuario(usuario);
		if (paciente.isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(PacienteMapper.INSTANCE.pacienteToPacienteDTO(paciente.get()));
	}

	public void add(PacienteDTO newPaciente) {
		// encriptar pass
		newPaciente.setClave(passwordEncoder.encode(newPaciente.getClave()));

		Paciente paciente = new Paciente(null,
				newPaciente.getNombre(),
				newPaciente.getApellidos(),
				newPaciente.getUsuario(),
				newPaciente.getClave(),
				newPaciente.getNSS(),
				newPaciente.getNumTarjeta(),
				newPaciente.getTelefono(),
				newPaciente.getDireccion(),
				new ArrayList<Medico>(),
				new ArrayList<Cita>());

		// append every medic if exists iterating every ID
		if (newPaciente.getMedicos() != null) {
			List<Medico> medicos = new ArrayList<Medico>();
			List<MedicoChildDTO> medicosChildDto = new ArrayList<MedicoChildDTO>();
			for (MedicoChildDTO medicoDTO : newPaciente.getMedicos()) {
				if (medicoDTO.getId() != null) {
					Optional<Medico> medico = medicoRepository.findById(medicoDTO.getId());
					medicos.add(medico.get());
					medicosChildDto.add(MedicoMapper.INSTANCE.medicoToMedicoChildDTO(medico.get()));
				}
			}
			newPaciente.setMedicos(medicosChildDto);
			paciente.setMedicos(medicos);
		}
		
		// add the new patient and update the ID for the return data
		newPaciente.setId(pacienteRepository.save(paciente).getId());
	}

	public void update(PacienteDTO newPaciente) {
		// check id
		if (newPaciente.getId() == null) {
			throw new BadRequestException("id must not be empty");
		}

		Optional<Paciente> paciente = pacienteRepository.findById(newPaciente.getId());

		// check medico exists
		if (paciente.isEmpty()) {
			throw new NotFoundException("Paciente not found : " + newPaciente.getId());
		}

		Paciente pacienteReplace = new Paciente(newPaciente.getId(),
				newPaciente.getNombre(),
				newPaciente.getApellidos(),
				newPaciente.getUsuario(),
				newPaciente.getClave(),
				newPaciente.getNSS(),
				newPaciente.getNumTarjeta(),
				newPaciente.getTelefono(),
				newPaciente.getDireccion(),
				new ArrayList<>(),
				new ArrayList<>());

		// append every patient if exists iterating every ID
		if (newPaciente.getMedicos() != null) {
			List<Medico> medicos = new ArrayList<>();
			for (MedicoChildDTO medicoChildDTO : newPaciente.getMedicos()) {
				if (medicoChildDTO.getId() != null) {
					Optional<Medico> medico = medicoRepository.findById(medicoChildDTO.getId());
					medico.ifPresent(medicos::add);
				}
			}

			pacienteReplace.setMedicos(medicos);
		}

		// append every cita if exists
		if (newPaciente.getCitas() != null) {
			List<Cita> citas = new ArrayList<>();
			for (CitaDTO citaDTO : newPaciente.getCitas()) {
				if (citaDTO.getId() != null) {
					Optional<Cita> cita = citaRepository.findById(citaDTO.getId());
					cita.ifPresent(citas::add);
				}
			}

			pacienteReplace.setCitas(citas);
		}

		// add the new medic and update the ID for the return data
		newPaciente.setId(pacienteRepository.save(pacienteReplace).getId());
	}

	public void delete(Long id) {
		if (!pacienteRepository.existsById(id)) {
			throw new NotFoundException("Medico not found : " + id);
		}

		pacienteRepository.deleteById(id);
	}
}
