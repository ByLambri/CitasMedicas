package metaenlace.citasmedicas.controller;

import metaenlace.citasmedicas.entitydto.DiagnosticoDTO;
import metaenlace.citasmedicas.service.DiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DiagnosticoController {
	@Autowired
	private DiagnosticoService diagnosticoService;

	// get all diagnosticos
	@GetMapping("/diagnosticos")
	public List<DiagnosticoDTO> getAll() {
		return diagnosticoService.getAll();
	}

	// get diagnostico by id
	@GetMapping("/diagnosticos/{id}")
	public DiagnosticoDTO getByID(@PathVariable Long id) {
		return diagnosticoService.getByID(id);
	}

	// add diagnostico
	@PostMapping("/diagnosticos")
	public DiagnosticoDTO add(@Validated @RequestBody DiagnosticoDTO newDiagnostico) {
		diagnosticoService.add(newDiagnostico);

		return newDiagnostico;
	}

	// Replace entire diagnostico
	@PutMapping("/diagnosticos")
	public DiagnosticoDTO replace(@Validated @RequestBody DiagnosticoDTO newDiagnostico) {
		diagnosticoService.update(newDiagnostico);

		return newDiagnostico;
	}

	// delete diagnostico by id
	@DeleteMapping("/diagnosticos/{id}")
	public Long delete(@PathVariable Long id) {
		diagnosticoService.delete(id);

		return id;
	}
}
