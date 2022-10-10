# KeVeo Web Project

# Information

* The KeVeo web project has been created to practice web programming skills.
The project consists of a web page that allows you to search and filter movies to quickly access the video platforms where they are available.
It also has a favourite section to save your movies and a comments section where you can comment and rate your favorite movies.
The results of the platforms in which the films are found are fictitious.
### Info Creator
#### Name:
* Esteban Torres Pastor
##### Linkedin:
* https://www.linkedin.com/in/esteban-torres-past/
##### Email:
* estebanto92@gmail.com or esteban92linkedin@gmail.com
##### Repository:
* https://github.com/europa115/KeVeo_PRUEBA.git


# Requirements

* Java >= 18
* Maven >= 4.0

# Start it up

Just execute
`mvn spring-boot:run`

Then launch your favourite browser and go to `http://localhost:8080`. Enjoy!

## How to access to the database
* The database of this project has been created with MySQL Workbench.

* Create your database with: CREATE DATABASE base_keveo;

* Enter in application.yaml:

    username: <- your base_username ->

    password: <- your base_password ->
* Spring Boot will create the database automatically and the data needed to run the web app will be inserted with data.sql.

  If you want your database to save your data permanently you will have to change `jpa.hibernate.ddl-auto: update` in application.yaml
## How to access change user photo
* Amazon AWS S3 has been used to store user photos.
* If you want to use the select user photo function or change your default image you will need to enter in application.yaml:

  access_key_id: <-your_access_key->

  secret_access_key: <-your_secret->

## Users
Login with email and password
* admin@email.com:123
* user1@email.com:123
* user2@email.com:123
* nonactive@email.com:123 (this user will not work just because active field is disabled)
* anonymous@email.com:123 (this user will not work just because active field is disabled)

## Roles

* `ROLE_ADMIN` (for admin user)
* `ROLE_USER` (for user1/user2 user)
* `ROLE_ANONYMOUS` (for anonymous user. User not logged)

## User functions

### Functions for anonymous user:

* Search movies.
* See information, comments and ratings of the movie.
* Access to movie platforms.

### Functions for user logged:


* Search movies.
* See information, comments and ratings of the movie.
* Access to movie platforms.
* Favorites section.
* Edit user account(Change password, change user photo, delete account).
* Comment and rate movies.

### Functions for admin:

* All functions for user logged.
* Edit, delete and view information of all users.
* Edit, delete, add and view information of all movies.
* Delete comments and scores for movies.

## Technologies used in the project

* Java 18
* Maven 4.0
* Spring Boot 2.7.1
* Spring Security
* JPA
* Hibernate
* Lombok
* MySQL
* DataBase H2
* ModelMapper
* Amazon AWS S3
* Thymeleaf
* HTML
* CSS
* JavaScript
* Git
* GitHub