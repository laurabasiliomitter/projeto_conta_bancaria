package com.senai.projeto_conta_bancaria.domain.exception;

public class ContaDoMesmoTipoException extends RuntimeException {

    public ContaDoMesmoTipoException() {
        super("O Cliente já possui uma conta desse tipo");
    }
}
