package com.senai.projeto_conta_bancaria.application.dto;

import com.senai.projeto_conta_bancaria.domain.entity.Cliente;
import com.senai.projeto_conta_bancaria.domain.entity.Conta;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.util.ArrayList;

public record ClienteRegistroDTO(
        @Schema(description = "Nome", example = "Rafael")
        @NotNull(message = "O nome é obrigatório.")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
        String nome,

        @Schema(description = "cpf", example = "123.456.789-10" )
        @NotNull(message = "O CPF é obrigatório.")
        @Pattern(regexp = "\\d{11}", message = "CPF deve conter apenas 11 números")
        String cpf,

        @Schema(description = "senha", example = "******" )
        @NotNull(message = "A senha é obrigatória.")
        String senha,

        @Valid
        @NotNull(message = "A conta é obrigatória.")
        ContaResumoDTO contaDTO
) {
    public Cliente toEntity() {
        return Cliente.builder()
                .ativo(true)
                .nome(this.nome)
                .cpf(this.cpf)
                .senha(this.senha)
                .contas(new ArrayList<Conta>())
                .build();
    }
}
