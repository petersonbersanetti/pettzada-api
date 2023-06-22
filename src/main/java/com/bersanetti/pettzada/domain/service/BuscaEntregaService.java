package com.bersanetti.pettzada.domain.service;

import com.bersanetti.pettzada.domain.exception.EntidadeNaoEncontradaException;
import com.bersanetti.pettzada.domain.model.Entrega;
import com.bersanetti.pettzada.domain.repositoy.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

    private EntregaRepository entregaRepository;

    public Entrega buscar (Long entregaId){
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
    }

}
