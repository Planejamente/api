package org.planejamente.planejamente.repository;

import org.planejamente.planejamente.entity.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgendaRepository extends JpaRepository<Agenda, UUID> {
}