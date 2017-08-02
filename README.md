# **Project Forum**
Example of simple CRUD web application with Spring Framework and Thymeleaf. 


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

## Database 
### Connection
The connection settings are in src/main/resources/application.properties

    ## Database connection ##
    spring.datasource.url=jdbc:mysql://localhost:3306/DB_NAME
    spring.datasource.username=USERNAME
    spring.datasource.password=PASSWORD

### Diagram
![Sorry, error loading image of diagram](http://i.imgur.com/1W1xaBi.png)

