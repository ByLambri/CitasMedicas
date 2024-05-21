package metaenlace.citasmedicas.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import metaenlace.citasmedicas.entity.Cita;
import metaenlace.citasmedicas.entity.Diagnostico;
import metaenlace.citasmedicas.entity.Medico;
import metaenlace.citasmedicas.entity.Paciente;
import metaenlace.citasmedicas.entitydto.CitaDTO;
import metaenlace.citasmedicas.entitydto.DiagnosticoChildDTO;
import metaenlace.citasmedicas.entitydto.MedicoChildDTO;
import metaenlace.citasmedicas.entitydto.PacienteChildDTO;
import metaenlace.citasmedicas.entitydto.PacienteDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-28T16:40:34+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class PacienteMapperImpl implements PacienteMapper {

    @Override
    public PacienteDTO pacienteToPacienteDTO(Paciente paciente) {
        if ( paciente == null ) {
            return null;
        }

        PacienteDTO pacienteDTO = new PacienteDTO();

        pacienteDTO.setId( paciente.getId() );
        pacienteDTO.setUsuario( paciente.getUsuario() );
        pacienteDTO.setNombre( paciente.getNombre() );
        pacienteDTO.setApellidos( paciente.getApellidos() );
        pacienteDTO.setClave( paciente.getClave() );
        pacienteDTO.setNSS( paciente.getNSS() );
        pacienteDTO.setNumTarjeta( paciente.getNumTarjeta() );
        pacienteDTO.setTelefono( paciente.getTelefono() );
        pacienteDTO.setDireccion( paciente.getDireccion() );
        pacienteDTO.setMedicos( medicoListToMedicoChildDTOList( paciente.getMedicos() ) );
        pacienteDTO.setCitas( citaListToCitaDTOList( paciente.getCitas() ) );

        return pacienteDTO;
    }

    @Override
    public PacienteChildDTO pacienteToPacienteChildDTO(Paciente paciente) {
        if ( paciente == null ) {
            return null;
        }

        PacienteChildDTO pacienteChildDTO = new PacienteChildDTO();

        pacienteChildDTO.setId( paciente.getId() );
        pacienteChildDTO.setUsuario( paciente.getUsuario() );
        pacienteChildDTO.setNombre( paciente.getNombre() );
        pacienteChildDTO.setApellidos( paciente.getApellidos() );
        pacienteChildDTO.setClave( paciente.getClave() );
        pacienteChildDTO.setNSS( paciente.getNSS() );
        pacienteChildDTO.setNumTarjeta( paciente.getNumTarjeta() );
        pacienteChildDTO.setTelefono( paciente.getTelefono() );
        pacienteChildDTO.setDireccion( paciente.getDireccion() );

        return pacienteChildDTO;
    }

    protected MedicoChildDTO medicoToMedicoChildDTO(Medico medico) {
        if ( medico == null ) {
            return null;
        }

        MedicoChildDTO medicoChildDTO = new MedicoChildDTO();

        medicoChildDTO.setId( medico.getId() );
        medicoChildDTO.setUsuario( medico.getUsuario() );
        medicoChildDTO.setNombre( medico.getNombre() );
        medicoChildDTO.setApellidos( medico.getApellidos() );
        medicoChildDTO.setClave( medico.getClave() );
        medicoChildDTO.setNumColegiado( medico.getNumColegiado() );

        return medicoChildDTO;
    }

    protected List<MedicoChildDTO> medicoListToMedicoChildDTOList(List<Medico> list) {
        if ( list == null ) {
            return null;
        }

        List<MedicoChildDTO> list1 = new ArrayList<MedicoChildDTO>( list.size() );
        for ( Medico medico : list ) {
            list1.add( medicoToMedicoChildDTO( medico ) );
        }

        return list1;
    }

    protected DiagnosticoChildDTO diagnosticoToDiagnosticoChildDTO(Diagnostico diagnostico) {
        if ( diagnostico == null ) {
            return null;
        }

        DiagnosticoChildDTO diagnosticoChildDTO = new DiagnosticoChildDTO();

        diagnosticoChildDTO.setId( diagnostico.getId() );
        diagnosticoChildDTO.setValoracionEspecialista( diagnostico.getValoracionEspecialista() );
        diagnosticoChildDTO.setEnfermedad( diagnostico.getEnfermedad() );

        return diagnosticoChildDTO;
    }

    protected CitaDTO citaToCitaDTO(Cita cita) {
        if ( cita == null ) {
            return null;
        }

        CitaDTO citaDTO = new CitaDTO();

        citaDTO.setId( cita.getId() );
        citaDTO.setFechaHora( cita.getFechaHora() );
        citaDTO.setMotivoCita( cita.getMotivoCita() );
        citaDTO.setMedico( medicoToMedicoChildDTO( cita.getMedico() ) );
        citaDTO.setPaciente( pacienteToPacienteChildDTO( cita.getPaciente() ) );
        citaDTO.setDiagnostico( diagnosticoToDiagnosticoChildDTO( cita.getDiagnostico() ) );

        return citaDTO;
    }

    protected List<CitaDTO> citaListToCitaDTOList(List<Cita> list) {
        if ( list == null ) {
            return null;
        }

        List<CitaDTO> list1 = new ArrayList<CitaDTO>( list.size() );
        for ( Cita cita : list ) {
            list1.add( citaToCitaDTO( cita ) );
        }

        return list1;
    }
}
