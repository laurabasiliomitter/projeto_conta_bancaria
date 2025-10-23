package com.senai.projeto_conta_bancaria.application.dto;

import com.senai.projeto_conta_bancaria.domain.entity.Gerente;
import com.senai.projeto_conta_bancaria.domain.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record GerenteDTO(
        @Schema(description = "ID", example = "1")
        String id,
        @Schema(description = "nome", example = "Rafael")
        String nome,
        @Schema(description = "cpf", example = "123.456.789-10")
        String cpf,
        @Schema(description = "email", example = "rafaelsilva@gmail.com")
        String email,
        @Schema(description = "senha", example = "admin123")
        String senha,
        @Schema(description = "ativo", example = "ativo/desativado")
        Boolean ativo,
        Role role
) {

    public static GerenteDTO fromEntity(Gerente gerente) {
        return GerenteDTO.builder()
                .id(gerente.getId())
                .nome(gerente.getNome())
                .cpf(gerente.getCpf())
                .email(gerente.getEmail())
                .ativo(gerente.isAtivo())
                .role(gerente.getRole())
                .build();
    }

    public Gerente toEntity() {
        return Gerente.builder()
                .id(this.id)
                .nome(this.nome)
                .cpf(this.cpf)
                .email(this.email)
                .senha(this.senha)
                .ativo(this.ativo != null ? this.ativo : true)
                .role(this.role != null ? this.role : Role.GERENTE)
                .build();
    }


}
