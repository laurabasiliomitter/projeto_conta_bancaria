package com.senai.projeto_conta_bancaria.domain.exception;

public class SaldoInsuficienteExcepition extends RuntimeException {

    public SaldoInsuficienteExcepition(String operacao) {

        super( "Saldo insuficiente para realizar a operação de " + operacao);
    }

}
