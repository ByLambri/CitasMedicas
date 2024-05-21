package metaenlace.citasmedicas.service;

import metaenlace.citasmedicas.entity.Cita;
import metaenlace.citasmedicas.entity.Diagnostico;
import metaenlace.citasmedicas.entity.Medico;
import metaenlace.citasmedicas.entity.Paciente;
import metaenlace.citasmedicas.entitydto.CitaDTO;
import metaenlace.citasmedicas.exception.BadRequestException;
import metaenlace.citasmedicas.exception.NotFoundException;
import metaenlace.citasmedicas.mapper.CitaMapper;
import metaenlace.citasmedicas.mapper.DiagnosticoMapper;
import metaenlace.citasmedicas.mapper.MedicoMapper;
import metaenlace.citasmedicas.mapper.PacienteMapper;
import metaenlace.citasmedicas.repository.CitaRepository;
import metaenlace.citasmedicas.repository.DiagnosticoRepository;
import metaenlace.citasmedicas.repository.MedicoRepository;
import metaenlace.citasmedicas.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CitaService {
	@Autowired
	CitaRepository citaRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private DiagnosticoRepository diagnosticoRepository;

	public List<CitaDTO> getAll() {
		List<CitaDTO> citasDTO = new ArrayList<>();
		for (Cita cita : citaRepository.findAll()) {
			citasDTO.add(CitaMapper.INSTANCE.citaToCitaDTO(cita));
		}

		return citasDTO;
	}

	public CitaDTO getById(Long id) {
		Optional<Cita> cita = citaRepository.findById(id);
		if (cita.isEmpty()) {
			throw new NotFoundException("Medico not found : " + id);
		}

		return CitaMapper.INSTANCE.citaToCitaDTO(cita.get());
	}

	public void add(CitaDTO newCita) {
		Optional<Medico> medico = medicoRepository.findById(newCita.getMedico().getId());
		Optional<Paciente> paciente = pacienteRepository.findById(newCita.getPaciente().getId());

		// if medico or paciente eq null
		if (medico.isEmpty()) {
			throw new NotFoundException("Medico not found : " + newCita.getMedico().getId());
		}

		if (paciente.isEmpty()) {
			throw new NotFoundException("Paciente not found : " + newCita.getPaciente().getId());
		}

		Optional<Diagnostico> diagnostico = Optional.empty();
		if (newCita.getDiagnostico() != null) {
			diagnostico = diagnosticoRepository.findById(newCita.getDiagnostico().getId());
		}

		Cita cita = new Cita(null,
				newCita.getFechaHora(),
				newCita.getMotivoCita(),
				medico.get(),
				paciente.get(),
				diagnostico.orElse(null));

		// add the new appointment and update the ID for the return data
		newCita.setId(citaRepository.save(cita).getId());
		newCita.setMedico(MedicoMapper.INSTANCE.medicoToMedicoChildDTO(medico.get()));
		newCita.setPaciente(PacienteMapper.INSTANCE.pacienteToPacienteChildDTO(paciente.get()));


		citaRepository.save(cita);

		// update diagnostico
		if (diagnostico.isPresent()) {
			diagnostico.get().setCita(cita);
			diagnosticoRepository.save(diagnostico.get());
		}
	}

	public void update(CitaDTO newCita) {
		// check id
		if (newCita.getId() == null) {
			throw new BadRequestException("id must not be empty");
		}

		// check if cita exists
		if (citaRepository.findById(newCita.getId()).isEmpty()) {
			throw new NotFoundException("Cita not found : " + newCita.getPaciente().getId());
		}

		Optional<Medico> medico = medicoRepository.findById(newCita.getMedico().getId());
		Optional<Paciente> paciente = pacienteRepository.findById(newCita.getPaciente().getId());

		// if medico or paciente eq null
		if (medico.isEmpty()) {
			throw new NotFoundException("Medico not found : " + newCita.getMedico().getId());
		}

		if (paciente.isEmpty()) {
			throw new NotFoundException("Paciente not found : " + newCita.getPaciente().getId());
		}


		Optional<Diagnostico> diagnostico = Optional.empty();
		if (newCita.getDiagnostico() != null) {
			diagnostico = diagnosticoRepository.findById(newCita.getDiagnostico().getId());
		}

		Cita cita = new Cita(newCita.getId(),
				newCita.getFechaHora(),
				newCita.getMotivoCita(),
				medico.get(),
				paciente.get(),
				diagnostico.orElse(null));

		citaRepository.save(cita);

		// update diagnostico
		if (diagnostico.isPresent()) {
			diagnostico.get().setCita(cita);
			diagnosticoRepository.save(diagnostico.get());
			newCita.setDiagnostico(DiagnosticoMapper.INSTANCE.diagnosticoToDiagnosticoChildDTO(diagnostico.get()));
		}

		newCita.setMedico(MedicoMapper.INSTANCE.medicoToMedicoChildDTO(medico.get()));
		newCita.setPaciente(PacienteMapper.INSTANCE.pacienteToPacienteChildDTO(paciente.get()));
	}

	public void delete(Long id) {
		if (!citaRepository.existsById(id)) {
			throw new NotFoundException("Cita not found : " + id);
		}

		citaRepository.deleteById(id);
	}
}
