package metaenlace.citasmedicas.controller;

import metaenlace.citasmedicas.entitydto.CitaDTO;
import metaenlace.citasmedicas.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
public class CitaController {
    @Autowired
    private CitaService citaService;

    // Get all citas
    @GetMapping("/citas")
    public List<CitaDTO> getAll() {
        return citaService.getAll();
    }

    // Get cita by id
    @GetMapping("/citas/{id}")
    public CitaDTO getByID(@PathVariable Long id) {
        return citaService.getById(id);
    }

    // Add new cita
    @PostMapping("/citas")
    public CitaDTO add(@Validated @RequestBody CitaDTO newCita) {
        citaService.add(newCita);

        return newCita;
    }

    // Replace entire cita
    @PutMapping("/citas")
    public CitaDTO replace(@Validated @RequestBody CitaDTO newCita) {
        citaService.update(newCita);

        return newCita;
    }

    // Delete cita by id
    @DeleteMapping("/citas/{id}")
    public Long delete(@PathVariable Long id) {
        citaService.delete(id);

        return id;
    }
}
