package com.bersanetti.pettzada.domain.service;

import com.bersanetti.pettzada.domain.exception.NegocioException;
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
        boolean emailEmUso = clienteRepository.findByEmail(cliente.getEmail()) //se o email cadastrado do cliente
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente) ); //for diferente do que a gente está salvando, da match (true)
        if (emailEmUso){
            throw new NegocioException("Já existe um cliente cadastrado com este e-mail!");
        }
        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir (Long clienteId) {
        clienteRepository.deleteById(clienteId);
    }
}
