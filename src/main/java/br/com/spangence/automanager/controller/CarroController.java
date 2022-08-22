package br.com.spangence.automanager.controller;

import br.com.spangence.automanager.model.CarroModel;
import br.com.spangence.automanager.service.CarroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/carros")
public class CarroController {

    @Autowired
    private CarroService service;


    @PostMapping
    public ResponseEntity save(@RequestBody CarroModel model){
        CarroModel carroModel = this.service.save(model);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(carroModel.getId()).toUri();
        return ResponseEntity.created(location).body(carroModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        CarroModel car = this.service.findBydId(id);
        if(car == null){
            return ResponseEntity.notFound().build();
        }
        this.service.delete(car.getId());
        return ResponseEntity.ok("Carro Removido com Sucesso.");
    }

    @GetMapping("/all")
    public ResponseEntity getAll(){
        List<CarroModel> list = this.service.listAll();
        return ResponseEntity.ok(list);
    }

}
