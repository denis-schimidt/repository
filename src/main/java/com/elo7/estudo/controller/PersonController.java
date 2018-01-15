package com.elo7.estudo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elo7.estudo.model.Person;
import com.elo7.estudo.repository.PersonRespository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/persons")
public class PersonController {

    @Autowired
    private PersonRespository personRespository;

    @GetMapping
    public Flux<Person> index() {
        return personRespository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Person>> findById(@PathVariable(value = "id") String id){
        return personRespository.findById(id)
            .map(person-> ResponseEntity.ok(person))
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<Person> create(@Valid @RequestBody Person person) {
        return personRespository.save(person);
    }

    @PutMapping
    public Mono<ResponseEntity<Person>> update(@Valid @RequestBody Person person) {

        return personRespository.findById(person.getId())
            .flatMap(personRetrived-> {
                personRetrived.setAge(person.getAge());
                personRetrived.setName(person.getName());
                return personRespository.save(personRetrived)
                    .then(Mono.just(new ResponseEntity<>(person, HttpStatus.OK)));
            })
            .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable(value = "id") String id) {

        return personRespository.findById(id)
            .flatMap(personRetrived ->
                personRespository.delete(personRetrived)
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
            )
            .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
