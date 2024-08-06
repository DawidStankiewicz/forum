# **Project Forum**
Simple forum created using Spring Boot and Thymeleaf.
Example of simple CRUD web application. 

## Technologies used
- Spring Boot
- Spring Security
- Spring JPA & Hibernate
- Thymeleaf
- Bootstrap 5
- Sass (SCSS)
- Lombok
- Docker
- Docker Compose
- MySQL
- Node.js (gulp)

## Spring's features demonstrated
- custom HandlerMethodArgumentResolver - look at PathTopicArgumentResolver and @PathTopic that looks for `idTopic` in the path and resolves it into Topic object that is injected into controller's methods


## Current functions
### For everyone: 
- Creating a user profile at [/registration](http://localhost:8080/forum/registration).
- Displaying at the homepage: all existing sections, recent topics and recent posts.
- Displaying all topics from section at [/section/{id}](http://localhost:8080/forum/section/1).
- Displaying topic and posts at [/topics/{id}](http://localhost:8080/forum/topics/1).

### Only for authorized user (ROLE_USER):
- Displaying of user profiles at [/user/{username}](http://localhost:8080/forum/user/user) or [/user/id/{id}](http://localhost:8080/forum/user/id/1). Displaying your own profile at [/myprofile](http://localhost:8080/forum/myprofile).
- Editing your profile at [/myprofile/edit](http://localhost:8080/forum/myprofile/edit).
- Removal user profile at [/myprofile/delete](http://localhost:8080/forum/myprofile/delete). This operation requires a password confirmation.
- Creating new topic at [/topics/new](http://localhost:8080/forum/topics/new).
- Creating posts at topic page.
- Removal own topics and posts by button at topic page.

### Only for admin (ROLE_ADMIN): 
- Creating new section at [/section/new](http://localhost:8080/forum/section/new).
- Displaying list of all users at [/users](http://localhost:8080/forum//users)

## Configuration
Example application properties are in the `application.example.properties` file.
Befroe build Forum or run tests you have to create `application.properties` files.

## Development Setup

1. run mysql database - you can use docker compose with file `docker-compose.yml`
1. in directory `scr/main/resources` copy file `application.example.properties` to `application.properties` - change settings if you need
1. run `npm i `
1. run `npm run build`
1. run spring application with your IDE or maven
1. run `npm run watch`
1. application should be running on `localhost:3000`