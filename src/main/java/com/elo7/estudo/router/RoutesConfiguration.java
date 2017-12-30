package com.elo7.estudo.router;

import com.elo7.estudo.model.Person;
import com.elo7.estudo.repository.PersonRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import static org.springframework.web.reactive.function.server.RequestPredicates.method;
import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RoutesConfiguration {

    @Autowired
    private PersonHandler personHandler;

    @Bean
    RouterFunction<?> routes(PersonRespository personRespository) {
        return nest(path("/person"),
                route(RequestPredicates.GET("/{id}"), personHandler::findPersonById)

                        .andRoute(method(HttpMethod.POST), request -> {
                            personRespository.insert(request.bodyToMono(Person.class))
                                    .doOnNext(person->System.out.printf("Pessoa inserida com sucesso => %s\n",person))
                                    .subscribe();

                            return ServerResponse.ok().build();
                        }));
    }
}