package com.senai.projeto_conta_bancaria.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name="gerentes ")
public class Gerente extends Usuario{

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "gerente_contas", joinColumns=@JoinColumn(name="gerentes_id"))
    @Column(name = "conta")
    private List<String> listaDeContas;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name= "gerente_clientes", joinColumns=@JoinColumn(name="gerente_id"))
    @Column(name="cliente")
    private List<String> listaDeClientes;

}
