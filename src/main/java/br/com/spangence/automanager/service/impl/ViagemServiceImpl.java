package br.com.spangence.automanager.service.impl;

import br.com.spangence.automanager.entity.CarroEntity;
import br.com.spangence.automanager.entity.FuncionarioEntity;
import br.com.spangence.automanager.entity.ViagemEntity;
import br.com.spangence.automanager.model.ViagemModel;
import br.com.spangence.automanager.repository.CarroRepository;
import br.com.spangence.automanager.repository.FuncionarioRepository;
import br.com.spangence.automanager.repository.ViagemRepository;
import br.com.spangence.automanager.service.ViagemService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

@Component
public class ViagemServiceImpl implements ViagemService {

    @Autowired
    private ViagemRepository viagemRepository;

    @Autowired
    private CarroRepository carroRepository;

    private FuncionarioRepository funcionarioRepository;

    @Override
    public ViagemModel save(Integer idFuncionario, Integer idCarro) {
        Optional<CarroEntity> carro = this.carroRepository.findById(idCarro);
        Optional<FuncionarioEntity> func = this.funcionarioRepository.findById(idFuncionario);
        ViagemEntity viagem = new ViagemEntity();
        viagem.setCarroEntity(carro.get());
        viagem.setFuncionarioEntity(func.get());
        viagem.setDataRetirada(LocalDate.now());
        viagem.setUsuarioId(func.get().getId());
        ViagemEntity viagemSaved = this.viagemRepository.save(viagem);
        return new ModelMapper().map(viagemSaved, ViagemModel.class);
    }

    @Override
    public void changeStatus(Integer idCarro, Integer idFuncionario) {
        Optional<CarroEntity> carro = this.carroRepository.findById(idCarro);
        Optional<FuncionarioEntity> func = this.funcionarioRepository.findById(idFuncionario);
        ViagemEntity viagem = new ViagemEntity();
        viagem.setCarroEntity(carro.get());
        viagem.setFuncionarioEntity(func.get());
        viagem.setDataEntrega(LocalDate.now());
        viagem.setUsuarioId(func.get().getId());
        this.viagemRepository.save(viagem);
    }

    @Override
    public List<ViagemModel> findByPeriod(Integer mes, Integer ano) {
        LocalDate initial = LocalDate.of(ano, mes, 1);
        LocalDate start = initial.withDayOfMonth(1);
        LocalDate end = initial.with(lastDayOfMonth());
        return new ModelMapper().map(this.viagemRepository.findByDataEntregaBetween(start, end), ArrayList.class);
    }
}
