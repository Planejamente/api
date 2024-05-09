package org.planejamente.planejamente.service;

import jakarta.servlet.http.HttpServletResponse;
import org.planejamente.planejamente.dto.dtoConsultar.PacienteDtoConsultar;
import org.planejamente.planejamente.entity.usuario.Paciente;
import org.planejamente.planejamente.mapper.PacienteMapper;
import org.planejamente.planejamente.repository.PacienteRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.FormatterClosedException;
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

    public void gravaArquivoCsv(String nomeArq) {
        List<Paciente> lista = this.repository.findAll();
        FileWriter arq = null;
        PrintWriter saida = null;
        Boolean deuRuim = false;

        try {
            arq = new FileWriter(nomeArq);
            saida = new PrintWriter(arq);

        } catch (IOException erro) {
            System.out.println("Erro ao abrir o arquivo");
            System.exit(1);
        }

        try {
            for (int i = 0; i < lista.size(); i++) {
                Paciente paciente = lista.get(i);
                saida.println(paciente.getId() + ";" + paciente.getNome() + ";" + paciente.getEmail() + ";" );
            }
        } catch (FormatterClosedException erro) {
            System.out.println("Erro ao gravar o arquivo");
            deuRuim = true;
        } finally {
            saida.close();
            try {
                arq.close();
            } catch (IOException erro) {
                System.out.println("Erro ao fechar o arquivo");
                deuRuim = true;
            }
            if (deuRuim) {
                System.exit(1);
            }
        }
    }

}
