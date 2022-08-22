package br.com.spangence.automanager.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "Carro")
public class CarroEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String modelo;
    @Column
    private String marca;
    @Column
    private LocalDate dataFabricacao;

}
