package org.planejamente.planejamente.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class Consulta {
    private UUID uuid;
    private UUID fkPsicologo;
    private UUID fkPaciente;
    private LocalDateTime dataHoraMarcada;

    public Consulta(UUID fkPsicologo, UUID fkPaciente, LocalDateTime dataHoraMarcada) {
        this.uuid = UUID.randomUUID();
        this.fkPsicologo = fkPsicologo;
        this.fkPaciente = fkPaciente;
        this.dataHoraMarcada = dataHoraMarcada;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public UUID getFkPsicologo() {
        return fkPsicologo;
    }

    public void setFkPsicologo(UUID fkPsicologo) {
        this.fkPsicologo = fkPsicologo;
    }

    public UUID getFkPaciente() {
        return fkPaciente;
    }

    public void setFkPaciente(UUID fkPaciente) {
        this.fkPaciente = fkPaciente;
    }

    public LocalDateTime getDataHoraMarcada() {
        return dataHoraMarcada;
    }

    public void setDataHoraMarcada(LocalDateTime dataHoraMarcada) {
        this.dataHoraMarcada = dataHoraMarcada;
    }
}
