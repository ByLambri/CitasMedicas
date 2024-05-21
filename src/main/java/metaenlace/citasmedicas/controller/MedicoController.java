package metaenlace.citasmedicas.controller;

import metaenlace.citasmedicas.entitydto.MedicoDTO;
import metaenlace.citasmedicas.service.CitaService;
import metaenlace.citasmedicas.service.MedicoService;
import metaenlace.citasmedicas.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
public class MedicoController {
	@Autowired
	private MedicoService medicoService;

	@Autowired
	private PacienteService pacienteService;

	@Autowired
	private CitaService citaService;

	@GetMapping("/medicos")
	public ResponseEntity<List<MedicoDTO>> getAll() {
		return ResponseEntity.ok(medicoService.getAll());
	}

	@GetMapping("/medicos/{id}")
	public MedicoDTO getByID(@PathVariable Long id) {
		return medicoService.getById(id);
	}

	@PostMapping("/medicos")
	public MedicoDTO add(@Validated @RequestBody MedicoDTO newMedico) {
		medicoService.add(newMedico);

		return newMedico;
	}

	@DeleteMapping("/medicos/{id}")
	public Long delete(@PathVariable Long id) {
		medicoService.delete(id);

		return id;
	}

	// update medico
	@PutMapping("/medicos")
	public MedicoDTO replace(@Validated @RequestBody MedicoDTO newMedico) {
		medicoService.update(newMedico);

		return newMedico;
	}
}
