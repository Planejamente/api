package org.planejamente.planejamente.service;

import org.planejamente.planejamente.dto.PsicologosDisponiveisDto;
import org.planejamente.planejamente.dto.dtoConsultar.ConsultaDtoConsultar;
import org.planejamente.planejamente.dto.dtoConsultar.PsicologoDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.ConsultaDto;
import org.planejamente.planejamente.entity.Consulta;
import org.planejamente.planejamente.entity.usuario.Paciente;
import org.planejamente.planejamente.entity.usuario.Psicologo;
import org.planejamente.planejamente.mapper.ConsultaMapper;
import org.planejamente.planejamente.mapper.PsicologoMapper;
import org.planejamente.planejamente.repository.ConsultaRepository;
import org.planejamente.planejamente.repository.PacienteRepository;
import org.planejamente.planejamente.repository.PsicologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class ConsultaService {
    private static final Logger logger = Logger.getLogger(ConsultaService.class.getName());

    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final PsicologoRepository psicologoRepository;
    private final CalendarService calendarService;

    @Autowired
    public ConsultaService(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository, PsicologoRepository psicologoRepository, CalendarService calendarService) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.psicologoRepository = psicologoRepository;
        this.calendarService = calendarService;
    }

    // Método para criar uma nova consulta
    public Consulta criar(ConsultaDto consultaDto, String accessToken, String calendarId) {
        UUID idPsi = consultaDto.getIdPsicologo();
        UUID idPac = consultaDto.getIdPaciente();

        // Busca o psicólogo no repositório pelo ID
        Psicologo psicologo = psicologoRepository.findPsicologoByIdIs(idPsi);
        if (psicologo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Psicólogo não encontrado");

        // Busca o paciente no repositório pelo ID
        Paciente paciente = pacienteRepository.findById(idPac)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado"));

        // Converte o DTO de consulta para a entidade Consulta
        Consulta consulta = ConsultaMapper.toEntity(consultaDto);
        if (Objects.isNull(consulta)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Dados da consulta inválidos");

        consulta.setPsicologo(psicologo);
        consulta.setPaciente(paciente);

        LocalDateTime inicio = consulta.getInicio();
        LocalDateTime fim = consulta.getFim();

        // Valida se a data/hora de início é depois da data/hora de fim
        if (inicio.isAfter(fim)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data/hora da consulta inválida");
        }

        // Verifica se já existe uma consulta no mesmo horário
        boolean existeConsultaHorario = consultaRepository.existsByPsicologoIdAndInicioBetween(idPsi, inicio, fim)
                && consultaRepository.existsByPsicologoIdAndFimBetween(idPsi, inicio, fim);

        if (existeConsultaHorario) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Psicólogo já tem consulta no horário");
        }

        // Cria um evento no Google Calendar
        try {
            String meetLink = calendarService.createEventWithMeetLink(accessToken, calendarId, consulta);
            consulta.setLinkMeet(meetLink);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Erro ao criar evento no Google Calendar", e);
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao criar evento no Google Calendar", e);
        }

        // Salva a consulta no repositório
        return consultaRepository.save(consulta);
    }

    public List<ConsultaDtoConsultar> buscarPorPsicologo(UUID idPsi) {
        if (!this.psicologoRepository.existsById(idPsi)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<Consulta> consultas = this.consultaRepository.findAllByPsicologoId(idPsi);
        return ConsultaMapper.toDto(consultas);
    }

    public List<ConsultaDtoConsultar> buscarPorPaciente(UUID idPac) {
        if(!this.pacienteRepository.existsById(idPac)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        List<Consulta> consultas = this.consultaRepository.findAllByPacienteId(idPac);
        return ConsultaMapper.toDto(consultas);
    }

    // Método para buscar todos os psicólogos disponíveis
    public List<PsicologoDtoConsultar> buscarTodos(PsicologosDisponiveisDto dto) {
        return psicologoRepository.findAll().stream()
                .map(PsicologoMapper::toDto)
                .collect(Collectors.toList());
    }
}
