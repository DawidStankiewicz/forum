# **Project Forum**
The web application in Java. As the title suggests it is a web forum. 


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
The connection settings are in the forum.properties file.

    db.driver.name=com.mysql.jdbc.Driver
    db.jdbc.url=jdbc:mysql://localhost:3306/forum
    db.username=root
    db.password=root

### Diagram
![Sorry, error loading image of diagram. Diagram is in the file model.mwb at resource/database folder.](http://i.imgur.com/1W1xaBi.png)


## Maven dependencies

### Spring Framework 
Version: 4.3.1.RELEASE

### Spring Security
Version: 4.1.0.RELEASE

### Spring Data 
Version: 1.10.2.RELEASE

### Thymeleaf
Varsion: 3.0.0.RELEASE

### Hibernate
Version: 5.2.1.FINAL

### JUnit
Version: 4.12

### MySql Connector
Version: 5.1.39

### jBCrypt
Version: 0.3m

### Java Servlet API
Version: 3.1.0

-
The information on this site may be updated with a delay.
