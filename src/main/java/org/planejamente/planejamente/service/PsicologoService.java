package org.planejamente.planejamente.service;

import org.planejamente.planejamente.dto.AuthCalendarId;
import org.planejamente.planejamente.dto.dtoAtualizar.PsicologoDtoAtualizar;
import org.planejamente.planejamente.dto.dtoConsultar.PsicologoDtoConsultar;
import org.planejamente.planejamente.dto.dtoConsultar.PsicologoDtoExibir;
import org.planejamente.planejamente.dto.dtoCriar.EnderecoDto;
import org.planejamente.planejamente.dto.dtoCriar.PsicologoDto;
import org.planejamente.planejamente.entity.Consulta;
import org.planejamente.planejamente.entity.Endereco;
import org.planejamente.planejamente.entity.Especialidade;
import org.planejamente.planejamente.entity.ExperienciaFormacao;
import org.planejamente.planejamente.entity.usuario.Psicologo;
import org.planejamente.planejamente.mapper.EnderecoMapper;
import org.planejamente.planejamente.mapper.PsicologoMapper;
import org.planejamente.planejamente.oredenacao.QuickSort;
import org.planejamente.planejamente.repository.*;
import org.planejamente.planejamente.util.PilhaObj;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class PsicologoService {
    private final PsicologoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final PsicologoMapper mapper;
    private final EspecialidadeRepository especialidadeRepository;
    private final ExperienciaFormacaoRepository expFormRepository;
    private final EnderecoRepository enderecoRepository;
    private final ConsultaRepository consultaRepository;
    private final CalendarService calendarService;

    public PsicologoService(PsicologoRepository repository, UsuarioRepository usuarioRepository,
                            EspecialidadeRepository especialidadeRepository, ExperienciaFormacaoRepository expFormRepository,
                            EnderecoRepository enderecoRepository, ConsultaRepository consultaRepository, CalendarService calendarService) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.especialidadeRepository = especialidadeRepository;
        this.expFormRepository = expFormRepository;
        this.enderecoRepository = enderecoRepository;
        this.consultaRepository = consultaRepository;
        this.mapper = new PsicologoMapper();
        this.calendarService = calendarService;
    }

    public List<PsicologoDtoConsultar> listarTodos() {
        List<Psicologo> todos = this.repository.findAll();
        return PsicologoMapper.toDto(todos);
    }

    public List<PsicologoDtoExibir> listarComFiltro(String genero, String cidade, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim) {
        List<Psicologo> listaPsi = this.repository.findAllByGeneroEqualsIgnoreCase(genero);
        List<PsicologoDtoExibir> listaDto = new ArrayList<>();
        PilhaObj<Psicologo> usuariosParaDeletar = new PilhaObj<>(listaPsi.size());

        if(!listaPsi.isEmpty()) {
            for (Psicologo psicologo : listaPsi) {
                UUID idPsi = psicologo.getId();
                boolean existe = this.consultaRepository.existsByPsicologoIdAndFimBetween(idPsi, dataHoraInicio, dataHoraFim) || this.consultaRepository.existsByPsicologoIdAndInicioBetween(idPsi, dataHoraInicio, dataHoraFim);

                if(existe) usuariosParaDeletar.push(psicologo);
            }

            while (!usuariosParaDeletar.isEmpty()) {
                listaPsi.remove(usuariosParaDeletar.pop());
            }

            for (Psicologo psicologo : listaPsi) {
                UUID idPsi = psicologo.getId();
                boolean existe = this.enderecoRepository.existsByUsuarioIdAndCidadeNotIgnoreCase(idPsi, cidade);
                if(existe) usuariosParaDeletar.push(psicologo);
            }

            while (!usuariosParaDeletar.isEmpty()) {
                listaPsi.remove(usuariosParaDeletar.pop());
            }

            for (Psicologo psicologo : listaPsi) {
                listaDto.add(buscarPorId(psicologo.getId()));
            }
        }

        return listaDto;
    }

    public PsicologoDtoExibir buscarPorId(UUID id) {
        Psicologo psi = this.repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Psicologo não encontrado"));

        List<Especialidade> especialidades = this.especialidadeRepository.findAllByPsicologoId(psi.getId());
        psi.setEspecialidades(especialidades);

        List<ExperienciaFormacao> expForm = this.expFormRepository.findByPsicologoId(psi.getId());
        psi.setExperienciaFormacoes(expForm);

        Endereco endereco = this.enderecoRepository.findByUsuarioId(psi.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar um endereço dessa psicólogo"));
        psi.setEndereco(endereco);

        PsicologoDtoExibir dto = PsicologoMapper.toDtoCompleta(psi);

        Integer quantidadeConsultas = this.consultaRepository.countConsultaByPsicologoId(psi.getId());
        dto.setQtdAtendimentos(quantidadeConsultas);

        List<Consulta> consultas = this.consultaRepository.findAllByPsicologoIdAndFimBefore(psi.getId(), LocalDateTime.now());

        Double somaNota = 0.0;
        for (Consulta consulta : consultas) {
            somaNota += consulta.getNota();
        }

        Integer qtdConsultasAvaliadas = this.consultaRepository.countConsultaByPsicologoIdAndFimBefore(psi.getId(), LocalDateTime.now());
        dto.setAvaliacao(consultas.isEmpty() ? 0.0 : somaNota / qtdConsultasAvaliadas);
        return dto;
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

        Psicologo criado = this.repository.save(psicologo);
        EnderecoDto enderecoAcriar = new EnderecoDto();
        enderecoAcriar.setCep(dto.getEndereco().getCep());
        enderecoAcriar.setIdUsuario(criado.getId());
        System.out.println("caralho" + criado.getId());
        enderecoAcriar.setEstado(dto.getEndereco().getEstado());
        enderecoAcriar.setCidade(dto.getEndereco().getCidade());
        enderecoAcriar.setRua(dto.getEndereco().getRua());
        Endereco endereco = EnderecoMapper.toEntity(enderecoAcriar);
        endereco.setUsuario(criado);
        this.enderecoRepository.save(endereco);
    }

    public PsicologoDtoConsultar atualizar(PsicologoDtoAtualizar psiAtualizado, UUID idPsi) {
        Psicologo psicologo = this.repository.findById(idPsi)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Psicólogo não encontrado"));

        Psicologo psicologoAtualizado = PsicologoMapper.merge(psicologo, psiAtualizado);
        Psicologo psiAtualizadoSalvo = this.repository.save(psicologoAtualizado);
        return PsicologoMapper.toDto(psiAtualizadoSalvo);
    }
}