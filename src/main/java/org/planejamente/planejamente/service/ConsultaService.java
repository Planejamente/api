package org.planejamente.planejamente.service;

import org.planejamente.planejamente.dto.dtoCriar.ConsultaDto;
import org.planejamente.planejamente.entity.Consulta;
import org.planejamente.planejamente.entity.usuario.Paciente;
import org.planejamente.planejamente.entity.usuario.Psicologo;
import org.planejamente.planejamente.mapper.ConsultaMapper;
import org.planejamente.planejamente.repository.ConsultaRepository;
import org.planejamente.planejamente.repository.PacienteRepository;
import org.planejamente.planejamente.repository.PsicologoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
public class ConsultaService {
    private final ConsultaRepository consultaRepository;
    private final PacienteRepository pacienteRepository;
    private final PsicologoRepository psicologoRepository;

    public ConsultaService(ConsultaRepository consultaRepository, PacienteRepository pacienteRepository, PsicologoRepository psicologoRepository) {
        this.consultaRepository = consultaRepository;
        this.pacienteRepository = pacienteRepository;
        this.psicologoRepository = psicologoRepository;
    }

    public Consulta criar(ConsultaDto consultaDto) {
        UUID idPsi = consultaDto.getIdPsicologo();
        UUID idPac = consultaDto.getIdPaciente();

        Psicologo psicologo = this.psicologoRepository.findPsicologoByIdIs(idPsi);
        if(psicologo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        Paciente paciente = this.pacienteRepository.findById(idPac)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente não encontrado"));

        Consulta consulta = ConsultaMapper.toEntity(consultaDto);
        if(Objects.isNull(consulta)) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        consulta.setPsicologo(psicologo);
        consulta.setPaciente(paciente);

        LocalDateTime inicio = consulta.getInicio();
        LocalDateTime fim = consulta.getFim();

        if(inicio.isAfter(fim)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data/hora da consulta inválida");
        }

        boolean existeConsultaHorario = this.consultaRepository.existsByPsicologoIdAndInicioBetween(idPsi, inicio, fim)
                && this.consultaRepository.existsByPsicologoIdAndFimBetween(idPsi, inicio, fim);

        if(existeConsultaHorario) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Psícologo já tem consulta no horário");
        }

        return this.consultaRepository.save(consulta);
    }
}
