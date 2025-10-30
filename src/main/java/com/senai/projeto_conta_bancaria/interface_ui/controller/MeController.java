package com.senai.projeto_conta_bancaria.interface_ui.controller;

import com.senai.projeto_conta_bancaria.application.dto.AuthDTO;
import com.senai.projeto_conta_bancaria.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MeController {

    private final UsuarioRepository usuarios;

    @GetMapping("/me")
    public AuthDTO.UserResponse me(Authentication auth) {
        var usuario = usuarios.findByEmail(auth.getName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return new AuthDTO.UserResponse(usuario.getNome(), usuario.getEmail(), usuario.getRole().name());
    }
}
