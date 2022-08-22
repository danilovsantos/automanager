package br.com.spangence.automanager.service;

import br.com.spangence.automanager.model.CarroModel;

import java.util.List;

public interface CarroService {

    CarroModel save(CarroModel model);

    void delete(Integer id);

    List<CarroModel> listAll();

    CarroModel findBydId(Integer id);

    List<CarroModel> getCarsInUse();

}
