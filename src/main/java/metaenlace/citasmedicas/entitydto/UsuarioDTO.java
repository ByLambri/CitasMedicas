package metaenlace.citasmedicas.entitydto;

import javax.validation.constraints.NotEmpty;

public class UsuarioDTO {
    private Long id;

    @NotEmpty(message = "usuario must not be empty")
    private String usuario;

    @NotEmpty(message = "nombre must not be empty")
    private String nombre;

    @NotEmpty(message = "apellidos must not be empty")
    private String apellidos;

    @NotEmpty(message = "clave must not be empty")
    private String clave;

    public UsuarioDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}
}
