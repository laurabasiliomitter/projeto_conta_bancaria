package com.senai.projeto_conta_bancaria.application.dto;

import com.senai.projeto_conta_bancaria.domain.entity.Cliente;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;



public record ClienteResponseDTO(

        @Schema(description = "ID Cliente", example = "1")
        @NotNull(message = "O ID não pode ser nulo.")
        String id,

        @Schema(description = "nome", example = "Rafael")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
        String nome,

        @Schema(description = "cpf", example = "123.456.789.10")
        @Pattern(regexp = "\\d{11}", message = "CPF deve conter apenas 11 números")
        String cpf,

        @NotNull(message = "A lista de contas não pode ser nula.")
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
