# Spring Boot Boilerplate

## Dependencies

- Spring Boot DevTools
- Spring Web
- Spring Security
- Spring Data JPA
- MySQL Driver
- Spring Data Redis(Access+Driver)
- Spring Validation
- Lombok
- Swagger

## Tech

- Docker 
- Docker Compose
- Session
- JUnit

## How To Build

- To make `build/libs`

```shell
$ ./gradlew build
```

- Start by Docker-Compose

```shell
$ docker-compose -f docker-compose.local.yml up -d --build
```

