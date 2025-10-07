package com.senai.projeto_conta_bancaria.domain.exception;

public class RendimentoInvalidoException extends RuntimeException {

    public RendimentoInvalidoException() {
        super("O Rendimento deve ser aplicado somente em conta Poupança");
    }
}
