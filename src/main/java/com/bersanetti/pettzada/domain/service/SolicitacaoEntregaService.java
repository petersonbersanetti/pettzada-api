package com.bersanetti.pettzada.domain.service;

import com.bersanetti.pettzada.domain.exception.NegocioException;
import com.bersanetti.pettzada.domain.model.Cliente;
import com.bersanetti.pettzada.domain.model.Entrega;
import com.bersanetti.pettzada.domain.model.StatusEntrega;
import com.bersanetti.pettzada.domain.repositoy.ClienteRepository;
import com.bersanetti.pettzada.domain.repositoy.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

    private CatalogoClienteService catalogoClienteService;
    private EntregaRepository entregaReposiroty;

    @Transactional
    public Entrega solicitar(Entrega entrega) {
        Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());

        return entregaReposiroty.save(entrega);
    }



}
