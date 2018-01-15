package com.elo7.estudo.router;

import org.springframework.stereotype.Controller;

@Controller
public class PersonHandler {

//	@Autowired
//	private PersonRespository personRespository;
//
//	public Mono<ServerResponse> findPersonById(final ServerRequest request) {
//		 Mono<Person> person = personRespository.findById(request.pathVariable("id"));
//
//		return ServerResponse.ok().body(person, Person.class);
//	}
//
//	public Mono<ServerResponse> createPerson(final ServerRequest request) {
//		//UriComponents uriComponents = null;
//
//		Mono<Person> person = request.bodyToMono(Person.class);
//		Mono<ServerResponse> notFound = ServerResponse.notFound().build();
//
//		return person.flatMap(p -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).body(fromObject(p)))
//			.switchIfEmpty(notFound);
//
//		//		ResponseEntity.ok(person);
//
//		return ServerResponse.ok().build(fromObject(personRespository.savePerson(person)));
//	}
}
