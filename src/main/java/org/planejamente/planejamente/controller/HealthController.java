package org.planejamente.planejamente.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-bro")
@CrossOrigin
public class HealthController {

    @GetMapping()
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Ta tudo bem!");
    }
}