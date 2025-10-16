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
    @CollectionTable(name = "gerente_turmas", joinColumns=@JoinColumn(name="gerentes_id"))
    @Column(name = "uc")
    private List<String> listaDeUC;
}
