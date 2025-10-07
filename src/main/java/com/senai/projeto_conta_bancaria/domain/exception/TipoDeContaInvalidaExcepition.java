package com.senai.projeto_conta_bancaria.domain.exception;

public class TipoDeContaInvalidaExcepition extends RuntimeException {

    public TipoDeContaInvalidaExcepition(String tipo) {

        super("Tipo de conta" + tipo + " inválida. Os tipos aceitos são: 'CORRENTE' ou 'POUPANCA'");
    }
}
