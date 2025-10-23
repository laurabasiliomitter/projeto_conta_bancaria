package com.senai.projeto_conta_bancaria.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ValorSaqueDepositoDTO(
        @Schema(description = "valor", example = "100.00")
        @NotNull(message = "O valor é obrigatório.")
        @DecimalMin(value = "0.01", inclusive = true, message = "O valor deve ser maior que zero.")
        @Digits(integer = 10, fraction = 2, message = "O valor deve ter até 10 dígitos inteiros e 2 casas decimais.")
        BigDecimal valor
) {
}
