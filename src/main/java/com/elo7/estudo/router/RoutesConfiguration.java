package com.elo7.estudo.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutesConfiguration {

    @Autowired
    private PersonHandler personHandler;

/*    @Bean
    RouterFunction<?> routes(PersonRespository personRespository) {
        return nest(path("/person"),
                route(RequestPredicates.GET("/{id}"), personHandler::findPersonById)

                        .andRoute(method(HttpMethod.POST), request -> {
                            personRespository.insert(request.bodyToMono(Person.class))
                                    .doOnNext(person->System.out.printf("Pessoa inserida com sucesso => %s\n",person))
                                    .subscribe();

                            return ServerResponse.ok().build();
                        }));
    }*/

//    @Bean
//    RouterFunction<?> routes() {
//        return RouterFunctions.nest(RequestPredicates.path("/person"),
//            RouterFunctions.route(RequestPredicates.GET("/{id}"), personHandler::findPersonById)
//                .andRoute(RequestPredicates.method(HttpMethod.POST), personHandler::createPerson));
//    }
}