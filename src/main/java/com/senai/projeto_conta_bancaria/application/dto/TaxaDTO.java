package com.senai.projeto_conta_bancaria.application.dto;

import com.senai.projeto_conta_bancaria.domain.entity.Taxa;

import java.math.BigDecimal;

public class TaxaDTO(
        String id,
        String descricao,
        BigDecimal percentual,
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
