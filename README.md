# Recipes ver. 1.0 #

### Description ###

Simple cookbook project using Spring boot to manage recipes as logged user.

Project's tasks come from JetBrains Academy.

### Features ###

1. Only registered users can access recipes (email as username with at least 8 characters password).
2. To run application you can use one from the existing users (e.g. login: `user@gmail.com` password: `password123!`) or register new one.    
3. As a user you can: 
* read all recipes:
```
http://localhost:8080/api/recipes/all
```
* find recipe by id:
```
http://localhost:8080/api/recipes/7
```
* find recipe by category
```
http://localhost:8080/api/recipes/search/beverage
```
* find recipe by element of name
```
http://localhost:8080/api/recipes/search/?name=tea
```
* find all current logged-in user's recipes:
```
http://localhost:8080/api/recipes/myrecipes
```
* add new recipe
```
http://localhost:8080/api/recipes/new
```
* modify chosen recipe by id
```
localhost:8080/api/recipes/7
```
* delete chosen recipe
```
http://localhost:8080/api/recipes/7
```
* check all registered users emails
```
http://localhost:8080/api/users/all
```


### Motivation ###

Project was created to get to know basic of Spring Boot.

The most valuable concepts of this project (for me) are:
- Spring Data,
- Spring Security,
- SQL,
- REST API,
- HTTP basics,
- unit testing basics (JUnit, Mockito).

### How to run project? ###

1. Ensure that you have Java JDK (1.11 or higher) and Maven (version 3.8 or higher).
2. Clone this repository to your directory.
3. Go to project directory.
4. Compile project with Maven (command: mvn install).
5. Run the project (project works on 8080 port).