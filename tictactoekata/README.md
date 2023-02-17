## Welcome to 3*3 Tic Tac Toe Game

## Description

This project is a solution for TicTacToe Game challenge with Java, Spring boot, JPA, H2 and TTD with JUnit & Mockito, there are two user stories described below:

- User story 01: I want to create a new game.
- User story 02 : I want to play a turn with checking if there is a winner

Complete description can be found here https://github.com/stephane-genicot/katas/blob/master/TicTacToe.md

## Rules
* X always goes first.
* Players cannot play on a played position.
* Players alternate placing X’s and O’s on the board until either:
  - One player has three in a row, horizontally, vertically or diagonally
  - All nine squares are filled.
* If a player is able to draw three X’s or three O’s in a row, that player wins.
* If all nine squares are filled and neither player has three in a row, the game is a draw.


## Technologies

The technologies used in this solution are :

 * JDK 17
 * Spring boot 3.0
 * JPA
 * Lombok
 * H2 InMemory Database
 * JUnit
 * Mockito
 * Maven
 * Swagger/OpenApi

## Running the application locally

There are several ways to run a Spring Boot application on your local machine
you can run it with your preferred IDE executing the main method of TictactoekataApplication.class.
Alternatively, you can run the app using:

    mvn spring-boot:run
The app will start running at http://localhost:8090

## Test End Point with Postman Or swagger

This application defines the following endpoints:
- create a new game GET http://localhost:8090/api/tictactoe/create
- play a turn POST      http://localhost:8090/api/tictactoe/play

All APIs are "self-documented" by OpenApi using annotations. To view the API docs, run the application and browse to: http://localhost:8090/swagger-ui.html
You can test them directly from this Swagger UI or via Postman or any other rest client.

## Running Tests

You can run tests using one of the following options:

- Using the maven command:

        mvn test
- Alternatively, Using your preferred IDE: running all tests or one by one.