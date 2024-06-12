package org.planejamente.planejamente.service;

import org.planejamente.planejamente.dto.dtoConsultar.ExperienciaFormacaoDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.ExperienciaFormacaoDto;
import org.planejamente.planejamente.entity.ExperienciaFormacao;
import org.planejamente.planejamente.entity.usuario.Psicologo;
import org.planejamente.planejamente.mapper.ExperienciaFormacaoMapper;
import org.planejamente.planejamente.repository.ExperienciaFormacaoRepository;
import org.planejamente.planejamente.repository.PsicologoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class ExperienciaFormacaoService {
    private final ExperienciaFormacaoRepository experienciaFormacaoRepository;
    private final PsicologoRepository psicologoRepository;

    public ExperienciaFormacaoService(ExperienciaFormacaoRepository experienciaFormacaoRepository, PsicologoRepository psicologoRepository) {
        this.experienciaFormacaoRepository = experienciaFormacaoRepository;
        this.psicologoRepository = psicologoRepository;
    }

    public List<ExperienciaFormacaoDtoConsultar> buscarPorPsicologo(UUID idPsicologo) {
        if(!this.psicologoRepository.existsById(idPsicologo)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Psicólogo não encontrado");
        }

        List<ExperienciaFormacao> lista = this.experienciaFormacaoRepository.findByPsicologoId(idPsicologo);
        return ExperienciaFormacaoMapper.toDto(lista);
    }

    public ExperienciaFormacaoDtoConsultar criar(ExperienciaFormacaoDto expForm) {
        Psicologo psicologo = this.psicologoRepository.findById(expForm.getIdPsicologo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Psicólogo não encontrado"));

        if(expForm.getDataInicio().isAfter(expForm.getDataFim()) || expForm.getDataInicio().equals(expForm.getDataFim())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        ExperienciaFormacao expFormEntidade = ExperienciaFormacaoMapper.toEntity(expForm);
        expFormEntidade.setPsicologo(psicologo);

        ExperienciaFormacao expFormSalva = this.experienciaFormacaoRepository.save(expFormEntidade);
        return ExperienciaFormacaoMapper.toDto(expFormSalva);
    }
}