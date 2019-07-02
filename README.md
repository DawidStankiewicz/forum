# **Project Forum**
Simple forum created using Spring Framework and Thymeleaf.
Example of simple CRUD web application. 


## Current functions
### For everyone: 
- Creating a user profile at [/registration](http://localhost:8080/forum/registration).
- Displaying at the homepage: all existing sections, recent topics and recent posts.
- Displaying all topics from section at [/section/{id}](http://localhost:8080/forum/section/1).
- Displaying topic and posts at [/topic/{id}](http://localhost:8080/forum/topic/1).

### Only for authorized user (ROLE_USER):
- Displaying of user profiles at [/user/{username}](http://localhost:8080/forum/user/user) or [/user/id/{id}](http://localhost:8080/forum/user/id/1). Displaying your own profile at [/myprofile](http://localhost:8080/forum/myprofile).
- Editing your profile at [/myprofile/edit](http://localhost:8080/forum/myprofile/edit).
- Removal user profile at [/myprofile/delete](http://localhost:8080/forum/myprofile/delete). This operation requires a password confirmation.
- Creating new topic at [/topic/new](http://localhost:8080/forum/topic/new).
- Creating posts at topic page.
- Removal own topics and posts by button at topic page.

### Only for admin (ROLE_ADMIN): 
- Creating new section at [/section/new](http://localhost:8080/forum/section/new).
- Displaying list of all users at [/users](http://localhost:8080/forum//users)

## Configuration
Example application properties are in the `application.example.properties` file.
Befroe build Forum or run tests you have to create `application.properties` files.

```properties
## Database connection ##
spring.datasource.url=jdbc:mysql://localhost:3306/forum
spring.datasource.username=forum
spring.datasource.password=password

## Hibernate ##
spring.jpa.hibernate.ddl-auto=create
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl

## Thymeleaf ##
spring.thymeleaf.cache=false
spring.messages.basename=messages/messages

## Static resources ##
spring.mvc.static-path-pattern=/resources/**

## Email ##
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
spring.mail.transport.protocol=stmp
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=### EMAIL ###
spring.mail.password=### PASSWORD ### 
spring.mail.smtp.auth=true
```