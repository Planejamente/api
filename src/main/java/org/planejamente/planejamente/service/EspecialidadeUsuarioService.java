package org.planejamente.planejamente.service;

import org.planejamente.planejamente.dto.dtoConsultar.EspecialidadeUsuarioDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.EspecialidadeUsuarioDto;
import org.planejamente.planejamente.entity.Especialidade;
import org.planejamente.planejamente.entity.EspecialidadeUsuario;
import org.planejamente.planejamente.entity.usuario.Psicologo;
import org.planejamente.planejamente.mapper.EspecialidadeUsuarioMapper;
import org.planejamente.planejamente.repository.EspecialidadeRepository;
import org.planejamente.planejamente.repository.EspecialidadeUsuarioRepository;
import org.planejamente.planejamente.repository.PsicologoRepository;
import org.planejamente.planejamente.util.FilaObj;
import org.planejamente.planejamente.util.PilhaObj;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EspecialidadeUsuarioService {
    private final EspecialidadeUsuarioRepository espUsuRepository;
    private final PsicologoRepository psiRepository;
    private final EspecialidadeRepository espRepository;

    public EspecialidadeUsuarioService(EspecialidadeUsuarioRepository espUsuRepository, PsicologoRepository psiRepository, EspecialidadeRepository espRepository) {
        this.espUsuRepository = espUsuRepository;
        this.psiRepository = psiRepository;
        this.espRepository = espRepository;
    }

    public List<EspecialidadeUsuarioDtoConsultar> criar(EspecialidadeUsuarioDto dto) {
        Psicologo psicologo = this.psiRepository.findById(dto.getIdPsicologo())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Psicólogo não encontrado"));

        FilaObj<Especialidade> filaEspecialidades = new FilaObj<>(dto.getListaIdEspecialidade().size());
        PilhaObj<Especialidade> pilhaEspecialidade = new PilhaObj<>(dto.getListaIdEspecialidade().size());

        for (UUID idEspecialidade : dto.getListaIdEspecialidade()) {
            filaEspecialidades.insert(buscarEspecialidade(idEspecialidade));
        }

        EspecialidadeUsuario entidade = new EspecialidadeUsuario();
        entidade.setPsicologo(psicologo);

        while (!filaEspecialidades.isEmpty()) {
            Especialidade especialidade = filaEspecialidades.poll();
            salvarEspecialidadeUsuario(especialidade, entidade);
            pilhaEspecialidade.push(especialidade);
        }

        List<EspecialidadeUsuario> listaEspecialidades = new ArrayList<>();

        while (!pilhaEspecialidade.isEmpty()) {
            Especialidade especialidade = pilhaEspecialidade.pop();
            EspecialidadeUsuario espUsu = this.espUsuRepository.findByPsicologoIdAndEspecialidadeTitulo(psicologo.getId(), especialidade.getTitulo());
            listaEspecialidades.add(espUsu);
        }

        return EspecialidadeUsuarioMapper.toDto(listaEspecialidades);
    }

    public List<EspecialidadeUsuarioDtoConsultar> listarPorPsicologo(UUID idPsicologo) {
        if(!this.psiRepository.existsById(idPsicologo)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Psicólogo não encontrado");
        }

        List<EspecialidadeUsuario> lista = this.espUsuRepository.findAllByPsicologoId(idPsicologo);
        return EspecialidadeUsuarioMapper.toDto(lista);
    }

    public Especialidade buscarEspecialidade(UUID id) {
        return this.espRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Especialidade não encontrada"));
    }

    public void salvarEspecialidadeUsuario(Especialidade especialidade, EspecialidadeUsuario espUsu) {
        espUsu.setEspecialidade(especialidade);
        this.espUsuRepository.save(espUsu);
    }
}