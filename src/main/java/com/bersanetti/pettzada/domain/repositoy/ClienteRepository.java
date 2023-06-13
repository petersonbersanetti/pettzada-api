package com.bersanetti.pettzada.domain.repositoy;

import com.bersanetti.pettzada.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long> {

    List<Cliente> findByNome(String nome);
}
