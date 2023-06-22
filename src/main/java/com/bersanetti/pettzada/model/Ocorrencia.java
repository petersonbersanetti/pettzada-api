package com.bersanetti.pettzada.model;

import com.bersanetti.pettzada.domain.model.Entrega;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
@Entity
public class Ocorrencia {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne //muitas ocorrencias tem uma entrega
    private Entrega entrega;

    private String descricao;
    private OffsetDateTime dataRegistro;


}
