package org.planejamente.planejamente.service;

import org.planejamente.planejamente.dto.PsicologosDisponiveisDto;
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
import org.planejamente.planejamente.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ConsultaService {
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

    public Consulta criar(ConsultaDto consultaDto, String accessToken, String calendarId) {
        UUID idPsi = consultaDto.getIdPsicologo();
        UUID idPac = consultaDto.getIdPaciente();

        Psicologo psicologo = this.psicologoRepository.findPsicologoByIdIs(idPsi);
        if (psicologo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Paciente paciente = this.pacienteRepository.findById(idPac)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado"));

        Consulta consulta = ConsultaMapper.toEntity(consultaDto);
        if (Objects.isNull(consulta)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        consulta.setPsicologo(psicologo);
        consulta.setPaciente(paciente);

        LocalDateTime inicio = consulta.getInicio();
        LocalDateTime fim = consulta.getFim();

        if (inicio.isAfter(fim)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data/hora da consulta inválida");
        }

        boolean existeConsultaHorario = this.consultaRepository.existsByPsicologoIdAndInicioBetween(idPsi, inicio, fim)
                && this.consultaRepository.existsByPsicologoIdAndFimBetween(idPsi, inicio, fim);

        if (existeConsultaHorario) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Psicólogo já tem consulta no horário");
        }

        try {
            String meetLink = calendarService.createEventWithMeetLink(accessToken, calendarId, consulta);
            consulta.setLinkMeet(meetLink);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao criar evento no Google Calendar", e);
        }

        return this.consultaRepository.save(consulta);
    }

    public List<PsicologoDtoConsultar> buscarTodos(PsicologosDisponiveisDto dto) {
        return this.psicologoRepository.findAll().stream()
                .map(PsicologoMapper::toDto)
                .collect(Collectors.toList());
    }
}
