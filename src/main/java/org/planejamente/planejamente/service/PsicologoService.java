package org.planejamente.planejamente.service;

import org.planejamente.planejamente.dto.dtoConsultar.PsicologoDtoConsultar;
import org.planejamente.planejamente.entity.usuario.Psicologo;
import org.planejamente.planejamente.mapper.PsicologoMapper;
import org.planejamente.planejamente.oredenacao.QuickSort;
import org.planejamente.planejamente.repository.PsicologoRepository;
import org.springframework.stereotype.Service;

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

    public PsicologoDtoConsultar buscarPorId(UUID id) {
        Psicologo psicologoBuscado = this.repository.findById(id).orElse(null);
        return PsicologoMapper.toDto(psicologoBuscado);
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
}
