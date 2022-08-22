package br.com.spangence.automanager.service;

import br.com.spangence.automanager.model.FuncionarioModel;

import java.util.List;

public interface FuncionarioService {

    FuncionarioModel save(FuncionarioModel funcionarioModel);

    void delete(Integer id);

    List<FuncionarioModel> listAll();

    FuncionarioModel findBydId(Integer id);


}
