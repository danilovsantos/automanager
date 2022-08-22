package br.com.spangence.automanager.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
public class ViagemModel implements Serializable {

    private Integer id;
    private Integer usuarioId;
    private Integer carroId;
    private LocalDate dataRetirada;
    private LocalDate dataEntrega;

}
