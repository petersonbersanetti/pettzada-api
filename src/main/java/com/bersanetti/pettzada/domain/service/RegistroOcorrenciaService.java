package com.bersanetti.pettzada.domain.service;

import com.bersanetti.pettzada.domain.exception.NegocioException;
import com.bersanetti.pettzada.domain.model.Entrega;
import com.bersanetti.pettzada.domain.repositoy.EntregaRepository;
import com.bersanetti.pettzada.model.Ocorrencia;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

    private BuscaEntregaService buscaEntregaService;

    @Transactional
    public Ocorrencia registrar (Long entregaId, String descricao) {
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return entrega.adicionarOcorrencia(descricao);

    }

}
