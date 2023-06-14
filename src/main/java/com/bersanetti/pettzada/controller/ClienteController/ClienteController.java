package com.bersanetti.pettzada.controller.ClienteController;

import com.bersanetti.pettzada.domain.repositoy.ClienteRepository;
import com.bersanetti.pettzada.domain.model.Cliente;
import com.bersanetti.pettzada.domain.service.CatalogoClienteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping ("/clientes")
public class ClienteController {

    private ClienteRepository clienteRepository;
    private CatalogoClienteService catalogoClienteService;

    @GetMapping
    public List<Cliente> listar(){
        return clienteRepository.findAll();
    }

    @GetMapping ("/{clientId}")
    public ResponseEntity<Cliente > buscar (@PathVariable Long clientId){
        return clienteRepository.findById(clientId)
//                .map(cliente -> ResponseEntity.ok(cliente))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
//        Optional<Cliente> cliente = clienteRepository.findById(clientId);
//        if (cliente.isPresent()){
//            return ResponseEntity.ok(cliente.get());
//        }
//        return ResponseEntity.notFound().build();
//    }


    @PostMapping
    @ResponseStatus (HttpStatus.CREATED)
    public Cliente adicionar (@Valid  @RequestBody Cliente cliente){
//       return clienteRepository.save(cliente);
        return catalogoClienteService.salvar(cliente);
    }

    @PutMapping("/{clienteId}")
    public ResponseEntity<Cliente> atualizar (@Valid @PathVariable Long clienteId, @RequestBody Cliente cliente) {
        if (!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }
        cliente.setId(clienteId);
//        cliente = clienteRepository.save(cliente);
        cliente = catalogoClienteService.salvar(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{clienteId}")
    public ResponseEntity<Void> remover (@PathVariable Long clienteId){
        if (!clienteRepository.existsById(clienteId)){
            return ResponseEntity.notFound().build();
        }
//        clienteRepository.deleteById(clienteId);
        catalogoClienteService.excluir(clienteId);
        return ResponseEntity.noContent().build();
    }

}
