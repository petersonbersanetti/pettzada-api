package com.bersanetti.pettzada.controller;

import com.bersanetti.pettzada.assembler.OcorrenciaAssembler;
import com.bersanetti.pettzada.domain.model.Entrega;
import com.bersanetti.pettzada.domain.service.BuscaEntregaService;
import com.bersanetti.pettzada.domain.service.RegistroOcorrenciaService;
import com.bersanetti.pettzada.model.Ocorrencia;
import com.bersanetti.pettzada.model.OcorrenciaModel;
import com.bersanetti.pettzada.model.input.OcorrenciaInput;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaControler {

    private BuscaEntregaService buscaEntregaService;
    private RegistroOcorrenciaService registroOcorrenciaService;
    private OcorrenciaAssembler ocorrenciaAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaModel registrar(@PathVariable Long entregaId,
                                     @Valid @RequestBody OcorrenciaInput ocorrenciaInput){
        Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService
                .registrar(entregaId, ocorrenciaInput.getDescricao());
        return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);
    }

    @GetMapping
    public List<OcorrenciaModel> listar(@PathVariable Long entregaId){

        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencia());
    }
}
