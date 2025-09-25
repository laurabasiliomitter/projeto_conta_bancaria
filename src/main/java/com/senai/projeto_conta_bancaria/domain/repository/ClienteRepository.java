package com.senai.projeto_conta_bancaria.domain.repository;

import com.senai.projeto_conta_bancaria.domain.entity.Cliente;
import com.senai.projeto_conta_bancaria.domain.entity.Conta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
    Optional<Cliente> findByCpfAndAtivoTrue(String cpf);

    List<Cliente> findAllByAtivoTrue();
}
