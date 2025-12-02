package com.senai.projeto_conta_bancaria.application.dto;

import com.senai.projeto_conta_bancaria.domain.entity.Pagamento;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PagamentoDTO (
        @Schema(description = "ID Pagamento", example = "1")
        @NotNull(message = "ID não pode ser nulo")
        String id,

        @NotNull(message = "Conta não pode ser nulo.")
        String conta,

        @NotNull(message = "Boleto não pode ser nulo.")
        String boleto,

        @NotNull(message = "Valor não pode ser nulo.")
        BigDecimal valorPago,

        @NotNull(message = "Data não pode ser nulo.")
        LocalDateTime dataPagamento,

        @NotNull(message = "Status não pode ser nulo.")
        String status,

        @NotNull(message = "Taxa não pode ser nulo.")
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
