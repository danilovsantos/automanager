package br.com.spangence.automanager.service;

import br.com.spangence.automanager.model.ViagemModel;

import java.util.List;

public interface ViagemService {

    ViagemModel save(Integer idFuncionario, Integer idCarro);

    void changeStatus(Integer idCarro, Integer idFuncionario);

    List<ViagemModel> findByPeriod(Integer mes, Integer ano);

}
