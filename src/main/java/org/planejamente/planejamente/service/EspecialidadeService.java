package org.planejamente.planejamente.service;

import org.planejamente.planejamente.dto.dtoConsultar.EspecialidadeDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.EspecialidadeDto;
import org.planejamente.planejamente.entity.Especialidade;
import org.planejamente.planejamente.mapper.EspecialidadeMapper;
import org.planejamente.planejamente.repository.EspecialidadeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class EspecialidadeService {
    private final EspecialidadeRepository repository;

    public EspecialidadeService(EspecialidadeRepository repository) {
        this.repository = repository;
    }

    public List<EspecialidadeDtoConsultar> listar() {
        List<Especialidade> lista = this.repository.findAll();
        return EspecialidadeMapper.toDto(lista);
    }

    public EspecialidadeDtoConsultar criar(EspecialidadeDto dto) {
        Especialidade especialidade = EspecialidadeMapper.toEntity(dto);

        if(this.repository.existsByTituloIgnoreCase(especialidade.getTitulo())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Título da especialidade já existe");
        }

        Especialidade especialidadeSalva = this.repository.save(especialidade);
        return EspecialidadeMapper.toDto(especialidadeSalva);
    }

    public void deletar(UUID idEspecialidade) {
        Especialidade especialidade = this.repository.findById(idEspecialidade)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Especialidade não encontrada"));

        this.repository.delete(especialidade);
    }
}
