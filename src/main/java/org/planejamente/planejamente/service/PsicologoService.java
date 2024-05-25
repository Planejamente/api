package org.planejamente.planejamente.service;

import org.planejamente.planejamente.dto.dtoConsultar.PsicologoDtoConsultar;
import org.planejamente.planejamente.entity.usuario.Psicologo;
import org.planejamente.planejamente.mapper.PsicologoMapper;
import org.planejamente.planejamente.oredenacao.QuickSort;
import org.planejamente.planejamente.repository.PsicologoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PsicologoService {
    private final PsicologoRepository repository;

    public PsicologoService(PsicologoRepository repository) {
        this.repository = repository;
    }

    public List<PsicologoDtoConsultar> listarTodos() {
        List<Psicologo> todos = this.repository.findAll();
        return PsicologoMapper.toDto(todos);
    }

    public Psicologo buscarPorId(UUID id) {
        return this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Psicologo não encontrado"));
    }

    public List<PsicologoDtoConsultar> listarPorGenero(String genero) {
        String generoLower = genero.toLowerCase();
        List<Psicologo> todos = this.repository.findByGenero(generoLower);
        return PsicologoMapper.toDto(todos);
    }

    public List<PsicologoDtoConsultar> listarOrdenado() {
        List<Psicologo> todos = this.repository.findAll();
        QuickSort.ordenarQuickSort(todos);
        return PsicologoMapper.toDto(todos);
    }

    public Boolean buscarPorCrp(String crp) {
        String[] lista = {"123456789", "987654321"};
        for (String s : lista) {
            if(crp.equals(s)) return true;
        }
        return false;
    }

    public List<List<PsicologoDtoConsultar>> listarEmMatriz(int colunas) {
        List<PsicologoDtoConsultar> todosPsicologos = listarTodos();
        List<List<PsicologoDtoConsultar>> matriz = new ArrayList<>();
        int linhaAtual = 0;

        for (int i = 0; i < todosPsicologos.size(); i++) {
            if (i % colunas == 0) {
                matriz.add(new ArrayList<>());
                linhaAtual++;
            }
            matriz.get(linhaAtual - 1).add(todosPsicologos.get(i));
        }

        return matriz;
    }


}
