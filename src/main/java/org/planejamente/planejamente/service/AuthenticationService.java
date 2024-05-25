package org.planejamente.planejamente.service;

import org.planejamente.planejamente.dto.AuthResponseDto;
import org.planejamente.planejamente.dto.AuthenticationDto;
import org.planejamente.planejamente.entity.usuario.Usuario;
import org.planejamente.planejamente.infra.security.TokenService;
import org.planejamente.planejamente.repository.UsuarioRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final TokenService service;
    private final UsuarioRepository repository;

    public AuthenticationService(AuthenticationManager authenticationManager, TokenService service, UsuarioRepository repository) {
        this.authenticationManager = authenticationManager;
        this.service = service;
        this.repository = repository;
    }

    public AuthResponseDto login(AuthenticationDto data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.googleSub());
        var auth = this.authenticationManager.authenticate(usernamePassword);

        String tipoUsuario = getTipoUsuario(data.email());

        var token = this.service.generateToken((Usuario) auth.getPrincipal(), tipoUsuario);
        return new AuthResponseDto(token);
    }

    public String getTipoUsuario(String email) {
        Usuario usuario = this.repository.findFirstByEmail(email);
        if(Objects.isNull(usuario)) return "";

        String role = usuario.getRole().getRole();
        if(role.isBlank()) return "";

        return role.equalsIgnoreCase("ADMIN") ? "psicologo" : "paciente";
    }
}