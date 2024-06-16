package org.planejamente.planejamente.dto.dtoCriar;

import java.util.List;

public class RelatorioMensalDTO {
    private String nomePsicologo;
    private List<String> nomesPacientes;
    private double percentualPacientes;

    public RelatorioMensalDTO(String nomePsicologo, List<String> nomesPacientes, double percentualPacientes) {
        this.nomePsicologo = nomePsicologo;
        this.nomesPacientes = nomesPacientes;
        this.percentualPacientes = percentualPacientes;
    }

    // Getters e setters
    public String getNomePsicologo() {
        return nomePsicologo;
    }

    public void setNomePsicologo(String nomePsicologo) {
        this.nomePsicologo = nomePsicologo;
    }

    public List<String> getNomesPacientes() {
        return nomesPacientes;
    }

    public void setNomesPacientes(List<String> nomesPacientes) {
        this.nomesPacientes = nomesPacientes;
    }

    public double getPercentualPacientes() {
        return percentualPacientes;
    }

    public void setPercentualPacientes(double percentualPacientes) {
        this.percentualPacientes = percentualPacientes;
    }
}
