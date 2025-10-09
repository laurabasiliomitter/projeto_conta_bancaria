package com.senai.projeto_conta_bancaria.application.dto;

import com.senai.projeto_conta_bancaria.domain.entity.Cliente;
import com.senai.projeto_conta_bancaria.domain.entity.Conta;
import com.senai.projeto_conta_bancaria.domain.entity.ContaCorrente;
import com.senai.projeto_conta_bancaria.domain.entity.ContaPoupanca;
import com.senai.projeto_conta_bancaria.domain.exception.TipoDeContaInvalidaException;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record ContaResumoDTO(
        @NotNull(message = "O número da conta não pode ser nulo.")
        @Size(min = 6, max = 12, message = "O número da conta deve ter entre 6 e 12 caracteres.")
        String numero,

        @NotNull(message = "O tipo de conta não pode ser nulo.")
        @Pattern(regexp = "CORRENTE|POUPANCA", message = "O tipo de conta deve ser 'CORRENTE' ou 'POUPANCA'.")
        String tipo,

        @NotNull(message = "O saldo não pode ser nulo.")
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
