package metaenlace.citasmedicas.controller;

import metaenlace.citasmedicas.entitydto.PacienteDTO;
import metaenlace.citasmedicas.service.CitaService;
import metaenlace.citasmedicas.service.MedicoService;
import metaenlace.citasmedicas.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PacienteController {
	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private MedicoService medicoService;

	@Autowired
	private CitaService citaService;

	// Get all pacientes
	@GetMapping("/pacientes")
	public List<PacienteDTO> getAll() {
		return pacienteService.getAll();
	}

	// Get paciente by Id
	@GetMapping("/pacientes/{id}")
	public PacienteDTO getByID(@PathVariable Long id) {
		return pacienteService.getById(id);
	}

	// Add paciente
	@PostMapping("/pacientes")
	public PacienteDTO add(@Validated @RequestBody PacienteDTO newPaciente) {
		pacienteService.add(newPaciente);

		return newPaciente;
	}

	// Delete paciente by Id
	@DeleteMapping("/pacientes/{id}")
	public Long delete(@PathVariable Long id) {
		pacienteService.delete(id);

		return id;
	}

	// update paciente
	@PutMapping("/pacientes")
	public PacienteDTO replace(@Validated @RequestBody PacienteDTO newPaciente) {
		pacienteService.update(newPaciente);

		return newPaciente;
	}
}
