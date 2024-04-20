package org.planejamente.planejamente.controller;

import org.planejamente.planejamente.repository.AgendaRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/agendas")
public class AgendaController {
    private final AgendaRepository repository;

    public AgendaController(AgendaRepository repository) {
        this.repository = repository;
    }
}