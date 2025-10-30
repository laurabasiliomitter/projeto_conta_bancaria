package com.senai.projeto_conta_bancaria.interface_ui.controller;

import com.senai.projeto_conta_bancaria.application.dto.AuthDTO;
import com.senai.projeto_conta_bancaria.application.service.AuthService;
import com.senai.projeto_conta_bancaria.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService auth;

    private final UsuarioRepository usuarios;

    @PostMapping("/login")
    public ResponseEntity<AuthDTO.TokenResponse> login(@RequestBody AuthDTO.LoginRequest req) {
        String token = auth.login(req);
        return ResponseEntity.ok(new AuthDTO.TokenResponse(token));
    }

    @GetMapping("/me")
    public AuthDTO.UserResponse me(Authentication auth) {
        var usuario = usuarios.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new AuthDTO.UserResponse(usuario.getNome(), usuario.getEmail(), usuario.getRole().name());
        }
}
