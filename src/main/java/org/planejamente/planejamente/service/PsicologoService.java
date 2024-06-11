package org.planejamente.planejamente.service;

import org.planejamente.planejamente.dto.AuthCalendarId;
import org.planejamente.planejamente.dto.dtoConsultar.PsicologoDtoConsultar;
import org.planejamente.planejamente.dto.dtoCriar.PsicologoDto;
import org.planejamente.planejamente.entity.usuario.Psicologo;
import org.planejamente.planejamente.mapper.PsicologoMapper;
import org.planejamente.planejamente.oredenacao.QuickSort;
import org.planejamente.planejamente.repository.PsicologoRepository;
import org.planejamente.planejamente.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PsicologoService {
    private final PsicologoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final PsicologoMapper mapper;
    private final CalendarService calendarService;

    public PsicologoService(PsicologoRepository repository, UsuarioRepository usuarioRepository, CalendarService calendarService) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.mapper = new PsicologoMapper();
        this.calendarService = calendarService;
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

    public void salvar(PsicologoDto dto) throws GeneralSecurityException, IOException {
        var u = this.usuarioRepository.findByEmail(dto.getEmail());
        if(u != null) throw new ResponseStatusException(HttpStatus.CONFLICT, "Email já existe.");

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getGoogleSub());

        Psicologo psicologo = mapper.toEntity(dto);
        psicologo.setGoogleSub(encryptedPassword);

        AuthCalendarId idsCalendars = calendarService.createCalendars(dto.getAccessToken());
        psicologo.setIdCalendarioHorarioDeTrabalho(idsCalendars.calendarId1());
        psicologo.setIdCalendarioConsulta(idsCalendars.calendarId2());

        this.repository.save(psicologo);
    }
}