package com.senai.projeto_conta_bancaria.application.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ContaAtualizadaDTO(
        @NotNull(message = "O saldo não pode ser nulo.")
        @DecimalMin(value = "0.0", inclusive = true, message = "O saldo não pode ser negativo.")
        @Digits(integer = 10, fraction = 2, message = "O saldo deve ter até 10 dígitos inteiros e 2 casas decimais.")
        BigDecimal saldo,

        @NotNull(message = "O limite não pode ser nulo.")
        @DecimalMin(value = "0.0", inclusive = false, message = "O limite deve ser maior que zero.")
        @Digits(integer = 10, fraction = 2, message = "O limite deve ter até 10 dígitos inteiros e 2 casas decimais.")
        BigDecimal limite,

        @NotNull(message = "O rendimento não pode ser nulo.")
        @DecimalMin(value = "0.0", inclusive = true, message = "O rendimento não pode ser negativo.")
        @Digits(integer = 10, fraction = 2, message = "O rendimento deve ter até 10 dígitos inteiros e 2 casas decimais.")
        BigDecimal rendimento,

        @NotNull(message = "A taxa não pode ser nula.")
        @DecimalMin(value = "0.0", inclusive = true, message = "A taxa não pode ser negativa.")
        @Digits(integer = 10, fraction = 2, message = "A taxa deve ter até 10 dígitos inteiros e 2 casas decimais.")
        BigDecimal taxa
) {
}
