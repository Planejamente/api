package org.planejamente.planejamente.controller;

import org.planejamente.planejamente.dto.AuthCalendar;
import org.planejamente.planejamente.dto.AuthCalendarId;
import org.planejamente.planejamente.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/calendario")
public class CalendarController {

    @Autowired
    private CalendarService calendarService;
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody AuthCalendar accessToken) {
        try {
            if (accessToken.bearerToken() == null || accessToken.accessToken().isBlank()) {
                return ResponseEntity.badRequest().body("O token de acesso não pode estar vazio.");
            }

            AuthCalendarId dto = calendarService.createCalendars(accessToken.accessToken());

            return ResponseEntity.ok(dto);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Token de acesso inválido: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor: " + e.getMessage());
        }
    }

}
