package metaenlace.citasmedicas.entitydto;

import javax.validation.constraints.NotEmpty;

public class PacienteChildDTO extends UsuarioDTO {
	@NotEmpty
	private String NSS;
	
	@NotEmpty
	private String numTarjeta;
	
	@NotEmpty
	private String telefono;
	
	@NotEmpty
	private String direccion;
		
	public PacienteChildDTO() {}

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
}
