package com.senai.projeto_conta_bancaria.domain.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {
    public EntidadeNaoEncontradaException(String entidade) {
        super(entidade + " não existente ou inativo(a)!");
    }
}
