package com.bersanetti.pettzada.domain.model;

import com.bersanetti.pettzada.domain.ValidationGroups;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.groups.Default;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Cliente {

    @NotNull(groups = ValidationGroups.ClienteId.class)
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 60)
    private String nome;

    @NotBlank
    @Email
    @Size(max = 255)
    private String email;

    @NotBlank
    @Size(max = 20)
    private String telefone;

}
