package com.senai.projeto_conta_bancaria.domain.exception;

public class ContaDoMesmoTipoExcepition extends RuntimeException {

    public ContaDoMesmoTipoExcepition() {
        super("O Cliente já possui uma conta desse tipo");
    }
}
