package br.com.spangence.automanager.controller;

import br.com.spangence.automanager.model.ViagemModel;
import br.com.spangence.automanager.service.CarroService;
import br.com.spangence.automanager.service.FuncionarioService;
import br.com.spangence.automanager.service.ViagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/viagem")
public class ViagemController {

    @Autowired
    private ViagemService service;

    @Autowired
    private CarroService carroService;

    @Autowired
    private FuncionarioService funcionarioService;

    @PostMapping("/{idFuncionario}/{idCarro")
    public ResponseEntity save(@PathVariable Integer idFuncionario, @PathVariable Integer idCarro){
        ViagemModel viagem = this.service.save(idFuncionario, idCarro);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(viagem.getId()).toUri();
        return ResponseEntity.created(location).body(viagem);
    }

    @DeleteMapping("/{idFuncionario}/{idCarro")
    public ResponseEntity delete(@PathVariable Integer idFuncionario, @PathVariable Integer idCarro){
        this.service.changeStatus(idCarro, idFuncionario);
        return ResponseEntity.ok("Veículo devolvido com sucesso.");
    }

    @GetMapping("realizadas/{mes}/{ano}")
    public ResponseEntity realizadas(@PathVariable Integer mes, @PathVariable Integer ano){
        List<ViagemModel> viagens = this.service.findByPeriod(mes, ano);
        if(viagens == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(viagens);
    }

}
