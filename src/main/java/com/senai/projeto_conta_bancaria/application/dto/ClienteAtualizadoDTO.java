package com.senai.projeto_conta_bancaria.application.dto;

import com.senai.projeto_conta_bancaria.domain.entity.Cliente;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

public record ClienteAtualizadoDTO(
        @NotNull(message = "O ID não pode ser nulo.")
        String id,

        @NotNull(message = "O nome é obrigatório.")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
        String nome,

        @NotNull(message = "O CPF é obrigatório.")
        @Pattern(regexp = "\\d{11}", message = "CPF deve conter apenas 11 números")
        String cpf,

        @NotNull(message = "A lista de contas não pode ser nula.")
        @Size(min = 1, message = "Deve haver pelo menos uma conta associada ao cliente.")
        List<ContaResumoDTO> contas
) {

    public static ClienteResponseDTO fromEntity(Cliente cliente) {
        List<ContaResumoDTO> contas = cliente.getContas().stream().map(ContaResumoDTO::fromEntity).toList();
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpf(),
                contas
        );
    }

}
