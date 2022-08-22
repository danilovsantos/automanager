package br.com.spangence.automanager.service.impl;

import br.com.spangence.automanager.entity.CarroEntity;
import br.com.spangence.automanager.model.CarroModel;
import br.com.spangence.automanager.repository.CarroRepository;
import br.com.spangence.automanager.service.CarroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CarroServiceImpl implements CarroService {

    @Autowired
    private CarroRepository repository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public CarroModel save(CarroModel model) {
        CarroEntity entity = this.repository.save(new ModelMapper().map(model, CarroEntity.class));
        return new ModelMapper().map(entity, CarroModel.class);
    }

    @Override
    public void delete(Integer id) {
        if(id != null){
            Optional<CarroEntity> carro = this.repository.findById(id);
            if(carro.isPresent()){
                this.repository.delete(carro.get());
            }
        }
    }

    @Override
    public List<CarroModel> listAll() {
        return new ModelMapper().map(this.repository.findAll(), ArrayList.class);
    }

    @Override
    public CarroModel findBydId(Integer id) {
        Optional<CarroEntity> entity = this.repository.findById(id);
        return new ModelMapper().map(entity, CarroModel.class);
    }

    @Override
    public List<CarroModel> getCarsInUse() {

        TypedQuery<CarroEntity> query = em.createQuery(
                "SELECT c " +
                        "FROM ViagemEntity v " +
                        "JOIN v.carroEntity c " +
                        "WHERE c.id = v.carroEntity.id " +
                        "and v.dataRetirada is not null " +
                        "and v.dataEntrega is null", CarroEntity.class);
        List<CarroEntity> resultList = query.getResultList();

        return new ModelMapper().map(this.repository.findAll(), ArrayList.class);
    }
}
