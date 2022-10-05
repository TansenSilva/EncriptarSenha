package com.example.encriptar.senha.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="Usuario")
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String idade;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String telefone;

    @Column(unique = true)
    private String login;

    private String senha;
}
