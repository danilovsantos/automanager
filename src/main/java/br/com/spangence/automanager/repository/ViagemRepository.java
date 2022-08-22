package br.com.spangence.automanager.repository;

import br.com.spangence.automanager.entity.ViagemEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface ViagemRepository extends CrudRepository<ViagemEntity, Long> {

    List<ViagemEntity> findByDataEntregaBetween(LocalDate ini, LocalDate end);

}
