package com.bersanetti.pettzada.controller.ClienteController;

import com.bersanetti.pettzada.assembler.EntregaAssembler;
import com.bersanetti.pettzada.domain.model.Entrega;
import com.bersanetti.pettzada.domain.repositoy.EntregaRepository;
import com.bersanetti.pettzada.domain.service.SolicitacaoEntregaService;
import com.bersanetti.pettzada.model.DestinatarioModel;
import com.bersanetti.pettzada.model.EntregaModel;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping ("/entregas")
public class EntregaContoller {

    private EntregaRepository entregaRepository;
    private SolicitacaoEntregaService solicitacaoEntregaService;
    private EntregaAssembler entregaAssembler;

    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public EntregaModel solicitar(@Valid @RequestBody Entrega entrega){
        Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(entrega);
        return entregaAssembler.toModel(entregaSolicitada);
    }

    @GetMapping
    public List<EntregaModel> listar(){
        return entregaAssembler.toCollectionModel(entregaRepository.findAll());
    }

    @GetMapping("/{entregaId}")
    public ResponseEntity<EntregaModel> buscar (@PathVariable Long entregaId){
        return entregaRepository.findById(entregaId) //se nao existir nada dentro do optional retornado pelo entregaId
                .map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
                .orElse(ResponseEntity.notFound().build());

//                    EntregaModel entregaModel = new EntregaModel();
//                    entregaModel.setId(entrega.getId());
//                    entregaModel.setNomeCliente(entrega.getCliente().getNome());
//                    entregaModel.setDestinatario(new DestinatarioModel());
//                    entregaModel.getDestinatario().setNome(entrega.getDestinatario().getNome());
//                    entregaModel.getDestinatario().setLogradouro(entrega.getDestinatario().getLogradouro());
//                    entregaModel.getDestinatario().setNumero(entrega.getDestinatario().getNumero());
//                    entregaModel.getDestinatario().setComplemento(entrega.getDestinatario().getComplemento());
//                    entregaModel.getDestinatario().setBairro(entrega.getDestinatario().getBairro());
//                    entregaModel.setTaxa(entrega.getTaxa());
//                    entregaModel.setStatus(entrega.getStatus());
//                    entregaModel.setDataFinalizacao(entrega.getDataFinalizacao());



//                })
//                .orElse(ResponseEntity.notFound().build()); //retorna um notfound 404
    }

}
