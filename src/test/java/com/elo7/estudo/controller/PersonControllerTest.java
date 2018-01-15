package com.elo7.estudo.controller;

import com.elo7.estudo.model.Person;
import com.elo7.estudo.repository.PersonRespository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerTest {

	private static final String NAME_OF_PERSON = "Fulano da Silva";
	private static final int AGE_OF_PERSON = 30;

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private PersonRespository repository;

	@Test
	public void testCreate() {
		webTestClient.post().uri("/persons")
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.accept(MediaType.APPLICATION_JSON_UTF8)
			.body(Mono.just(new Person(NAME_OF_PERSON, AGE_OF_PERSON)), Person.class)
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
			.expectBody()
			.jsonPath("$.id").isNotEmpty()
			.jsonPath("$.name").isEqualTo("Fulano da Silva")
			.jsonPath("$.age").isEqualTo(AGE_OF_PERSON);
	}

	@Test
	public void testGetAllPersons() {
		webTestClient.get().uri("/persons")
			.accept(MediaType.APPLICATION_JSON_UTF8)
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
			.expectBodyList(Person.class);
	}

	@Test
	public void testGetSinglePerson() {
		Person person = repository.save(new Person("Ciclano", 25)).block();

		webTestClient.get()
			.uri("/persons/{id}", Collections.singletonMap("id", person.getId()))
			.exchange()
			.expectStatus().isOk()
			.expectBody()
			.consumeWith(response -> assertThat(response.getResponseBody()).isNotNull());
	}

	@Test
	public void testUpdatePerson() {
		Person person = repository.save(new Person("Beltrano", 45)).block();
		Person personUpdated = new Person("Beltrano da Silva", 35);
		personUpdated.setId(person.getId());

		webTestClient.put()
			.uri("/persons")
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.accept(MediaType.APPLICATION_JSON_UTF8)
			.body(Mono.just(personUpdated), Person.class)
			.exchange()
			.expectStatus().isOk()
			.expectHeader().contentType(MediaType.APPLICATION_JSON_UTF8)
			.expectBody()
			.jsonPath("$.id").isEqualTo(personUpdated.getId())
			.jsonPath("$.name").isEqualTo(personUpdated.getName())
			.jsonPath("$.age").isEqualTo(personUpdated.getAge());
	}

	@Test
	public void testDeleteTweet() {
		Person person = repository.save(new Person("Laura", 22)).block();

		webTestClient.delete()
			.uri("/persons/{id}", Collections.singletonMap("id", person.getId()))
			.exchange()
			.expectStatus().isOk();
	}
}