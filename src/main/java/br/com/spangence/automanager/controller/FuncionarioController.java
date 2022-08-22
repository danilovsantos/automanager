package br.com.spangence.automanager.controller;

import br.com.spangence.automanager.model.FuncionarioModel;
import br.com.spangence.automanager.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;


    @PostMapping
    public ResponseEntity save(@RequestBody FuncionarioModel model){
        FuncionarioModel funcionarioModel = this.service.save(model);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(funcionarioModel.getId()).toUri();
        return ResponseEntity.created(location).body(funcionarioModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer idFuncionario){
        FuncionarioModel func = this.service.findBydId(idFuncionario);
        if(func == null){
            return ResponseEntity.notFound().build();
        }
        this.service.delete(func.getId());
        return ResponseEntity.ok("Funcionário Removido com Sucesso.");
    }

    @GetMapping("/all")
    public ResponseEntity getAll(){
        List<FuncionarioModel> list = this.service.listAll();
        return ResponseEntity.ok(list);
    }

}
