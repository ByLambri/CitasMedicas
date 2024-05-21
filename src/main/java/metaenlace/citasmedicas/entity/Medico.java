package metaenlace.citasmedicas.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MEDICOS")
@PrimaryKeyJoinColumn(name = "ID")
public class Medico extends Usuario {
	@Column(name = "NUM_COLEGIADO", nullable = false)
	@NotEmpty
	private String numColegiado;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "MEDICOS_PACIENTES",
			joinColumns = @JoinColumn(name = "MEDICO_ID"),
			inverseJoinColumns = @JoinColumn(name = "PACIENTE_ID"))
	private List<Paciente> pacientes;

	@OneToMany(mappedBy = "medico", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Cita> citas;
	
	public Medico() {}
	
	public Medico( Long ID, String nombre, 
				   String apellidos, String usuario, 
				   String clave, String numColegiado,
				   ArrayList<Paciente> pacientes, ArrayList<Cita> citas ) {
		super(ID, nombre, apellidos, usuario, clave);
		this.numColegiado = numColegiado;
		this.pacientes = pacientes;
		this.citas = citas;
	}

	public String getNumColegiado() {
		return numColegiado;
	}

	public void setNumColegiado(String numColegiado) {
		this.numColegiado = numColegiado;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
	
	public Usuario getUsuarioInstance() {
		return (Usuario) this;
	}

	public List<Cita> getCitas() {
		return citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}
}
