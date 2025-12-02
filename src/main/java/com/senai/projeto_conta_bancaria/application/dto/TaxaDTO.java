package com.senai.projeto_conta_bancaria.application.dto;

import com.senai.projeto_conta_bancaria.domain.entity.Taxa;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class TaxaDTO(
        @Schema(description = "ID Pagamento", example = "1")
        @NotNull(message = "ID não pode ser nulo")
        String id,

        @NotNull(message = "Descriçao ***** ***** ***** ***** não pode ser nula ")
        String descricao,

        @NotNull(message = "*,*% não pode ser nulo")
        BigDecimal percentual,

        @NotNull(message = "Valor não pode ser nulo")
        BigDecimal valorFixo
) {


    public static TaxaDTO fromEntity(Taxa taxa) {
        return new TaxaDTO(
                taxa.getId(),
                taxa.getDescricao(),
                taxa.getPercentual(),
                taxa.getValorFixo()
        );
    }



}
