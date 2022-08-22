package br.com.spangence.automanager.model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class CarroModel implements Serializable {

    private Integer id;
    private String modelo;
    private String marca;
    private LocalDate dataFabricacao;

}
