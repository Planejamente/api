package org.planejamente.planejamente.service;

import org.planejamente.planejamente.dto.dtoConsultar.PacienteDtoConsultar;
import org.planejamente.planejamente.entity.usuario.Paciente;
import org.planejamente.planejamente.mapper.PacienteMapper;
import org.planejamente.planejamente.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PacienteService {
    private final PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }

    public List<PacienteDtoConsultar> listarTodos() {
        List<Paciente> todos = this.repository.findAll();
        return PacienteMapper.toDto(todos);
    }

    public PacienteDtoConsultar buscarPorId(UUID id) {
        Paciente pacienteBuscado = this.repository.findById(id).orElse(null);
        return PacienteMapper.toDto(pacienteBuscado);
    }
}
