package com.bersanetti.pettzada.domain.model;

import com.bersanetti.pettzada.domain.ValidationGroups;
import com.bersanetti.pettzada.domain.exception.NegocioException;
import com.bersanetti.pettzada.model.Ocorrencia;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
@Entity
public class Entrega {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
    @NotNull
    @ManyToOne
    private Cliente cliente;

    @Valid
    @NotNull
    @Embedded
    private Destinatario destinatario;

    @NotNull
    private BigDecimal taxa;

    @OneToMany(mappedBy ="entrega", cascade = CascadeType.ALL) //uma para muitas
    private List<Ocorrencia> ocorrencia = new ArrayList<>();

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated (EnumType.STRING)
    private StatusEntrega status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataPedido;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataFinalizacao;

    public Ocorrencia adicionarOcorrencia(String descricao) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao(descricao);
        ocorrencia.setDataRegistro(OffsetDateTime.now());
        ocorrencia.setEntrega(this);

        this.getOcorrencia().add(ocorrencia);

        return ocorrencia;
    }

    public void finalizar() {
        if(naoPodeSerFinalizada()){
            throw new NegocioException("Entrega não pode ser finalizada.");
        }
        setStatus(StatusEntrega.FINALIZADA);
        setDataFinalizacao(OffsetDateTime.now());
    }

    public boolean podeSerFinalizada(){
        return StatusEntrega.PENDENTE.equals(getStatus());
    }

    public boolean naoPodeSerFinalizada(){
        return !podeSerFinalizada();
    }
}
