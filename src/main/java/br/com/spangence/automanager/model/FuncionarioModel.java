package br.com.spangence.automanager.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FuncionarioModel implements Serializable {

    private Integer id;
    private String nome;
    private Integer matricula;
}
