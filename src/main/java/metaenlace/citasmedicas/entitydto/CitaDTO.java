package metaenlace.citasmedicas.entitydto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class CitaDTO {
	private Long id;
	
	@NotNull
	private Date fechaHora;
	
	@NotEmpty
	private String motivoCita;

	@NotNull
	private MedicoChildDTO medico;

	@NotNull
	private PacienteChildDTO paciente;

	private DiagnosticoChildDTO diagnostico;
	
	public CitaDTO() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Date fechaHora) {
		this.fechaHora = fechaHora;
	}

	public String getMotivoCita() {
		return motivoCita;
	}

	public void setMotivoCita(String motivoCita) {
		this.motivoCita = motivoCita;
	}

	public MedicoChildDTO getMedico() {
		return medico;
	}

	public void setMedico(MedicoChildDTO medico) {
		this.medico = medico;
	}

	public PacienteChildDTO getPaciente() {
		return paciente;
	}

	public void setPaciente(PacienteChildDTO paciente) {
		this.paciente = paciente;
	}

	public DiagnosticoChildDTO getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(DiagnosticoChildDTO diagnostico) {
		this.diagnostico = diagnostico;
	}
}
