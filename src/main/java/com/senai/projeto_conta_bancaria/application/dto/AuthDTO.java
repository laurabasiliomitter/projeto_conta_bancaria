package com.senai.projeto_conta_bancaria.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public class AuthDTO {

    public record LoginRequest(
            @Schema(description = "email", example = "rafaelsilva@gmail")
            @NotNull(message = "email é obrigatório")
            String email,

            @Schema(description = "senha", example = "admin123")
            @NotNull(message = "senha é obrigatória")
            String senha
    ) {}
    public record TokenResponse(
            @Schema(description = "token", example = "1111-1")
            String token
    ) {}
}
