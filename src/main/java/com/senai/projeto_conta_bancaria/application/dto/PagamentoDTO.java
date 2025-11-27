package com.senai.projeto_conta_bancaria.application.dto;

import com.senai.projeto_conta_bancaria.domain.entity.Pagamento;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagamentoDTO (
        String id,
        String conta,
        String boleto,
        BigDecimal valorPago,
        LocalDateTime dataPagamento,
        String status,
        String taxas
){
    public static PagamentoDTO fromEntity(Pagamento pagamento) {
        return new PagamentoDTO(
                pagamento.getId(),
                pagamento.getConta().getId(),
                pagamento.getBoleto(),
                pagamento.getValorPago(),
                pagamento.getDataPagamento(),
                pagamento.getStatus(),
                pagamento.getTaxas()
        );
    }


}
