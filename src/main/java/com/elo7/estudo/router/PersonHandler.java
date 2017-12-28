package com.elo7.estudo.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.elo7.estudo.model.Person;
import com.elo7.estudo.repository.PersonRespository;

import reactor.core.publisher.Mono;

@Controller
public class PersonHandler {

	@Autowired
	private PersonRespository personRespository;

	public Mono<ServerResponse> findPersonById(final ServerRequest request) {
		Mono<Person> person = personRespository.findById(request.pathVariable("id"));

		return ServerResponse.ok().body(person, Person.class);
	}

	public Mono<ServerResponse> createPerson(final ServerRequest request) {
		request.bodyToMono(Person.class)
			.doOnNext(personRespository::save)
			.doOnSuccess(personResult->{
				UriComponents uriComponents = UriComponentsBuilder.fromPath(request.path())
					.queryParam("id", personResult.getId())
					.build();

				uriComponents.toUri();
			});

		return ServerResponse.ok().build();
	}
}
