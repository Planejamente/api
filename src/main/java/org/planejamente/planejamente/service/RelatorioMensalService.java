package org.planejamente.planejamente.service;

import org.planejamente.planejamente.dto.dtoCriar.RelatorioMensalDTO;
import org.planejamente.planejamente.repository.ConsultaRepository;
import org.planejamente.planejamente.repository.PsicologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;
import java.util.UUID;

@Service
public class RelatorioMensalService {

    @Autowired
    private PsicologoRepository psicologoRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    public RelatorioMensalDTO gerarRelatorioMensal(UUID psicologoId, int mes) {
        LocalDate agora = LocalDate.now();
        YearMonth anoMes = YearMonth.of(agora.getYear(), mes);
        LocalDateTime inicioMes = anoMes.atDay(1).atStartOfDay();
        LocalDateTime fimMes = anoMes.atEndOfMonth().atTime(LocalTime.MAX);

        LocalDateTime inicioAno = LocalDateTime.of(agora.getYear(), 1, 1, 0, 0);
        LocalDateTime fimAno = LocalDateTime.of(agora.getYear(), 12, 31, 23, 59);

        if (!psicologoRepository.existsById(psicologoId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Psicólogo não encontrado");
        }

        String nomePsicologo = psicologoRepository.findNomeById(psicologoId);
        List<String> nomesPacientes = consultaRepository.findNomesPacientesNoMes(psicologoId, inicioMes, fimMes);

        long totalPacientesAno = consultaRepository.countTotalPacientesNoAno(inicioAno, fimAno);
        double percentualPacientes = (double) nomesPacientes.size() / totalPacientesAno * 100;

        return new RelatorioMensalDTO(nomePsicologo, nomesPacientes, percentualPacientes);
    }
}

