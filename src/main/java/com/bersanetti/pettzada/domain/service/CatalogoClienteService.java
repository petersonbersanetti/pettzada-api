package com.bersanetti.pettzada.domain.service;

import com.bersanetti.pettzada.domain.model.Cliente;
import com.bersanetti.pettzada.domain.repositoy.ClienteRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.aspectj.apache.bcel.generic.InstructionConstants;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class CatalogoClienteService {

    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente salvar (Cliente cliente){
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir (Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}
