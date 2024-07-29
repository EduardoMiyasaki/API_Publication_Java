package com.example.springboot.controllers;

import com.example.springboot.dtos.PublicationRecordDto;
import com.example.springboot.models.PublicationModel;
import com.example.springboot.repositories.PublicationRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class PublicationController {

    @Autowired
    PublicationRepository publicationRepository;

    // injeção de dependências meio que instanciando

    // Metódo Post = receber dados,fazer validações e salvar na base de dados

    // esse productRecordDto @valid para ter as validações ditas no productRecordDto
    // depois você instancia um productModel
    // depois conversão de recordDto para model utilizando um recurso do próprio spring
    // no product Repository tem métodos default e esse productRespository que vai o productModel salvar no banco

    @PostMapping("/publications")
    public ResponseEntity<PublicationModel> savePublication(@RequestBody @Valid PublicationRecordDto publicationRecordDto){

        // instanciando um Publication Model
        var publicationModel = new PublicationModel();
        // Fazendo a conversão de dto para model
        BeanUtils.copyProperties(publicationRecordDto ,  publicationModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(publicationRepository.save(publicationModel));
    }

    @GetMapping("/publications")
    public ResponseEntity<List<PublicationModel>> getAllPublication(){

        return ResponseEntity.status(HttpStatus.OK).body(publicationRepository.findAll());
    }

    // <Object> pois vamos ter dois tipos de respostas
    // @PathVariable serve para você receber o id e ele ser passado pros parâmetros da função
    @GetMapping("/publications/{id}")
    public ResponseEntity<Object> getOnePublication(@PathVariable(value = "id") UUID id){

        Optional<PublicationModel> publication0 = publicationRepository.findById(id);

        if(publication0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publication Not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(publication0.get());
    }

    //update
    @PutMapping("/publications/{id}")
    public ResponseEntity<Object> updatePublication(@PathVariable(value = "id") UUID id, @RequestBody @Valid PublicationRecordDto publicationRecordDto){

        Optional<PublicationModel> publication0 = publicationRepository.findById(id);

        if(publication0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publication is not found");
        }

        var publicationModel = publication0.get();
        BeanUtils.copyProperties(publicationRecordDto , publicationModel);
        return ResponseEntity.status(HttpStatus.OK).body(publicationRepository.save(publicationModel));
    }

    @DeleteMapping("/publications/{id}")
    public ResponseEntity<Object> deletePublication(@PathVariable(value = "id")UUID id){

        Optional<PublicationModel> publication0 = publicationRepository.findById(id);

        if(publication0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Publication is not found");
        }

        publicationRepository.delete(publication0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Publication has been deleted");
    }
}
