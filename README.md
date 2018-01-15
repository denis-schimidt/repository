# Spring Boot Webflux Reactive Mongo

This is a sample application that shows how to build a web application using
 - Spring Boot 2
 - Spring Webflux
 - Spring Reactive Data MongoDb
 - Spring Security Reactive Webflux
 
 
<br/>
Please see the following pages for more details
  
  - Spring Web Reactive <br/><a>http://docs.spring.io/spring-framework/docs/5.0.0.M1/spring-framework-reference/html/web-reactive.html</a>
  - Spring Data Reactive <br/><a>https://spring.io/blog/2016/11/28/going-reactive-with-spring-data</a>
  - Spring Functional Web Framework <br/><a>https://spring.io/blog/2016/09/22/new-in-spring-5-functional-web-framework</a>

## Running

In application.properties, configure appropriate values.
<br/>
<br/>
Run this using using the gradle wrapper included

```
./gradlew bootRun
```

And then go to http://localhost:8080 to test the API's.


## cURL Commands

You can try the following API's once the server is running.

GET __/persons__

``` curl http://localhost:8080/persons -v -u fulano:password```

GET __/persons/{id}__

``` curl http://localhost:8080/persons/{id} -v -u denis:password```

POST __/persons__

``` curl -X POST -d '{"name":"Maria","age":30}' -H "Content-Type: application/json" http://localhost:8080/persons -v -u denis:password```

PUT __/persons__

``` curl -X PUT -d '{"id":"<ID>", "name":"Maria","age":30}' -H "Content-Type: application/json" http://localhost:8080/persons -v -u denis:password```

DELETE __/persons/{id}__

``` curl -X DELETE http://localhost:8080/persons/{id} -v -u denis:password```
