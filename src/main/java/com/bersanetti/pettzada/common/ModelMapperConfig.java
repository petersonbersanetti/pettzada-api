package com.bersanetti.pettzada.common;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //componente spring de configuração de bin - adiciona metodos que define bean spring
public class ModelMapperConfig {

    @Bean //inicializa e configura o bean que sera gerenciado pelo spring
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
