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
import metaenlace.citasmedicas.entitydto.MedicoDTO;
import metaenlace.citasmedicas.entitydto.PacienteChildDTO;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-28T16:40:35+0200",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 17.0.2 (Oracle Corporation)"
)
@Component
public class MedicoMapperImpl implements MedicoMapper {

    @Override
    public MedicoDTO medicoToMedicoDTO(Medico medico) {
        if ( medico == null ) {
            return null;
        }

        MedicoDTO medicoDTO = new MedicoDTO();

        medicoDTO.setId( medico.getId() );
        medicoDTO.setUsuario( medico.getUsuario() );
        medicoDTO.setNombre( medico.getNombre() );
        medicoDTO.setApellidos( medico.getApellidos() );
        medicoDTO.setClave( medico.getClave() );
        medicoDTO.setNumColegiado( medico.getNumColegiado() );
        medicoDTO.setPacientes( pacienteListToPacienteChildDTOList( medico.getPacientes() ) );
        medicoDTO.setCitas( citaListToCitaDTOList( medico.getCitas() ) );

        return medicoDTO;
    }

    @Override
    public MedicoChildDTO medicoToMedicoChildDTO(Medico medico) {
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

    protected PacienteChildDTO pacienteToPacienteChildDTO(Paciente paciente) {
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

    protected List<PacienteChildDTO> pacienteListToPacienteChildDTOList(List<Paciente> list) {
        if ( list == null ) {
            return null;
        }

        List<PacienteChildDTO> list1 = new ArrayList<PacienteChildDTO>( list.size() );
        for ( Paciente paciente : list ) {
            list1.add( pacienteToPacienteChildDTO( paciente ) );
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
