package org.planejamente.planejamenteapi.repositories;

import org.planejamente.planejamenteapi.Psicologo;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsicologoRepository extends JpaRepository<Psicologo, Integer> {
    Psicologo save(Psicologo psicologo);
}
