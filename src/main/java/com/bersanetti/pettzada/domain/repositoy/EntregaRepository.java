package com.bersanetti.pettzada.domain.repositoy;

import com.bersanetti.pettzada.domain.model.Entrega;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long> {
}
