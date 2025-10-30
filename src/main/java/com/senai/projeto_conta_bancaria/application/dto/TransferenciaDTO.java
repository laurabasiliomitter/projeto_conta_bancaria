package com.senai.projeto_conta_bancaria.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record TransferenciaDTO (

        @Schema(description = "contaDestino", example = "2222-2")
        @Size(min = 6, max = 12, message = "A conta de destino deve ter entre 6 e 12 caracteres.")
        String contaDestino,

        @Schema(description = "valor", example = "100.00")
        @DecimalMin(value = "0.01", inclusive = true, message = "O valor da transferência deve ser maior que zero.")
        @Digits(integer = 10, fraction = 2, message = "O valor deve ter até 10 dígitos inteiros e 2 casas decimais.")
        BigDecimal valor
){

}
