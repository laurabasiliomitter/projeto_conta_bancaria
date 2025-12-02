package com.senai.projeto_conta_bancaria.domain.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("PAGAMENTO")
@Data
@SuperBuilder
@NoArgsConstructor

public class Pagamento extends Conta {

    private String id;

    private String conta;

    private String boleto;

    private String valorPago;

    private LocalDateTime dataPagamento;

    private String status;

    private String taxas;


    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta contaAssociadas;

}
