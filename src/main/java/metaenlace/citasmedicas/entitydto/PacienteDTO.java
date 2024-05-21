package metaenlace.citasmedicas.entitydto;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class PacienteDTO extends UsuarioDTO {
    @NotEmpty(message = "NSS must not be empty")
    private String NSS;

    @NotEmpty(message = "numTarjeta must not be empty")
    private String numTarjeta;

    @NotEmpty(message = "telefono must not be empty")
    private String telefono;

    @NotEmpty(message = "direccion must not be empty")
    private String direccion;

    private List<MedicoChildDTO> medicos;

    private List<CitaDTO> citas;

    public PacienteDTO() {}

    public String getNSS() {
        return NSS;
    }

    public void setNSS(String NSS) {
        this.NSS = NSS;
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

    public List<MedicoChildDTO> getMedicos() {
        return medicos;
    }

    public void setMedicos(List<MedicoChildDTO> medicos) {
        this.medicos = medicos;
    }

    public List<CitaDTO> getCitas() {
        return citas;
    }

    public void setCitas(List<CitaDTO> citas) {
        this.citas = citas;
    }
}
