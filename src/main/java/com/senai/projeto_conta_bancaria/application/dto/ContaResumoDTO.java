package com.senai.projeto_conta_bancaria.application.dto;

import com.senai.projeto_conta_bancaria.domain.entity.Cliente;
import com.senai.projeto_conta_bancaria.domain.entity.Conta;
import com.senai.projeto_conta_bancaria.domain.entity.ContaCorrente;
import com.senai.projeto_conta_bancaria.domain.entity.ContaPoupanca;
import com.senai.projeto_conta_bancaria.domain.exception.TipoDeContaInvalidaException;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ContaResumoDTO(
        @Schema(description = "numero", example = "1111-1")
        @Size(min = 6, max = 12, message = "O número da conta deve ter entre 6 e 12 caracteres.")
        String numero,

        @Schema(description = "tipo", example = "CORRENTE/POUPANCA")
        @Pattern(regexp = "CORRENTE|POUPANCA", message = "O tipo de conta deve ser 'CORRENTE' ou 'POUPANCA'.")
        String tipo,

        @Schema(description = "saldo", example = "100.00")
        @DecimalMin(value = "0.0", inclusive = true, message = "O saldo não pode ser negativo.")
        @Digits(integer = 10, fraction = 2, message = "O saldo deve ter até 10 dígitos inteiros e 2 casas decimais.")
        BigDecimal saldo
) {

    public Conta toEntity(Cliente cliente) {
        if("CORRENTE".equalsIgnoreCase(tipo)) {
            return ContaCorrente.builder()
                    .cliente(cliente)
                    .numero(this.numero)
                    .saldo(this.saldo)
                    .taxa(new BigDecimal("0.05"))
                    .limite(new BigDecimal("500.00"))
                    .ativa(true)
                    .build();
        } else if ("POUPANCA".equalsIgnoreCase(tipo)) {
            return ContaPoupanca.builder()
                    .cliente(cliente)
                    .numero(this.numero)
                    .saldo(this.saldo)
                    .rendimento(new BigDecimal("0.01"))
                    .ativa(true)
                    .build();
        }
        throw new TipoDeContaInvalidaException(tipo);
    }

    public static ContaResumoDTO fromEntity(Conta conta) {
        return new ContaResumoDTO(
                conta.getNumero(),
                conta.getTipo(),
                conta.getSaldo()
        );
    }
}
