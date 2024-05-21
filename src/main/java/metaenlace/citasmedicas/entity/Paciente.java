package metaenlace.citasmedicas.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PACIENTES")
@PrimaryKeyJoinColumn(name = "ID")
public class Paciente extends Usuario {
	@Column(name = "NSS", nullable = false)
	private String NSS;
	
	@Column(name = "NUM_TARJETA", nullable = false)
	private String numTarjeta;
	
	@Column(name = "TELEFONO", nullable = false)
	private String telefono;
	
	@Column(name = "DIRECCION", nullable = false)
	private String direccion;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "MEDICOS_PACIENTES",
			joinColumns = @JoinColumn(name = "PACIENTE_ID"),
			inverseJoinColumns = @JoinColumn(name = "MEDICO_ID"))
	private List<Medico> medicos;
	
	@OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Cita> citas;
	
	public Paciente() {}
	
	public Paciente( Long ID, String nombre, 
					 String apellidos, String usuario, 
					 String clave, String nSS,
					 String numTarjeta, String telefono, 
					 String direccion, ArrayList<Medico> medicos,
					 ArrayList<Cita> citas ) {
		super(ID, nombre, apellidos, usuario, clave);
		NSS = nSS;
		this.numTarjeta = numTarjeta;
		this.telefono = telefono;
		this.direccion = direccion;
		this.medicos = medicos;
		this.citas = citas;
	}

	public String getNSS() {
		return NSS;
	}

	public void setNSS(String nSS) {
		NSS = nSS;
	}

	public String getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public List<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}

	public List<Cita> getCitas() {
		return citas;
	}

	public void setCitas(List<Cita> citas) {
		this.citas = citas;
	}
	
	public Usuario getUsuarioInstance() {
		return (Usuario) this;
	}
}
