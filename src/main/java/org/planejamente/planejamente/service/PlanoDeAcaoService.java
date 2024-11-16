package org.planejamente.planejamente.service;

import org.planejamente.planejamente.dto.dtoConsultar.PlanoDeAcaoDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.PlanoDeAcaoDto;
import org.planejamente.planejamente.dto.dtoCriar.PlanoDeAcaoTarefasDto;
import org.planejamente.planejamente.entity.Consulta;
import org.planejamente.planejamente.entity.PlanoDeAcao;
import org.planejamente.planejamente.entity.PlanoDeAcaoTarefas;
import org.planejamente.planejamente.mapper.PlanoDeAcaoMapper;
import org.planejamente.planejamente.mapper.PlanoDeAcaoTarefasMapper;
import org.planejamente.planejamente.repository.ConsultaRepository;
import org.planejamente.planejamente.repository.PlanoDeAcaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class PlanoDeAcaoService {
    private final PlanoDeAcaoRepository repository;
    private final ConsultaRepository consultaRepository;

    public PlanoDeAcaoService(PlanoDeAcaoRepository repository, ConsultaRepository consultaRepository) {
        this.repository = repository;
        this.consultaRepository = consultaRepository;
    }

    public List<PlanoDeAcaoDtoConsultar> buscarPorConsulta(UUID idConsulta) {
        List<PlanoDeAcao> todos = this.repository.findByConsulta_Id(idConsulta);
        return PlanoDeAcaoMapper.toDto(todos);
    }

    public List<PlanoDeAcaoDtoConsultar> buscarPorUsuario(UUID idUsuario) {
        List<PlanoDeAcao> todos = this.repository.findByConsulta_Paciente_IdOrConsulta_Psicologo_Id(idUsuario, idUsuario);
        return PlanoDeAcaoMapper.toDto(todos);
    }

    public PlanoDeAcaoDtoConsultar salvar(PlanoDeAcaoDto dto) {
        Consulta consulta = this.consultaRepository.findById(dto.getIdConsulta())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Consulta não encontrada"));

        PlanoDeAcao planoDeAcao = PlanoDeAcaoMapper.toEntity(dto);
        planoDeAcao.setConsulta(consulta);

        PlanoDeAcao salvo = this.repository.save(planoDeAcao);
        return PlanoDeAcaoMapper.toDto(salvo);
    }

    public PlanoDeAcaoDtoConsultar atualizarTarefa(UUID idPlanoDeAcao, String idTarefa) {
        PlanoDeAcao pda = this.repository.findById(idPlanoDeAcao)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano de acao não encontrado"));

        PlanoDeAcaoTarefas tarefaDesatualizada = pda.getTarefas().stream().filter(tarefa -> tarefa.getIdTarefa().equals(idTarefa)).findFirst().get();
        if(tarefaDesatualizada.getStatus().equals("afazer")) {
            tarefaDesatualizada.setStatus("feito");
        } else {
            tarefaDesatualizada.setStatus("afazer");
        }

        this.repository.save(pda);
        return PlanoDeAcaoMapper.toDto(pda);
    }

    public PlanoDeAcaoDtoConsultar atualizar(UUID idPlanoDeAcao) {
        PlanoDeAcao pda = this.repository.findById(idPlanoDeAcao)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano de acao não encontrado"));

        pda.getTarefas().forEach(tarefa -> tarefa.setStatus("feito"));
        this.repository.save(pda);

        return PlanoDeAcaoMapper.toDto(pda);
    }

    public PlanoDeAcaoDtoConsultar adicionarTarefa(UUID idPlanoDeAcao, PlanoDeAcaoTarefasDto tarefa) {
        PlanoDeAcao pda = this.repository.findById(idPlanoDeAcao)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano de acao não encontrado"));

        PlanoDeAcaoTarefas novaTarefa = PlanoDeAcaoTarefasMapper.toEntity(tarefa);
        List<PlanoDeAcaoTarefas> tarefas = pda.getTarefas();
        tarefas.add(novaTarefa);

        pda.setTarefas(tarefas);
        this.repository.save(pda);
        return PlanoDeAcaoMapper.toDto(pda);
    }

    public PlanoDeAcaoDtoConsultar editarTarefa(UUID idPlanoDeAcao, String idTarefa, PlanoDeAcaoTarefasDto tarefa) {
        PlanoDeAcao pda = this.repository.findById(idPlanoDeAcao)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano de acao não encontrado"));

        PlanoDeAcaoTarefas tarefaEditada = pda.getTarefas()
                .stream()
                .filter(t -> t.getIdTarefa().equals(idTarefa))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));
        PlanoDeAcaoTarefasMapper.merge(tarefaEditada, PlanoDeAcaoTarefasMapper.toEntity(tarefa));

        this.repository.save(pda);
        return PlanoDeAcaoMapper.toDto(pda);
    }

    public PlanoDeAcaoDtoConsultar deletarUltimaTarefa(UUID idPlanoDeAcao) {
        PlanoDeAcao pda = this.repository.findById(idPlanoDeAcao)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano de acao não encontrado"));

        List<PlanoDeAcaoTarefas> tarefas = pda.getTarefas();
        tarefas.remove(tarefas.size() - 1);

        pda.setTarefas(tarefas);
        this.repository.save(pda);
        return PlanoDeAcaoMapper.toDto(pda);
    }

    public PlanoDeAcaoDtoConsultar deletarTarefa(UUID idPlanoDeAcao, String idTarefa) {
        PlanoDeAcao pda = this.repository.findById(idPlanoDeAcao)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano de acao não encontrado"));

        List<PlanoDeAcaoTarefas> tarefas = pda.getTarefas();
        tarefas.removeIf(t -> t.getIdTarefa().equals(idTarefa));

        pda.setTarefas(tarefas);
        this.repository.save(pda);
        return PlanoDeAcaoMapper.toDto(pda);
    }

    public PlanoDeAcaoDtoConsultar editarTitulo(UUID idPlanoDeAcao, String titulo) {
        PlanoDeAcao pda = this.repository.findById(idPlanoDeAcao)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Plano de acao não encontrado"));

        pda.setTituloPlanoDeAcao(titulo);
        this.repository.save(pda);
        return PlanoDeAcaoMapper.toDto(pda);
    }
}
