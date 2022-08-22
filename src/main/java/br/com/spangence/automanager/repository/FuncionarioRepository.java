package br.com.spangence.automanager.repository;

import br.com.spangence.automanager.entity.FuncionarioEntity;
import org.springframework.data.repository.CrudRepository;

public interface FuncionarioRepository extends CrudRepository<FuncionarioEntity, Integer> {
}
