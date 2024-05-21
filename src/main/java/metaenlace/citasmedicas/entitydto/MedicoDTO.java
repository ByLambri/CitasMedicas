package metaenlace.citasmedicas.entitydto;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class MedicoDTO extends UsuarioDTO {
	@NotEmpty
	private String numColegiado;
	
	private List<PacienteChildDTO> pacientes;
	
	private List<CitaDTO> citas;
	
	public MedicoDTO() {}

	public String getNumColegiado() {
		return numColegiado;
	}

	public void setNumColegiado(String numColegiado) {
		this.numColegiado = numColegiado;
	}

	public List<PacienteChildDTO> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<PacienteChildDTO> pacientes) {
		this.pacientes = pacientes;
	}

	public List<CitaDTO> getCitas() {
		return citas;
	}

	public void setCitas(List<CitaDTO> citas) {
		this.citas = citas;
	}
}
