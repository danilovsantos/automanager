package br.com.spangence.automanager.service.impl;

import br.com.spangence.automanager.entity.FuncionarioEntity;
import br.com.spangence.automanager.model.FuncionarioModel;
import br.com.spangence.automanager.repository.FuncionarioRepository;
import br.com.spangence.automanager.service.FuncionarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Override
    public FuncionarioModel save(FuncionarioModel funcionarioModel) {
        FuncionarioEntity funcionarioEntity = this.repository.save(new ModelMapper().map(funcionarioModel, FuncionarioEntity.class));
        return new ModelMapper().map(funcionarioEntity, FuncionarioModel.class);
    }

    @Override
    public void delete(Integer id) {
        if(id != null){
            Optional<FuncionarioEntity> func = this.repository.findById(id);
            if(func.isPresent()){
                this.repository.delete(func.get());
            }
        }
    }

    @Override
    public List<FuncionarioModel> listAll() {
        return new ModelMapper().map(this.repository.findAll(), ArrayList.class);
    }

    @Override
    public FuncionarioModel findBydId(Integer id) {
        Optional<FuncionarioEntity> func = this.repository.findById(id);
        return new ModelMapper().map(func, FuncionarioModel.class);
    }

}
