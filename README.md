# Notes
Used [ToDo MVC](http://todomvc.com/examples/backbone/) features to add minor enhancements to Item (`done` attribute) and CRUD (filter by `done`). Having familiarity with DropWizard, thought this was a good chance to dabble in Spring Boot:
* Chose Jersey instead of SpringWebMvc because I prefer:
  ```
  @GET
  @Path("/id")
  ```
  over
  ```
  @RequestMapping(value="/id", method = RequestMethod.GET)
  ```
* Used JPA to avoid SQL grunt-work.  Spring Data makes this trivial with `Repository` providing auto-generated CRUD and queries.  The REST endpoints are such a thin CRUD-wrapper that `@Transactional` is overkill (but was trivial to add).
* Contemplated separate `Item` classes in `jpa.model` and `api.model` packages to decouple the database and API representations (auto-converted with [modelmapper](http://modelmapper.org/)) but this was premature.
* Decided against [Spring Data REST](https://projects.spring.io/spring-data-rest/) because [HAL](http://stateless.co/hal_specification.html) is really dense and verbose... also because this whole exercise would've been **_one_** file.

## TODO
Thought about adding a `User` entity with a one-to-many relationship to `Item` then change the API to use HTTP `Basic-Auth` so that users have private To Do lists.

# Running
This all assumes you're on *nix or Mac and have Java installed.  The `./gradlw.bat` wrapper should work for WIndows, but I don't know about any of the `docker` stuff.
## Local Development
This uses a default [application.properties](https://github.com/StdVectorBool/todo/blob/master/src/main/resources/application.properties) that runs the service with an H2 in-memory database.  Useful for local development since the database resets with service restart.

To build and run in one-step:
```
./gradlew bootRun
```

## Docker
For a more production-like configuration, build the `com.gmail.lnqhien/todo` image:
```
./gradlew buildDocker
```

The [application-gradle.properties](https://github.com/StdVectorBool/todo/blob/master/application-docker.properties) baked into the image will make it connect to MySQL instead of H2.

Then bring up the isolated MySQL instance:
```
docker-compose up -d db
```

Once the `db` instance is ready (e.g.: `docker logs todo_db_1`), bring up the `todo` service:
```
docker-compose up -d todo
```

Avoid the one-shot `docker-compose up` since Compose's `depends_on` doesn't guarantee the `db` is ready before the `todo` tries to connect.

# Testing
Both methods above make the service available at http://localhost:8080/todo.  There is a CORS filter in place to allow browser clients to call the API.

Unit-testing with Spring-Jersey isn't as polished as SpringWebMvc and I ran out of time... so there is a rudimentary [test.sh](https://github.com/StdVectorBool/todo/blob/master/test.sh) script to exercise all the available APIs:

| Endpoint | Description |
| ------------- | ------------- |
| `POST /todo` | Add a new To-Do item |
| `GET /todo/{id}` | Get a single To-Do item |
| `DELETE /todo/{id}` | Delete a single To-Do item |
| `PUT /todo/{id}` | Update a single To-Do item |
| `GET /todo`| Get all To-Do items. <br> Supports `page`, `size`, `done` query parameters for pagination and filtering, e.g. <br> `GET /todo?page=1&size=5&done=false`