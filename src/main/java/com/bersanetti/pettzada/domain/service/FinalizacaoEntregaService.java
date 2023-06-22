package com.bersanetti.pettzada.domain.service;

import com.bersanetti.pettzada.domain.exception.NegocioException;
import com.bersanetti.pettzada.domain.model.Entrega;
import com.bersanetti.pettzada.domain.model.StatusEntrega;
import com.bersanetti.pettzada.domain.repositoy.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

    private EntregaRepository entregaRepository;
    private BuscaEntregaService buscaEntregaService;

    @Transactional
    public void finaliza(Long entregId){
        Entrega entrega = buscaEntregaService.buscar(entregId);
        entrega.finalizar();

        entregaRepository.save(entrega);

    }

}
