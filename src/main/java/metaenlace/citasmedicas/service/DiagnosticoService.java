package metaenlace.citasmedicas.service;

import metaenlace.citasmedicas.entity.Cita;
import metaenlace.citasmedicas.entity.Diagnostico;
import metaenlace.citasmedicas.entitydto.DiagnosticoDTO;
import metaenlace.citasmedicas.exception.BadRequestException;
import metaenlace.citasmedicas.exception.NotFoundException;
import metaenlace.citasmedicas.mapper.CitaMapper;
import metaenlace.citasmedicas.mapper.DiagnosticoMapper;
import metaenlace.citasmedicas.repository.CitaRepository;
import metaenlace.citasmedicas.repository.DiagnosticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DiagnosticoService {
	@Autowired
	DiagnosticoRepository diagnosticoRepository;
	
	@Autowired
	CitaRepository citaRepository;

	public List<DiagnosticoDTO> getAll() {
		List<DiagnosticoDTO> diagnosticosDTO = new ArrayList<DiagnosticoDTO>();
		for (Diagnostico diagnostico : diagnosticoRepository.findAll()) {
			diagnosticosDTO.add(DiagnosticoMapper.INSTANCE.diagnosticoToDiagnosticoDTO(diagnostico));
		}

		return diagnosticosDTO;
	}

	public DiagnosticoDTO getByID(Long id) {
		Optional<Diagnostico> diagnostico = diagnosticoRepository.findById(id);
		if (diagnostico.isEmpty()) {
			throw new NotFoundException("Diagnostico not found : " + id);
		}

		return DiagnosticoMapper.INSTANCE.diagnosticoToDiagnosticoDTO(diagnostico.get());
	}

	public void add(DiagnosticoDTO newDiagnostico) {
		Optional<Cita> cita = citaRepository.findById(newDiagnostico.getCita().getId());

		if (cita.isEmpty()) {
			throw new NotFoundException("Cita not found : " + newDiagnostico.getCita().getId());
		}

		Diagnostico diagnostico = new Diagnostico(null,
				newDiagnostico.getValoracionEspecialista(),
				newDiagnostico.getEnfermedad(),
				cita.get());

		// add the new appointment and update the ID for the return data
		newDiagnostico.setId(diagnosticoRepository.save(diagnostico).getId());
		newDiagnostico.setCita(CitaMapper.INSTANCE.citaToCitaDTO(cita.get()));
		
		// update new diagnostico to cita
		cita.get().setDiagnostico(diagnostico);
		citaRepository.save(cita.get());
	}

	public void update(DiagnosticoDTO newDiagnostico) {
		// check id
		if (newDiagnostico.getId() == null) {
			throw new BadRequestException("id must not be empty");
		}

		// check if diagnostico exists
		if (diagnosticoRepository.findById(newDiagnostico.getId()).isEmpty()) {
			throw new NotFoundException("Diagnostico not found : " + newDiagnostico.getId());
		}

		Optional<Cita> cita = citaRepository.findById(newDiagnostico.getCita().getId());

		Diagnostico diagnostico = new Diagnostico(newDiagnostico.getId(),
				newDiagnostico.getValoracionEspecialista(),
				newDiagnostico.getEnfermedad(),
				cita.orElse(null));

		newDiagnostico.setCita(CitaMapper.INSTANCE.citaToCitaDTO(cita.get()));

		diagnosticoRepository.save(diagnostico);

		// update diagnostico
		if (cita.isPresent()) {
			cita.get().setDiagnostico(diagnostico);
			citaRepository.save(cita.get());
		}
	}

	public void delete(Long id) {
		if (!diagnosticoRepository.existsById(id)) {
			throw new NotFoundException("Diagnostico not found : " + id);
		}

		diagnosticoRepository.deleteById(id);
	}
}
