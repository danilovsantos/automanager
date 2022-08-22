package br.com.spangence.automanager.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "Viagem")
public class ViagemEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private Integer usuarioId;
    @Column
    private LocalDate dataRetirada;
    @Column
    private LocalDate dataEntrega;
    @ManyToOne
    @JoinColumn(name = "carroId")
    private CarroEntity carroEntity;
    @ManyToOne
    @JoinColumn(name = "funcionarioId")
    private FuncionarioEntity funcionarioEntity;

}
