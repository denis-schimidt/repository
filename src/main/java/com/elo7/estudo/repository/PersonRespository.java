package com.elo7.estudo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.elo7.estudo.model.Person;

import reactor.core.publisher.Flux;

public interface PersonRespository extends ReactiveMongoRepository<Person, String> {
    Flux<Person> findByName(String name);
}
